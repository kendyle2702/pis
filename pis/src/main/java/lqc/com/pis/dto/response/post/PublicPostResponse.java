package lqc.com.pis.dto.response.post;

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
public class PublicPostResponse {
    Long id;
    UserPostResponse userPostResponse;
    String caption;
    List<ImagePostReponse> images;
    int likes;
    int comments;
    String createTime;
    String type;
    boolean isLike;
}
