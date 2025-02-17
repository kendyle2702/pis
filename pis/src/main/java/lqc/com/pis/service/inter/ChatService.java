package lqc.com.pis.service.inter;

import lqc.com.pis.dto.response.chat.ConversationResponse;
import lqc.com.pis.dto.response.chat.MessageResponse;
import lqc.com.pis.entity.Conversation;
import lqc.com.pis.entity.Message;

import java.util.List;

public interface ChatService {
    List<ConversationResponse> getConversationList(Long userId);
    List<MessageResponse> getMessagesInConversation(Long userId, Long receiveId);
}
