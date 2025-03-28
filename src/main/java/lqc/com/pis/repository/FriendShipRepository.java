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
    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM friendship f WHERE f.user_id = :userId AND f.friend_id = :friendId AND f.friend_type = :friendType AND f.is_block = FALSE", nativeQuery = true)
    int existsFriendship(@Param("userId") Long userId, @Param("friendId") Long friendId, @Param("friendType") String friendType);


    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM friendship f WHERE f.user_id = :userId AND f.friend_id = :friendId AND f.friend_type = :friendType AND f.is_friend = TRUE AND f.is_block = FALSE", nativeQuery = true)
    int isFriend(@Param("userId") Long userId, @Param("friendId") Long friendId, @Param("friendType") String friendType);


    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM friendship f WHERE f.user_id = :userId AND f.friend_id = :friendId AND f.friend_type = :friendType AND f.is_friend = FALSE AND f.is_block = FALSE", nativeQuery = true)
    int isSendRequestFriend(@Param("userId") Long userId, @Param("friendId") Long friendId, @Param("friendType") String friendType);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM friendship f WHERE f.user_id = :userId AND f.friend_id = :friendId AND f.friend_type = :friendType AND f.is_block=TRUE", nativeQuery = true)
    int isBlockFriend(@Param("userId") Long userId, @Param("friendId") Long friendId, @Param("friendType") String friendType);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM friendship f WHERE f.user_id = :userId AND f.friend_id = :friendId AND f.friend_type = :friendType AND f.is_block=TRUE AND f.is_friend = FALSE", nativeQuery = true)
    int isBlockToFriend(@Param("userId") Long userId, @Param("friendId") Long friendId, @Param("friendType") String friendType);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM friendship f WHERE f.user_id = :userId AND f.friend_id = :friendId AND f.friend_type = :friendType AND f.is_block=TRUE AND f.is_friend = TRUE", nativeQuery = true)
    int isBlocked(@Param("userId") Long userId, @Param("friendId") Long friendId, @Param("friendType") String friendType);



    List<Friendship> findByUserId(Long userId);
    List<Friendship> findByFriendId(Long friendId);

    @Query(value = "SELECT COUNT(*) FROM friendship WHERE user_id = :userId AND friend_type = :friendType", nativeQuery = true)
    Long countByUserIdAndFriendType(@Param("userId") Long userId, @Param("friendType") String friendType);

    @Query(value = "SELECT COUNT(*) FROM friendship WHERE friend_id = :friendId AND friend_type = :friendType", nativeQuery = true)
    Long countByFriendIdAndFriendType(@Param("friendId") Long friendId, @Param("friendType") String friendType);

    @Query(value = "SELECT user_id FROM friendship WHERE friend_id = :friendId AND friend_type = :friendType", nativeQuery = true)
    List<Integer> findUserIdsByFriendIdAndFriendType(@Param("friendId") Integer friendId, @Param("friendType") String friendType);

    @Query(value = "SELECT friend_id FROM friendship WHERE user_id = :userId AND friend_type = :friendType", nativeQuery = true)
    List<Integer> findFriendIdsByUserIdAndFriendType(@Param("userId") Integer userId, @Param("friendType") String friendType);

}

