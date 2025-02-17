package lqc.com.pis.dto.response.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Integer id;
    String username;
    String firstName;
    String lastName;
    String phoneNumber;
    String email;
    String avatar;
    String qrCode;
    LocalDate birthday;
    Boolean isActive;
}
