package lqc.com.pis.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import lqc.com.pis.dto.request.auth.*;
import lqc.com.pis.dto.response.auth.*;
import lqc.com.pis.entity.InvalidAccessToken;
import lqc.com.pis.entity.User;
import lqc.com.pis.exception.AppException;
import lqc.com.pis.exception.ErrorCode;
import lqc.com.pis.mapper.UserMapper;
import lqc.com.pis.repository.InvalidAccessTokenRepository;
import lqc.com.pis.repository.UserRepository;
import lqc.com.pis.service.inter.AuthService;
import lqc.com.pis.service.inter.EmailService;
import lqc.com.pis.service.inter.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthServiceImpl implements AuthService {
    UserRepository userRepository;

    InvalidAccessTokenRepository invalidAccessTokenRepository;

    EmailService emailService;

    UserMapper userMapper;

    private final UserService userService;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        var user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() ->new AppException(ErrorCode.USER_NOT_EXISTED));

        if(!user.getIsActive()){
            throw new AppException(ErrorCode.USER_BANNED);
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(loginRequest.getPassword(),user.getHashPassword());

        if(!authenticated){
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        userRepository.save(user);

        var token = generateToken(user.getUsername());

        return LoginResponse.builder()
                .authenticated(true)
                .token(token).build();
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException {

        var token = introspectRequest.getToken();

        boolean isValid = true;

        try{
            verifyToken(token);
        }
        catch (AppException e){
            isValid = false;
        }


        return IntrospectResponse.builder()
                .valid(isValid)
                .build();
    }

    @Override
    public void logout(LogoutRequest logoutRequest) throws ParseException, JOSEException {
        var signToken = verifyToken(logoutRequest.getToken());

        String jit = signToken.getJWTClaimsSet().getJWTID();

        Date expiryDate = signToken.getJWTClaimsSet().getExpirationTime();

        InvalidAccessToken invalidAccessToken = InvalidAccessToken.builder()
                .tokenUuid(jit)
                .expiryTime(expiryDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .build();


        invalidAccessTokenRepository.save(invalidAccessToken);

    }

    @Override
    public RegisterAccountResponse register(RegisterAccountRequest registerAccountRequest) {

        boolean isExistAccount = userRepository.existsByEmail(registerAccountRequest.getEmail());

        if(isExistAccount){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(registerAccountRequest);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setHashPassword(passwordEncoder.encode(registerAccountRequest.getPassword().trim()));

        user.setUsername(registerAccountRequest.getEmail().trim().split("@")[0]);
        user.setIsActive(true);
        user.setLoginAttempts(0);

        userRepository.save(user);

        return userMapper.toRegisterAccountResponse(user);
    }

    @Override
    public ForgotPasswordResponse forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
        boolean isExist = userRepository.existsByEmail(forgotPasswordRequest.getEmail());

        if (!isExist){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        else{
            //send otp
            User user = userRepository.findByEmail(forgotPasswordRequest.getEmail()).orElseThrow(()->new AppException(ErrorCode.USER_NOT_EXISTED));

            if(!user.getIsActive()){
                throw new AppException(ErrorCode.USER_BANNED);
            }

            Random random = new Random();
            int otp = random.nextInt(900000) + 100000;
            user.setOtp(String.valueOf(otp));
            user.setLoginAttempts(0);

            userRepository.save(user);

            emailService.sendEmail(forgotPasswordRequest.getEmail(),"SEND YOUR OTP FROM PIS APP", "Your OTP is: " + user.getOtp());
        }
        return new ForgotPasswordResponse(true);
    }

    @Override
    public ResetPasswordResponse resetPassword(ResetPasswordRequest resetPasswordRequest) {
        // check token right
        User user = userRepository.findByEmail(resetPasswordRequest.getEmail()).orElseThrow(()->new AppException(ErrorCode.USER_NOT_EXISTED));
        if(user.getOtp().equals(resetPasswordRequest.getOtp())){
            //reset data
            user.setLoginAttempts(0);
            userRepository.save(user);

            return ResetPasswordResponse.builder()
                    .isValid(true)
                    .exceedTime(false)
                    .build();
        }
        else{
            if(user.getLoginAttempts() >= 4){
                return ResetPasswordResponse.builder()
                        .isValid(false)
                        .exceedTime(true)
                        .build();
            }
            else{
                user.setLoginAttempts(user.getLoginAttempts() + 1);
                userRepository.save(user);
                return ResetPasswordResponse.builder()
                        .isValid(false)
                        .exceedTime(false)
                        .build();
            }
        }
    }

    @Override
    public void updatePassword(UpdatePasswordRequest updatePasswordRequest) {
        User user = userRepository.findByEmail(updatePasswordRequest.getEmail()).orElseThrow(()->new AppException(ErrorCode.USER_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        user.setHashPassword(passwordEncoder.encode(updatePasswordRequest.getPassword()));

        userRepository.save(user);
    }

    private SignedJWT verifyToken(String token) throws ParseException, JOSEException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        var verified = signedJWT.verify(verifier);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        // right token and have time
        if(!(verified && expiryTime.after(new Date()))){
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        // check logout token
        if(invalidAccessTokenRepository.existsByTokenUuid(signedJWT.getJWTClaimsSet().getJWTID()))
        {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        return signedJWT;
    }

    private String generateToken(String username) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("lqc.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(5, ChronoUnit.DAYS).toEpochMilli()))
                .claim("userId", userRepository.findUserIdByUsername(username))
                .jwtID(UUID.randomUUID().toString())
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());



        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

}
