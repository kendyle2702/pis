package lqc.com.pis.dto.response.profile;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FollowResponse {
    int followers;
    int followingNumbers;
}
