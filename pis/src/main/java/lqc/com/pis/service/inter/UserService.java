package lqc.com.pis.service.inter;

import lqc.com.pis.dto.request.UserCreationRequest;
import lqc.com.pis.dto.request.UserUpdateRequest;
import lqc.com.pis.dto.response.ApiResponse;
import lqc.com.pis.entity.User;

import java.util.List;

public interface UserService {
    User createUser(UserCreationRequest userCreationRequest);
    User getUserById(Long id);
    User updateUser(Long id, UserUpdateRequest userUpdateRequest);
    void deleteUserById(Long id);
    List<User> getUsers();
}
