package lqc.com.pis.repository;
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
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByConversationId(Integer conversation_id);
    @Query("SELECT m FROM Message m JOIN FETCH m.sender WHERE " +
            "(m.conversation.id = :conversationId AND m.sender.id = :senderId) " +
            "AND m.createdAt >= :since ORDER BY m.createdAt ASC")
    List<Message> findLatestMessages(Long conversationId, Long senderId, Instant since);

    @Modifying
    @Transactional
    @Query("UPDATE Message m SET m.status = 'SEEN' WHERE m.conversation.id = :conversationId AND m.sender.id = :senderId AND m.status = 'NOT SEEN'")
    void markMessagesAsRead(@Param("conversationId") Long conversationId, @Param("senderId") Long senderId);
}
