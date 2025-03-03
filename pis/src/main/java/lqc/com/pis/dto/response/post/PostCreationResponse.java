package lqc.com.pis.dto.response.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostCreationResponse {
    int postId;
    int userId;
    String type;
    String content;
    String mode;
    Instant createAt;
    boolean pinned;
    List<String> urls;
}
