package lqc.com.pis.mapper;

import javax.annotation.processing.Generated;
import lqc.com.pis.dto.request.auth.RegisterAccountRequest;
import lqc.com.pis.dto.request.user.UserUpdateRequest;
import lqc.com.pis.dto.response.auth.RegisterAccountResponse;
import lqc.com.pis.dto.response.user.UserResponse;
import lqc.com.pis.dto.response.user.UserUpdateResponse;
import lqc.com.pis.entity.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-10T08:50:26+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.2-ea (Private Build)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public void updateUser(User user, UserUpdateRequest userUpdateRequest) {
        if ( userUpdateRequest == null ) {
            return;
        }

        user.setFirstName( userUpdateRequest.getFirstName() );
        user.setLastName( userUpdateRequest.getLastName() );
        user.setEmail( userUpdateRequest.getEmail() );
        user.setBirthday( userUpdateRequest.getBirthday() );
        user.setIsActive( userUpdateRequest.getIsActive() );
    }

    @Override
    public User toUser(RegisterAccountRequest registerAccountRequest) {
        if ( registerAccountRequest == null ) {
            return null;
        }

        User user = new User();

        user.setHashPassword( registerAccountRequest.getPassword() );
        user.setFirstName( registerAccountRequest.getFirstName() );
        user.setLastName( registerAccountRequest.getLastName() );
        user.setEmail( registerAccountRequest.getEmail() );

        return user;
    }

    @Override
    public RegisterAccountResponse toRegisterAccountResponse(User user) {
        if ( user == null ) {
            return null;
        }

        RegisterAccountResponse.RegisterAccountResponseBuilder registerAccountResponse = RegisterAccountResponse.builder();

        registerAccountResponse.username( user.getUsername() );
        registerAccountResponse.firstName( user.getFirstName() );
        registerAccountResponse.lastName( user.getLastName() );
        registerAccountResponse.phoneNumber( user.getPhoneNumber() );
        registerAccountResponse.email( user.getEmail() );
        registerAccountResponse.avatar( user.getAvatar() );
        registerAccountResponse.qrCode( user.getQrCode() );
        registerAccountResponse.birthday( user.getBirthday() );
        registerAccountResponse.isActive( user.getIsActive() );
        registerAccountResponse.isLogin( user.getIsLogin() );
        registerAccountResponse.loginAttempts( user.getLoginAttempts() );
        registerAccountResponse.otp( user.getOtp() );

        return registerAccountResponse.build();
    }

    @Override
    public UserUpdateResponse toUserUpdateResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserUpdateResponse.UserUpdateResponseBuilder userUpdateResponse = UserUpdateResponse.builder();

        userUpdateResponse.username( user.getUsername() );
        userUpdateResponse.firstName( user.getFirstName() );
        userUpdateResponse.lastName( user.getLastName() );
        userUpdateResponse.phoneNumber( user.getPhoneNumber() );
        userUpdateResponse.email( user.getEmail() );
        userUpdateResponse.avatar( user.getAvatar() );
        userUpdateResponse.qrCode( user.getQrCode() );
        userUpdateResponse.birthday( user.getBirthday() );
        userUpdateResponse.isActive( user.getIsActive() );
        userUpdateResponse.isLogin( user.getIsLogin() );
        userUpdateResponse.loginAttempts( user.getLoginAttempts() );
        userUpdateResponse.otp( user.getOtp() );

        return userUpdateResponse.build();
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id( user.getId() );
        userResponse.username( user.getUsername() );
        userResponse.firstName( user.getFirstName() );
        userResponse.lastName( user.getLastName() );
        userResponse.phoneNumber( user.getPhoneNumber() );
        userResponse.email( user.getEmail() );
        userResponse.avatar( user.getAvatar() );
        userResponse.qrCode( user.getQrCode() );
        userResponse.birthday( user.getBirthday() );
        userResponse.isActive( user.getIsActive() );

        return userResponse.build();
    }
}
