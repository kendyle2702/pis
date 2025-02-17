package lqc.com.pis.service.inter;

import com.nimbusds.jose.JOSEException;
import lqc.com.pis.dto.request.auth.*;
import lqc.com.pis.dto.response.auth.*;

import java.text.ParseException;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    IntrospectResponse introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException;
    void logout(LogoutRequest logoutRequest) throws ParseException, JOSEException;
    RegisterAccountResponse register(RegisterAccountRequest registerAccountRequest);
    ForgotPasswordResponse forgotPassword(ForgotPasswordRequest forgotPasswordRequest);
    ResetPasswordResponse resetPassword(ResetPasswordRequest resetPasswordRequest);
    void updatePassword(UpdatePasswordRequest updatePasswordRequest);
}
