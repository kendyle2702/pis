package lqc.com.pis.controller;

import jakarta.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lqc.com.pis.dto.request.chat.MessageCreationRequest;
import lqc.com.pis.dto.response.ApiResponse;
import lqc.com.pis.dto.response.chat.ConversationCreationResponse;
import lqc.com.pis.dto.response.chat.ConversationResponse;
import lqc.com.pis.dto.response.chat.MessageResponse;
import lqc.com.pis.dto.response.post.PostResponse;
import lqc.com.pis.entity.Conversation;
import lqc.com.pis.entity.Message;
import lqc.com.pis.repository.MessageRepository;
import lqc.com.pis.service.inter.ChatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;



import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

@RestController
@RequestMapping("/conversations")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatController {

    ChatService chatService;
    MessageRepository messageRepository;
    EntityManager entityManager;



    @GetMapping("/{userId}")
    ResponseEntity<ApiResponse<List<ConversationResponse>>> getConversations(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<ConversationResponse>>builder()
                        .code(2000)
                        .data(chatService.getConversationList(userId)).build()
        );
    }

    @GetMapping("/messages/{userId}/{receiveId}")
    ResponseEntity<ApiResponse<List<MessageResponse>>> getMessagesInConversation(@PathVariable("userId") Long userId, @PathVariable("receiveId") Long receiveId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<MessageResponse>>builder()
                        .code(2000)
                            .data(chatService.getMessagesInConversation(userId,receiveId)).build()
        );
    }

    @PostMapping("/chat/send")
    ResponseEntity<ApiResponse<MessageResponse>> sendMessage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("senderId") String senderId,
            @RequestParam("conversationId") String conversationId,
            @RequestParam("content") String content,
            @RequestParam("type") String type
    ) throws IOException {

        if(file == null || file.isEmpty()){
            file = null;
        }

        MessageCreationRequest messageCreationRequest = MessageCreationRequest.builder()
                .conversationId(Integer.parseInt(conversationId))
                .senderId(Integer.parseInt(senderId))
                .file(file)
                .content(content)
                .type(type)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<MessageResponse>builder()
                        .code(2001)
                        .data(chatService.sendMessage(messageCreationRequest)).build()
        );
    }

    @PostMapping("/create/{userId}/{receiveId}")
    ResponseEntity<ApiResponse<ConversationCreationResponse>> createConversation(@PathVariable("userId") Long userId, @PathVariable("receiveId") Long receiveId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<ConversationCreationResponse>builder()
                        .code(2000)
                        .data(chatService.createConversation(userId,receiveId)).build()
        );
    }

    @GetMapping("/getId/{userId}/{receiveId}")
    ResponseEntity<ApiResponse<ConversationCreationResponse>> getConversationId(@PathVariable("userId") Long userId, @PathVariable("receiveId") Long receiveId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<ConversationCreationResponse>builder()
                        .code(2000)
                        .data(chatService.getConversationId(userId,receiveId)).build()
        );
    }

    @GetMapping("/{conversationId}/{senderId}/wait")
    public DeferredResult<List<MessageResponse>> waitForNewMessages(@PathVariable("conversationId") Long conversationId, @PathVariable("senderId") Long senderId) {
        DeferredResult<List<MessageResponse>> output = new DeferredResult<>(5000L, List.of());

        CompletableFuture.runAsync(() -> {
            Instant since = Instant.now().minusSeconds(5);
            log.info("since {}", since);
            int maxAttempts = 10;
            int attempts = 0;

            while (attempts < maxAttempts) {
                if (output.isSetOrExpired()) {
                    return;
                }

                entityManager.clear();

                List<Message> newMessages = messageRepository.findLatestMessages(conversationId, senderId, since);
                if (!newMessages.isEmpty()) {
                    log.info("Have message");
                    try {
                        output.setResult(newMessages.stream().map(message -> MessageResponse.builder()
                                .type(message.getType())
                                .userId(message.getSender().getId())
                                .avatar(message.getSender().getAvatar())
                                .content(message.getContent())
                                .createTime(chatService.timeAgo(message.getCreatedAt()))
                                .status(message.getStatus())
                                .build()).toList());

                        log.info("Result set successfully");
                    }
                    catch (Exception e) {
                        log.error("❌ Failed to set result: {}", e.getMessage(), e);
                    }
                    return;
                }

                attempts++;
                try { Thread.sleep(500); } catch (Exception e) {log.error("❌ Failed to set result: {}", e.getMessage(), e); }
            }
        });

        return output;
    }

    @PostMapping("/{conversationId}/{senderId}/seen")
    ResponseEntity<ApiResponse<Void>> markAsSeen(@PathVariable("conversationId") Long conversationId, @PathVariable("senderId") Long senderId) {
        chatService.markAsSeen(conversationId,senderId);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Void>builder()
                        .code(2000)
                        .message("Seen all messages!")
                        .build()
        );
    }
}
