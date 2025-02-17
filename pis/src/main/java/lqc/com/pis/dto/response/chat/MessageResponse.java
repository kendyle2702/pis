package lqc.com.pis.dto.response.chat;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageResponse {
    int userId;
    String avatar;
    String type;
    String content;
    String url;
    String createTime;
    String status;
}
