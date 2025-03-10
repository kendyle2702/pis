package lqc.com.pis.service.impl;

import jakarta.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lqc.com.pis.dto.request.chat.MessageCreationRequest;
import lqc.com.pis.dto.response.chat.ConversationCreationResponse;
import lqc.com.pis.dto.response.chat.ConversationResponse;
import lqc.com.pis.dto.response.chat.MessageResponse;
import lqc.com.pis.entity.Conversation;
import lqc.com.pis.entity.Message;
import lqc.com.pis.entity.Post;
import lqc.com.pis.entity.User;
import lqc.com.pis.exception.AppException;
import lqc.com.pis.exception.ErrorCode;
import lqc.com.pis.repository.ConversationRepository;
import lqc.com.pis.repository.MessageRepository;
import lqc.com.pis.service.inter.ChatService;
import lqc.com.pis.service.inter.FileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatServiceImpl implements ChatService {
    ConversationRepository conversationRepository;
    MessageRepository messageRepository;
    EntityManager entityManager;
    FileService fileService;

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

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public MessageResponse sendMessage(MessageCreationRequest messageCreationRequest) throws IOException {

        Conversation conversation = entityManager.getReference(Conversation.class, messageCreationRequest.getConversationId());
        User sender = entityManager.getReference(User.class, messageCreationRequest.getSenderId());

        String file = null;
        if(messageCreationRequest.getFile() != null){
            file = fileService.uploadFile(messageCreationRequest.getFile());
        }

        Message message = Message.builder()
                .conversation(conversation)
                .sender(sender)
                .type(messageCreationRequest.getType())
                .url(file)
                .content(messageCreationRequest.getContent())
                .createdAt(Instant.now())
                .status("NOT SEEN")
                .build();

        Message newMessage = messageRepository.save(message);

        return MessageResponse.builder()
                .userId(newMessage.getSender().getId())
                .avatar(newMessage.getSender().getAvatar())
                .type(newMessage.getType())
                .content(newMessage.getContent())
                .createTime(timeAgo(message.getCreatedAt()))
                .status(newMessage.getStatus())
                .build();
    }

    @Override
    public ConversationCreationResponse createConversation(Long userId, Long receiveId) {

        Conversation conversation1 = conversationRepository.findByUser1IdAndUser2Id(Math.toIntExact(userId), Math.toIntExact(receiveId));
        Conversation conversation2 = conversationRepository.findByUser1IdAndUser2Id(Math.toIntExact(receiveId), Math.toIntExact(userId));

        Conversation newConversation;
        if(conversation1 == null && conversation2 == null){
            User user1 = entityManager.getReference(User.class, userId);
            User user2 = entityManager.getReference(User.class, receiveId);
            Conversation conversation = Conversation.builder()
                    .user1(user1)
                    .user2(user2)
                    .lastTime(Instant.now())
                    .build();

            newConversation = conversationRepository.save(conversation);
        }
        else{
            throw new AppException(ErrorCode.HAVE_CONVERSATION);
        }

        return ConversationCreationResponse.builder().conversationId(newConversation.getId()).build();
    }

    @Override
    public ConversationCreationResponse getConversationId(Long userId, Long receiveId) {
        Conversation conversation1 = conversationRepository.findByUser1IdAndUser2Id(Math.toIntExact(userId), Math.toIntExact(receiveId));
        Conversation conversation2 = conversationRepository.findByUser1IdAndUser2Id(Math.toIntExact(receiveId), Math.toIntExact(userId));

        if(conversation1 == null && conversation2 == null){
            throw new AppException(ErrorCode.HAVE_NOT_CONVERSATION);
        }
        else{
            if(conversation1  == null) return ConversationCreationResponse.builder().conversationId(conversation2.getId()).build();
            else return ConversationCreationResponse.builder().conversationId(conversation1.getId()).build();
        }
    }

    @Override
    public void markAsSeen(Long conversationId, Long senderId) {
        messageRepository.markMessagesAsRead(conversationId, senderId);
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

    @Override
    public String timeAgo(Instant createTime) {
        Instant now = Instant.now();

        LocalDateTime createDateTime = LocalDateTime.ofInstant(createTime, ZoneOffset.UTC);
        LocalDateTime nowDateTime = LocalDateTime.ofInstant(now, ZoneOffset.UTC);

        Duration duration = Duration.between(createDateTime, nowDateTime);
        //fd
        long seconds = duration.getSeconds();
        if (seconds < 60) return seconds + " seconds";
        long minutes = duration.toMinutes();
        if (minutes < 60) return minutes + " minutes";
        long hours = duration.toHours();
        if (hours < 24) return hours + " hours";
        long days = duration.toDays();
        if (days < 7) return days + " days";

        ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");
        LocalDateTime displayDateTime = createDateTime.atZone(ZoneOffset.UTC).withZoneSameInstant(zoneId).toLocalDateTime();

        DateTimeFormatter formatter;
        if (createDateTime.getYear() == nowDateTime.getYear()) {
            formatter = DateTimeFormatter.ofPattern("dd MMM");
        } else {
            formatter = DateTimeFormatter.ofPattern("dd MMM, yyyy");
        }

        return displayDateTime.format(formatter);
    }
}

