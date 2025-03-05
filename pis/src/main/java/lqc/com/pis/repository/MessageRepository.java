package lqc.com.pis.repository;

import lqc.com.pis.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByConversationId(Integer conversation_id);
    @Query("SELECT m FROM Message m JOIN FETCH m.sender WHERE " +
            "(m.conversation.id = :conversationId AND m.sender.id = :senderId) " +
            "AND m.createdAt >= :since ORDER BY m.createdAt ASC")
    List<Message> findLatestMessages(Long conversationId, Long senderId, Instant since);
}
