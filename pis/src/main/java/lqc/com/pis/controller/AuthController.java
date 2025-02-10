package lqc.com.pis.controller;

import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lqc.com.pis.dto.request.auth.*;
import lqc.com.pis.dto.response.ApiResponse;
import lqc.com.pis.dto.response.auth.*;
import lqc.com.pis.service.inter.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    AuthService authService;

    @PostMapping("/register")
    ResponseEntity<ApiResponse<RegisterAccountResponse>> register(@RequestBody RegisterAccountRequest registerAccountRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<RegisterAccountResponse>builder()
                        .code(2001)
                        .data(authService.register(registerAccountRequest)).build()
        );
    }

    @PostMapping("/login")
    ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<LoginResponse>builder()
                        .code(2000)
                        .data(authService.login(loginRequest)).build()
                        );
    }

    @PostMapping("/introspect")
    ResponseEntity<ApiResponse<IntrospectResponse>> introspect(@RequestBody IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<IntrospectResponse>builder()
                        .code(2000)
                        .data(authService.introspect(introspectRequest)).build()
        );
    }

    @PostMapping("/logout")
    ResponseEntity<ApiResponse<Void>> logout(@RequestBody LogoutRequest logoutRequest) throws ParseException, JOSEException {
        authService.logout(logoutRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.<Void>builder().code(2000).message("Logout success").build());
    }

    @PostMapping("/forgot-password")
    ResponseEntity<ApiResponse<ForgotPasswordResponse>> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.<ForgotPasswordResponse>builder()
                .code(2000)
                .data(authService.forgotPassword(forgotPasswordRequest)).build());
    }

    @PostMapping("/reset-password")
    ResponseEntity<ApiResponse<ResetPasswordResponse>> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.<ResetPasswordResponse>builder()
                .code(2000)
                .data(authService.resetPassword(resetPasswordRequest)).build());
    }

    @PostMapping("/update-password")
    ResponseEntity<ApiResponse<Void>> updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest) {
        authService.updatePassword(updatePasswordRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.<Void>builder()
                .code(2000)
                .message("update password success")
                .build());
    }


//
//    @PostMapping("/update-password")


}
