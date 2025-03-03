package lqc.com.pis.dto.response.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserPostResponse {
    int userId;
    String username;
    String avatar;
    int followers;
    String firstName;
    String lastName;
    boolean isFollow;

    public UserPostResponse(int userId, String username, String avatar, int followers, boolean isFollow) {
        this.userId = userId;
        this.username = username;
        this.avatar = avatar;
        this.followers = followers;
        this.isFollow = isFollow;
    }

}
