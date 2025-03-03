package lqc.com.pis.repository;

import lqc.com.pis.entity.Friendship;
import lqc.com.pis.entity.FriendshipId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendShipRepository extends JpaRepository<Friendship, FriendshipId> {
    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM friendship f WHERE f.user_id = :userId AND f.friend_id = :friendId AND f.friend_type = :friendType", nativeQuery = true)
    int existsFriendship(@Param("userId") Long userId, @Param("friendId") Long friendId, @Param("friendType") String friendType);

    List<Friendship> findByUserId(Long userId);



    @Query(value = "SELECT COUNT(*) FROM friendship WHERE user_id = :userId AND friend_type = :friendType", nativeQuery = true)
    Long countByUserIdAndFriendType(@Param("userId") Long userId, @Param("friendType") String friendType);

    @Query(value = "SELECT COUNT(*) FROM friendship WHERE friend_id = :friendId AND friend_type = :friendType", nativeQuery = true)
    Long countByFriendIdAndFriendType(@Param("friendId") Long friendId, @Param("friendType") String friendType);

    @Query(value = "SELECT user_id FROM friendship WHERE friend_id = :friendId AND friend_type = :friendType", nativeQuery = true)
    List<Integer> findUserIdsByFriendIdAndFriendType(@Param("friendId") Integer friendId, @Param("friendType") String friendType);

    @Query(value = "SELECT friend_id FROM friendship WHERE user_id = :userId AND friend_type = :friendType", nativeQuery = true)
    List<Integer> findFriendIdsByUserIdAndFriendType(@Param("userId") Integer userId, @Param("friendType") String friendType);
}

