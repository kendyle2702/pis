package lqc.com.pis.service.inter;

import lqc.com.pis.dto.response.post.PostResponse;
import lqc.com.pis.dto.response.user.UserResponse;

import java.util.List;

public interface FriendService {
    List<UserResponse> getFriendListByUserId(Long userId);
}
