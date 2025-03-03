package lqc.com.pis.dto.response.profile;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lqc.com.pis.dto.response.post.UserPostResponse;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FollowResponse {
    int followers;
    List<UserPostResponse> userFollowers;
    int followingNumbers;
    List<UserPostResponse> userFollowing;

}
