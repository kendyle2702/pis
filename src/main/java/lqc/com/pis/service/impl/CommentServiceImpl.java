package lqc.com.pis.service.impl;

import jakarta.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lqc.com.pis.dto.request.comment.CommentCreationRequest;
import lqc.com.pis.dto.response.comment.CommentCreationResponse;
import lqc.com.pis.repository.CommentRepository;
import lqc.com.pis.service.inter.CommentService;
import lqc.com.pis.service.inter.FileService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import lqc.com.pis.entity.User;
import lqc.com.pis.entity.Conversation;
import lqc.com.pis.entity.Friendship;
import lqc.com.pis.entity.FriendshipId;
import lqc.com.pis.entity.ImageComment;
import lqc.com.pis.entity.ImagePost;
import lqc.com.pis.entity.InvalidAccessToken;
import lqc.com.pis.entity.Message;
import lqc.com.pis.entity.Post;
import lqc.com.pis.entity.Reaction;
import lqc.com.pis.entity.Comment;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentServiceImpl implements CommentService {
    EntityManager entityManager;
    FileService fileService;

    CommentRepository commentRepository;

    @Override
    public CommentCreationResponse createComment(CommentCreationRequest commentCreationRequest) throws IOException {
        User user = entityManager.getReference(User.class, commentCreationRequest.getUserId());
        Post post = entityManager.getReference(Post.class, commentCreationRequest.getPostId());

        Comment comment = Comment.builder()
                .post(post)
                .parentComment(commentCreationRequest.getParentCommentId() != -1 ? entityManager.getReference(Comment.class, commentCreationRequest.getParentCommentId()):null)
                .user(user)
                .type(commentCreationRequest.getType())
                .content(commentCreationRequest.getContent())
                .createAt(Instant.now())
                .url(commentCreationRequest.getType().equals("Text")?null:fileService.uploadFile(commentCreationRequest.getFile()))
                .build();



        Comment newComment = commentRepository.save(comment);

        int parentCommentId = -1;
        if (newComment.getParentComment() != null) {
            parentCommentId =  newComment.getParentComment().getId();
        }
        return CommentCreationResponse.builder()
                .commentId(newComment.getId())
                .parentCommentId(parentCommentId)
                .userId(newComment.getUser().getId())
                .type(newComment.getType())
                .content(newComment.getContent())
                .url(newComment.getUrl())
                .createdAt(newComment.getCreateAt())
                .postId(newComment.getPost().getId())
                .build();
    }
}
