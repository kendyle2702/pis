package lqc.com.pis.dto.response.post;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ElementCommentLevel2Response {
    Long id;
    UserPostResponse userPostResponse;
    String content;
    String url;
    int likes;
    String createTime;
    String type;
    boolean isLike;
}
