package lqc.com.pis.service.inter;

import lqc.com.pis.dto.request.user.UserUpdateRequest;
import lqc.com.pis.dto.response.post.UserPostResponse;
import lqc.com.pis.dto.response.profile.FollowResponse;
import lqc.com.pis.dto.response.user.UserUpdateResponse;
import lqc.com.pis.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    User getUserById(Long id);
    List<User> getUsers();
    UserUpdateResponse updateUserPartial(Long userId, UserUpdateRequest userUpdateRequest);
    UserUpdateResponse updateAvatar(Long userId, MultipartFile avatarFile) throws IOException;
    UserUpdateResponse updateQr(Long userId, MultipartFile avatarFile) throws IOException;

    FollowResponse getFollow(Long userId);
    List<UserPostResponse> searchUsersInPublic(String request, String userId);
}
