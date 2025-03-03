package lqc.com.pis.dto.request.post;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostCreationRequest {
    int userId;
    String type;
    String content;
    String mode;
    MultipartFile[] files;
}
