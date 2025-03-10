package lqc.com.pis.dto.response.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponse {
    Long postId;
    String type;
    boolean pinned;
    List<ImagePostReponse> urls;

    String content;
    String createAt;
    String mode;
}
