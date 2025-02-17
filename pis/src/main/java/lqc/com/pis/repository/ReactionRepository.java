package lqc.com.pis.repository;

import jakarta.transaction.Transactional;
import lqc.com.pis.entity.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    Long countByPostId(Long postId);
    Long countByCommentId(Long commentId);
    boolean existsByPostIdAndUserId(Long postId, Long userId);
    boolean existsByCommentIdAndUserId(Long commentId, Long userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Reaction r WHERE r.post.id = :postId AND r.user.id = :userId")
    void deleteByPostIdAndUserId(Long postId, Long userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Reaction r WHERE r.comment.id = :commentId AND r.user.id = :userId")
    void deleteByCommentIdAndUserId(Long commentId, Long userId);
}
