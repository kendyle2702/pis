package lqc.com.pis.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    String username;
    String firstName;
    String lastName;
    String phoneNumber;
    String email;
    String avatar;
    String qrCode;
    LocalDate birthday;
    String hashPassword;
    Boolean isActive;
    Boolean isLogin;
    Integer loginAttempts;
    String otp;
}
