package lqc.com.pis.dto.response.chat;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConversationResponse {
    Long id;
    String avatar;
    String firstName;
    String lastName;
    String username;
    String lastMsg;
    String lastMsgTime;
    boolean isRead;
}
