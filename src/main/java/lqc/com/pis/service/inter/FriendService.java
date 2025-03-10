package lqc.com.pis.service.inter;

import lqc.com.pis.dto.request.friend.FriendActionRequest;
import lqc.com.pis.dto.request.friend.FriendProfileRequest;
import lqc.com.pis.dto.response.user.UserResponse;

import java.util.List;

public interface FriendService {
    List<UserResponse> getFriendListByUserId(Long userId);
    UserResponse getProfileOfUser(FriendProfileRequest request);

    void followFriend(FriendActionRequest request);
    void unfollowFriend(FriendActionRequest request);
    void friendRequest(FriendActionRequest request);
    void cancelFriendRequest(FriendActionRequest request);
    void acceptFriend(FriendActionRequest request);
    void rejectFriend(FriendActionRequest request);
    void unfriend(FriendActionRequest request);
    void blockFriend(FriendActionRequest request);
    void unblockFriend(FriendActionRequest request);

}
