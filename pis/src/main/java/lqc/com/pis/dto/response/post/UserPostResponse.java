package lqc.com.pis.dto.response.post;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserPostResponse {
    int userId;
    String username;
    String avatar;
    boolean isFollow;
}
