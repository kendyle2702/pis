package lqc.com.pis.dto.request.post;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentReactionRequest {
    Long userId;
    Long commentId;
}
