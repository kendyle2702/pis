package lqc.com.pis.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lqc.com.pis.dto.response.chat.ConversationResponse;
import lqc.com.pis.dto.response.chat.MessageResponse;
import lqc.com.pis.entity.Conversation;
import lqc.com.pis.entity.Message;
import lqc.com.pis.entity.Post;
import lqc.com.pis.exception.AppException;
import lqc.com.pis.exception.ErrorCode;
import lqc.com.pis.repository.ConversationRepository;
import lqc.com.pis.repository.MessageRepository;
import lqc.com.pis.service.inter.ChatService;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatServiceImpl implements ChatService {
    ConversationRepository conversationRepository;
    MessageRepository messageRepository;

    @Override
    public List<ConversationResponse> getConversationList(Long userId) {

        List<Conversation> conversationList = conversationRepository.findByUser1IdOrUser2Id(userId,userId);

        return conversationList.stream().map(conversation -> ConversationResponse
                .builder()
                .id(Long.valueOf(Long.parseLong(String.valueOf(conversation.getUser1().getId())) != userId ? conversation.getUser1().getId():conversation.getUser2().getId()))
                .avatar(Long.parseLong(String.valueOf(conversation.getUser1().getId())) != userId ? conversation.getUser1().getAvatar():conversation.getUser2().getAvatar())
                .firstName(Long.parseLong(String.valueOf(conversation.getUser1().getId())) != userId ? conversation.getUser1().getFirstName():conversation.getUser2().getFirstName())
                .lastName(Long.parseLong(String.valueOf(conversation.getUser1().getId())) != userId ? conversation.getUser1().getLastName():conversation.getUser2().getLastName())
                .username(Long.parseLong(String.valueOf(conversation.getUser1().getId())) != userId ? conversation.getUser1().getUsername():conversation.getUser2().getUsername())
                .lastMsg(getLastMsg(conversation, userId))
                .lastMsgTime(getLastMsgTime(conversation))
                .isRead(isReadMessage(conversation,userId))
                .build()).toList();
    }

    @Override
    public List<MessageResponse> getMessagesInConversation(Long userId, Long receiveId) {
        Conversation conversation1 = conversationRepository.findByUser1IdAndUser2Id(Math.toIntExact(userId), Math.toIntExact(receiveId));
        Conversation conversation2 = conversationRepository.findByUser1IdAndUser2Id(Math.toIntExact(receiveId), Math.toIntExact(userId));
        if (conversation1 == null) {
            if (conversation2 != null) {
                List<Message> messageList = messageRepository.findByConversationId(conversation2.getId());
                List<Message> sortedMessageList = messageList.stream()
                        .sorted(Comparator.comparing(Message::getCreatedAt))
                        .toList();

                return sortedMessageList.stream().map(message -> MessageResponse
                        .builder()
                        .userId(message.getSender().getId())
                        .avatar(message.getSender().getAvatar())
                        .content(message.getContent())
                        .type(message.getType())
                        .url(message.getUrl())
                        .createTime(timeAgo(message.getCreatedAt()))
                        .status(message.getStatus())
                        .build()).toList();
            }
            else{
                throw new AppException(ErrorCode.NOT_HAVE_CONVERSATION);
            }
        }
        else{
            List<Message> messageList = messageRepository.findByConversationId(conversation1.getId());
            List<Message> sortedMessageList = messageList.stream()
                    .sorted(Comparator.comparing(Message::getCreatedAt))
                    .toList();

            return sortedMessageList.stream().map(message -> MessageResponse
                    .builder()
                    .userId(message.getSender().getId())
                    .avatar(message.getSender().getAvatar())
                    .content(message.getContent())
                    .type(message.getType())
                    .url(message.getUrl())
                    .createTime(timeAgo(message.getCreatedAt()))
                    .status(message.getStatus())
                    .build()).toList();
        }
    }

    private String getLastMsg(Conversation conversation, Long userId) {
        List<Message> messages = messageRepository.findByConversationId(conversation.getId());
        List<Message> sortedMessages = messages.stream()
                .sorted(Comparator.comparing(Message::getCreatedAt).reversed())
                .toList();
        String type = sortedMessages.get(0).getType();

        if(type.equals("Text")){
            return sortedMessages.get(0).getContent();
        }
        else if(type.equals("Image")){
            if(Long.parseLong(String.valueOf(sortedMessages.get(0).getSender().getId())) == userId){
                return "You send image message";
            }
            else{
                return "Sent to image message";
            }
        }
        else if(type.equals("Voice")){
            if(Long.parseLong(String.valueOf(sortedMessages.get(0).getSender().getId())) == userId){
                return "You send voice message";
            }
            else{
                return "Sent to voice message";
            }
        }
        else if(type.equals("Reply")){
            if(Long.parseLong(String.valueOf(sortedMessages.get(0).getSender().getId())) == userId){
                return "You replied friend's post";
            }
            else{
                return "Replied to your post";
            }
        }
        return "";
    }

    private String getLastMsgTime(Conversation conversation ){
        List<Message> messages = messageRepository.findByConversationId(conversation.getId());
        List<Message> sortedMessages = messages.stream()
                .sorted(Comparator.comparing(Message::getCreatedAt).reversed())
                .toList();
        return timeAgo(sortedMessages.get(0).getCreatedAt());
    }

    private boolean isReadMessage(Conversation conversation, Long userId) {
        List<Message> messages = messageRepository.findByConversationId(conversation.getId());
        List<Message> sortedMessages = messages.stream()
                .sorted(Comparator.comparing(Message::getCreatedAt).reversed())
                .toList();
        if(sortedMessages.get(0).getStatus().equals("SEEN") ){
            return true;
        }
        else{
            return Long.parseLong(String.valueOf(sortedMessages.get(0).getSender().getId())) == userId;
        }
    }

    private String timeAgo(Instant createTime) {
        ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");

        ZonedDateTime nowZoned = ZonedDateTime.now(zoneId);

        LocalDateTime createDateTime = createTime.atOffset(ZoneOffset.UTC).toLocalDateTime();
        LocalDateTime nowDateTime = nowZoned.toLocalDateTime();

        Duration duration = Duration.between(createDateTime, nowDateTime);

        long seconds = duration.getSeconds();
        if (seconds < 60) return seconds + " seconds";
        long minutes = duration.toMinutes();
        if (minutes < 60) return minutes + " minutes";
        long hours = duration.toHours();
        if (hours < 24) return hours + " hours";
        long days = duration.toDays();
        if (days < 7) return days + " days";

        DateTimeFormatter formatter;
        if (createDateTime.getYear() == nowDateTime.getYear()) {
            formatter = DateTimeFormatter.ofPattern("dd MMM");
        } else {
            formatter = DateTimeFormatter.ofPattern("dd MMM, yyyy");
        }

        return createDateTime.format(formatter);
    }
}
