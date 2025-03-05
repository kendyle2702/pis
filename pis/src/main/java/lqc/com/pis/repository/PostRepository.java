package lqc.com.pis.repository;

import lqc.com.pis.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserIdAndMode(Integer userId, String mode);
    List<Post> findByMode(String mode);

    @Query("SELECT p FROM Post p WHERE p.user.id IN :userIds AND p.mode = 'Private' ORDER BY p.createAt DESC")
    List<Post> findByUserIds(@Param("userIds") List<Integer> userIds);
}

