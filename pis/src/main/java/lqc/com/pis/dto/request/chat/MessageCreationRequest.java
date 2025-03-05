package lqc.com.pis.dto.request.chat;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageCreationRequest {
    int conversationId;
    int senderId;
    String type;
    MultipartFile file;
    String content;
}
