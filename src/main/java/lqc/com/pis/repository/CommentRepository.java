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
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Long countByPostId(Long postId);
    Long countByParentCommentId(Long parentCommentId);
    List<Comment> findByPostIdAndParentCommentId(Long postId, Long parentCommentId);
    List<Comment> findByParentCommentId(Long parentCommentId);
}
