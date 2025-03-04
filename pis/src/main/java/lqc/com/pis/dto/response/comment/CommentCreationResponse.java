package lqc.com.pis.dto.response.comment;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
    public class CommentCreationResponse {
    int commentId;
    int postId;
    int parentCommentId;
    int userId;
    String type;
    String url;
    String content;
    Instant createdAt;
}
