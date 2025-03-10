package lqc.com.pis.dto.response.post;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentLevel2Response {
    Long id;
    UserPostResponse userPostResponse;
    String content;
    String url;
    int likes;
    int comments;
    String createTime;
    String type;
    boolean isLike;
    List<ElementCommentLevel2Response> elementCommentLevel2;
}
