package lqc.com.pis.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lqc.com.pis.dto.response.ApiResponse;
import lqc.com.pis.dto.response.chat.ConversationResponse;
import lqc.com.pis.dto.response.chat.MessageResponse;
import lqc.com.pis.dto.response.post.PostResponse;
import lqc.com.pis.entity.Conversation;
import lqc.com.pis.entity.Message;
import lqc.com.pis.service.inter.ChatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/conversations")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatController {

    ChatService chatService;
    @GetMapping("/{userId}")
    ResponseEntity<ApiResponse<List<ConversationResponse>>> getConversations(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<ConversationResponse>>builder()
                        .code(2001)
                        .data(chatService.getConversationList(userId)).build()
        );
    }

    @GetMapping("/messages/{userId}/{receiveId}")
    ResponseEntity<ApiResponse<List<MessageResponse>>> getMessagesInConversation(@PathVariable("userId") Long userId, @PathVariable("receiveId") Long receiveId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<MessageResponse>>builder()
                        .code(2001)
                            .data(chatService.getMessagesInConversation(userId,receiveId)).build()
        );
    }
}
