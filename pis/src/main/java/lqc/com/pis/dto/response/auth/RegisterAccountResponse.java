package lqc.com.pis.dto.response.auth;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterAccountResponse {
    String username;
    String firstName;
    String lastName;
    String phoneNumber;
    String email;
    String avatar;
    String qrCode;
    LocalDate birthday;
    Boolean isActive;
    Boolean isLogin;
    Integer loginAttempts;
    String otp;
}
