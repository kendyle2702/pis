package lqc.com.pis.repository;

import lqc.com.pis.entity.ImagePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagePostRepository extends JpaRepository<ImagePost, Long> {
    List<ImagePost> findAllByPostId(Long postId);

}
