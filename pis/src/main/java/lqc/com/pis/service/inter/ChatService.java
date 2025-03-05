package lqc.com.pis.service.inter;

import lqc.com.pis.dto.request.chat.MessageCreationRequest;
import lqc.com.pis.dto.response.chat.ConversationCreationResponse;
import lqc.com.pis.dto.response.chat.ConversationResponse;
import lqc.com.pis.dto.response.chat.MessageResponse;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

public interface ChatService {
    List<ConversationResponse> getConversationList(Long userId);
    List<MessageResponse> getMessagesInConversation(Long userId, Long receiveId);
    MessageResponse sendMessage(MessageCreationRequest messageCreationRequest) throws IOException;
    ConversationCreationResponse createConversation(Long userId, Long receiveId);
    ConversationCreationResponse getConversationId(Long userId, Long receiveId);
    void markAsSeen(Long conversationId, Long senderId);

    String timeAgo(Instant createTime);
}
