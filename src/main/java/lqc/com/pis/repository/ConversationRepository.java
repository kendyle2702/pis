package lqc.com.pis.repository;

import lqc.com.pis.entity.Conversation;
import lqc.com.pis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    List<Conversation> findByUser1IdOrUser2Id(Long user1Id, Long user2Id);
    Conversation findByUser1IdAndUser2Id(Integer user1_id, Integer user2_id);
}
