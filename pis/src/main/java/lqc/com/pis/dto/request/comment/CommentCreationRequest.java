package lqc.com.pis.dto.request.comment;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentCreationRequest {
    int postId;
    int parentCommentId;
    int userId;
    String type;
    MultipartFile file;
    String content;
}
