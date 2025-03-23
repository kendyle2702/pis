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
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserIdAndMode(Integer userId, String mode);
    List<Post> findByMode(String mode);

    @Query("SELECT p FROM Post p WHERE (p.user.id IN :userIds AND p.mode = 'Private') OR (p.user.id = :userId AND p.mode = 'Private') ORDER BY p.createAt DESC")
    List<Post> findByUserIds(@Param("userIds") List<Integer> userIds,@Param("userId") Long userId);
}

