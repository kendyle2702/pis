package lqc.com.pis.controller;

import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lqc.com.pis.dto.request.IntrospectRequest;
import lqc.com.pis.dto.request.LoginRequest;
import lqc.com.pis.dto.request.UserCreationRequest;
import lqc.com.pis.dto.response.ApiResponse;
import lqc.com.pis.dto.response.IntrospectResponse;
import lqc.com.pis.dto.response.LoginResponse;
import lqc.com.pis.entity.User;
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
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    AuthService authService;

    @PostMapping("/register")
    ResponseEntity<ApiResponse<User>> register(@RequestBody UserCreationRequest userCreationRequest) {
        return null;
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
}
