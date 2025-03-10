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
import jakarta.transaction.Transactional;
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
