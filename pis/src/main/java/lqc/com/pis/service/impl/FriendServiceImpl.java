package lqc.com.pis.service.impl;

import jakarta.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lqc.com.pis.dto.request.friend.FriendActionRequest;
import lqc.com.pis.dto.request.friend.FriendProfileRequest;
import lqc.com.pis.dto.response.post.PostResponse;
import lqc.com.pis.dto.response.user.UserResponse;
import lqc.com.pis.entity.Friendship;
import lqc.com.pis.entity.FriendshipId;
import lqc.com.pis.entity.User;
import lqc.com.pis.exception.AppException;
import lqc.com.pis.exception.ErrorCode;
import lqc.com.pis.mapper.UserMapper;
import lqc.com.pis.repository.FriendShipRepository;
import lqc.com.pis.repository.UserRepository;
import lqc.com.pis.service.inter.FriendService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FriendServiceImpl implements FriendService {

    FriendShipRepository friendShipRepository;
    UserRepository userRepository;
    UserMapper userMapper;
    EntityManager entityManager;

    @Override
    public List<UserResponse> getFriendListByUserId(Long userId) {
        List<Friendship> filteredFriendships = friendShipRepository.findByUserId(userId).stream()
                .filter(f -> "FRIEND".equals(f.getId().getFriendType()) && Boolean.TRUE.equals(f.getIsFriend()) && Boolean.FALSE.equals(f.getIsBlock()))
                .toList();
        return filteredFriendships.stream().map(
                friendship -> {
                    UserResponse userResponse = userMapper.toUserResponse(friendship.getFriend());
                    userResponse.setIsFollowing(null);
                    userResponse.setIsFriend(null);
                    userResponse.setIsSendRequest(null);
                    userResponse.setIsBlock(null);
                    return userResponse;
                }).toList();
    }

    @Override
    public UserResponse getProfileOfUser(FriendProfileRequest request) {

        User user = userRepository.findById((long) request.getFriendId()).orElseThrow(() ->new AppException(ErrorCode.USER_NOT_EXISTED));

        UserResponse userResponse = userMapper.toUserResponse(user);

        userResponse.setIsFollowing(friendShipRepository.existsFriendship((long) request.getFriendId(), (long) request.getUserId(), "FOLLOW") > 0);
        userResponse.setIsFriend(friendShipRepository.isFriend((long) request.getUserId(), (long) request.getFriendId(), "FRIEND") >0);
        userResponse.setIsSendRequest(friendShipRepository.isSendRequestFriend((long) request.getUserId(), (long) request.getFriendId(), "FRIEND") >0);
        userResponse.setIsBlock(friendShipRepository.isBlockFriend((long) request.getUserId(), (long) request.getFriendId(), "FRIEND") >0);
        return userResponse;
    }

    @Override
    public void followFriend(FriendActionRequest request) {
        FriendshipId friendshipId = new FriendshipId(request.getFriendId(), request.getUserId(), "FOLLOW");
        if (friendShipRepository.existsById(friendshipId)) {
            throw new AppException(ErrorCode.HAVE_FOLLOWING);
        }

        User user = entityManager.getReference(User.class, request.getUserId());
        User friend = entityManager.getReference(User.class, request.getFriendId());

        Friendship friendship = new Friendship();
        friendship.setId(friendshipId);
        friendship.setUser(friend);
        friendship.setFriend(user);
        friendship.setIsFriend(false);
        friendship.setIsBlock(false);

        friendShipRepository.save(friendship);
    }

    @Override
    public void unfollowFriend(FriendActionRequest request) {
        FriendshipId friendshipId = new FriendshipId(request.getFriendId(), request.getUserId(), "FOLLOW");

        Friendship friendship = friendShipRepository.findById(friendshipId)
                .orElseThrow(() -> new AppException(ErrorCode.HAVE_NOT_FOLLOWING));

        friendShipRepository.delete(friendship);
    }

    @Override
    public void friendRequest(FriendActionRequest request) {
        FriendshipId friendshipId1 = new FriendshipId(request.getUserId(), request.getFriendId(), "FRIEND");
        FriendshipId friendshipId2 = new FriendshipId(request.getFriendId(), request.getUserId(), "FRIEND");

        boolean isBlock =
                friendShipRepository.isBlockFriend(
                        (long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0 ||
                        friendShipRepository.isBlockFriend((long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0;

        boolean isRequestFriend =
                friendShipRepository.isSendRequestFriend(
                        (long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0 ||
                        friendShipRepository.isSendRequestFriend((long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0;

        boolean isFriend =
                friendShipRepository.isFriend(
                        (long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0 ||
                        friendShipRepository.isFriend((long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0;

        if(isBlock){
            throw new AppException(ErrorCode.HAVE_BLOCK);
        }

        if(isRequestFriend){
            throw new AppException(ErrorCode.HAVE_REQUEST_FRIEND);
        }

        if(isFriend){
            throw new AppException(ErrorCode.HAVE_FRIEND);
        }


        User user1 = entityManager.getReference(User.class, request.getUserId());
        User user2 = entityManager.getReference(User.class, request.getFriendId());

        User friend1 = entityManager.getReference(User.class, request.getFriendId());
        User friend2 = entityManager.getReference(User.class, request.getUserId());

        Friendship friendship1 = new Friendship();
        friendship1.setId(friendshipId1);
        friendship1.setUser(user1);
        friendship1.setFriend(friend1);
        friendship1.setIsFriend(false);
        friendship1.setIsBlock(false);

        Friendship friendship2 = new Friendship();
        friendship2.setId(friendshipId2);
        friendship2.setUser(user2);
        friendship2.setFriend(friend2);
        friendship2.setIsFriend(false);
        friendship2.setIsBlock(false);

        friendShipRepository.save(friendship1);
        friendShipRepository.save(friendship2);

    }

    @Override
    public void cancelFriendRequest(FriendActionRequest request) {
        FriendshipId friendshipId1 = new FriendshipId(request.getUserId(), request.getFriendId(), "FRIEND");
        FriendshipId friendshipId2 = new FriendshipId(request.getFriendId(), request.getUserId(), "FRIEND");


        boolean isRequestFriend =
                friendShipRepository.isSendRequestFriend(
                        (long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0 ||
                        friendShipRepository.isSendRequestFriend((long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0;

        boolean isFriend =
                friendShipRepository.isFriend(
                        (long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0 ||
                        friendShipRepository.isFriend((long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0;

        boolean isBlock =
                friendShipRepository.isBlockFriend(
                        (long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0 ||
                        friendShipRepository.isBlockFriend((long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0;


        if(isBlock){
            throw new AppException(ErrorCode.HAVE_BLOCK);
        }

        if(isFriend){
            throw new AppException(ErrorCode.HAVE_FRIEND);
        }

        if (!isRequestFriend){
            throw new AppException(ErrorCode.HAVE_NOT_REQUEST_FRIEND);
        }


        Friendship friendship1 = friendShipRepository.findById(friendshipId1)
                .orElseThrow(() -> new AppException(ErrorCode.HAVE_NOT_REQUEST_FRIEND));
        Friendship friendship2 = friendShipRepository.findById(friendshipId2)
                .orElseThrow(() -> new AppException(ErrorCode.HAVE_NOT_REQUEST_FRIEND));


        friendShipRepository.delete(friendship1);
        friendShipRepository.delete(friendship2);
    }

    @Override
    public void acceptFriend(FriendActionRequest request) {

        FriendshipId friendshipId1 = new FriendshipId(request.getUserId(), request.getFriendId(), "FRIEND");
        FriendshipId friendshipId2 = new FriendshipId(request.getFriendId(), request.getUserId(), "FRIEND");


        boolean isRequestFriend =
                friendShipRepository.isSendRequestFriend(
                        (long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0 ||
                        friendShipRepository.isSendRequestFriend((long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0;

        boolean isFriend =
                friendShipRepository.isFriend(
                        (long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0 ||
                        friendShipRepository.isFriend((long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0;

        boolean isBlock =
                friendShipRepository.isBlockFriend(
                        (long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0 ||
                        friendShipRepository.isBlockFriend((long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0;


        if(isBlock){
            throw new AppException(ErrorCode.HAVE_BLOCK);
        }

        if(isFriend){
            throw new AppException(ErrorCode.HAVE_FRIEND);
        }

        if (!isRequestFriend){
            throw new AppException(ErrorCode.HAVE_NOT_REQUEST_FRIEND);
        }

        Friendship friendship1 = friendShipRepository.findById(friendshipId1)
                .orElseThrow(() -> new AppException(ErrorCode.HAVE_NOT_REQUEST_FRIEND));
        Friendship friendship2 = friendShipRepository.findById(friendshipId2)
                .orElseThrow(() -> new AppException(ErrorCode.HAVE_NOT_REQUEST_FRIEND));

        friendship1.setIsFriend(true);
        friendship2.setIsFriend(true);

        friendShipRepository.save(friendship1);
        friendShipRepository.save(friendship2);
    }

    @Override
    public void rejectFriend(FriendActionRequest request) {
        FriendshipId friendshipId1 = new FriendshipId(request.getUserId(), request.getFriendId(), "FRIEND");
        FriendshipId friendshipId2 = new FriendshipId(request.getFriendId(), request.getUserId(), "FRIEND");

        boolean isRequestFriend =
                friendShipRepository.isSendRequestFriend(
                        (long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0 ||
                        friendShipRepository.isSendRequestFriend((long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0;

        boolean isFriend =
                friendShipRepository.isFriend(
                        (long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0 ||
                        friendShipRepository.isFriend((long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0;

        boolean isBlock =
                friendShipRepository.isBlockFriend(
                        (long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0 ||
                        friendShipRepository.isBlockFriend((long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0;


        if(isBlock){
            throw new AppException(ErrorCode.HAVE_BLOCK);
        }

        if(isFriend){
            throw new AppException(ErrorCode.HAVE_FRIEND);
        }

        if (!isRequestFriend){
            throw new AppException(ErrorCode.HAVE_NOT_REQUEST_FRIEND);
        }

        Friendship friendship1 = friendShipRepository.findById(friendshipId1)
                .orElseThrow(() -> new AppException(ErrorCode.HAVE_NOT_REQUEST_FRIEND));
        Friendship friendship2 = friendShipRepository.findById(friendshipId2)
                .orElseThrow(() -> new AppException(ErrorCode.HAVE_NOT_REQUEST_FRIEND));


        friendShipRepository.delete(friendship1);
        friendShipRepository.delete(friendship2);
    }

    @Override
    public void unfriend(FriendActionRequest request) {
        FriendshipId friendshipId1 = new FriendshipId(request.getUserId(), request.getFriendId(), "FRIEND");
        FriendshipId friendshipId2 = new FriendshipId(request.getFriendId(), request.getUserId(), "FRIEND");

        boolean isFriend =
                friendShipRepository.isFriend(
                        (long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0 ||
                        friendShipRepository.isFriend((long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0;

        boolean isBlock =
                friendShipRepository.isBlockFriend(
                        (long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0 ||
                        friendShipRepository.isBlockFriend((long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0;


        if(isBlock){
            throw new AppException(ErrorCode.HAVE_BLOCK);
        }


        if(!isFriend){
            throw new AppException(ErrorCode.HAVE_NOT_FRIEND);
        }

        Friendship friendship1 = friendShipRepository.findById(friendshipId1)
                .orElseThrow(() -> new AppException(ErrorCode.HAVE_NOT_REQUEST_FRIEND));
        Friendship friendship2 = friendShipRepository.findById(friendshipId2)
                .orElseThrow(() -> new AppException(ErrorCode.HAVE_NOT_REQUEST_FRIEND));

        friendShipRepository.delete(friendship1);
        friendShipRepository.delete(friendship2);
    }

    @Override
    public void blockFriend(FriendActionRequest request) {
        FriendshipId friendshipId1 = new FriendshipId(request.getUserId(), request.getFriendId(), "FRIEND");
        FriendshipId friendshipId2 = new FriendshipId(request.getFriendId(), request.getUserId(), "FRIEND");

        boolean isBlock =
                friendShipRepository.isBlockFriend(
                        (long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0 ||
                        friendShipRepository.isBlockFriend((long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0;


        if(isBlock){
            throw new AppException(ErrorCode.HAVE_BLOCK);
        }

        boolean isRequestFriend =
                friendShipRepository.isSendRequestFriend(
                        (long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0 ||
                        friendShipRepository.isSendRequestFriend((long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0;

        boolean isFriend =
                friendShipRepository.isFriend(
                        (long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0 ||
                        friendShipRepository.isFriend((long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0;



        if(isFriend){
            Friendship friendship1 = friendShipRepository.findById(friendshipId1)
                    .orElseThrow(() -> new AppException(ErrorCode.HAVE_NOT_REQUEST_FRIEND));
            Friendship friendship2 = friendShipRepository.findById(friendshipId2)
                    .orElseThrow(() -> new AppException(ErrorCode.HAVE_NOT_REQUEST_FRIEND));

            friendship1.setIsBlock(true);
            friendship2.setIsBlock(true);

            friendShipRepository.save(friendship1);
            friendShipRepository.save(friendship2);
        }
        else{
            if (isRequestFriend){
                Friendship friendship1 = friendShipRepository.findById(friendshipId1)
                        .orElseThrow(() -> new AppException(ErrorCode.HAVE_NOT_REQUEST_FRIEND));
                Friendship friendship2 = friendShipRepository.findById(friendshipId2)
                        .orElseThrow(() -> new AppException(ErrorCode.HAVE_NOT_REQUEST_FRIEND));

                friendship1.setIsBlock(true);
                friendship2.setIsBlock(true);

                friendShipRepository.save(friendship1);
                friendShipRepository.save(friendship2);
            }
            else{
                User user1 = entityManager.getReference(User.class, request.getUserId());
                User user2 = entityManager.getReference(User.class, request.getFriendId());

                User friend1 = entityManager.getReference(User.class, request.getFriendId());
                User friend2 = entityManager.getReference(User.class, request.getUserId());

                Friendship friendship1 = new Friendship();
                friendship1.setId(friendshipId1);
                friendship1.setUser(user1);
                friendship1.setFriend(friend1);
                friendship1.setIsFriend(false);
                friendship1.setIsBlock(true);

                Friendship friendship2 = new Friendship();
                friendship2.setId(friendshipId2);
                friendship2.setUser(user2);
                friendship2.setFriend(friend2);
                friendship2.setIsFriend(false);
                friendship2.setIsBlock(true);

                friendShipRepository.save(friendship1);
                friendShipRepository.save(friendship2);
            }
        }
    }

    @Override
    public void unblockFriend(FriendActionRequest request) {
        boolean isBlock =
                friendShipRepository.isBlockFriend(
                        (long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0 ||
                        friendShipRepository.isBlockFriend((long) request.getFriendId(), (long) request.getUserId(),"FRIEND") > 0;

        if(!isBlock){
            throw new AppException(ErrorCode.HAVE_NOT_BLOCK);
        }

        FriendshipId friendshipId1 = new FriendshipId(request.getUserId(), request.getFriendId(), "FRIEND");
        FriendshipId friendshipId2 = new FriendshipId(request.getFriendId(), request.getUserId(), "FRIEND");

        Friendship friendship1 = friendShipRepository.findById(friendshipId1)
                .orElseThrow(() -> new AppException(ErrorCode.HAVE_NOT_REQUEST_FRIEND));
        Friendship friendship2 = friendShipRepository.findById(friendshipId2)
                .orElseThrow(() -> new AppException(ErrorCode.HAVE_NOT_REQUEST_FRIEND));

        friendShipRepository.delete(friendship1);
        friendShipRepository.delete(friendship2);
    }
}
