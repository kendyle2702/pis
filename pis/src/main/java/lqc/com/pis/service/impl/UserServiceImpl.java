package lqc.com.pis.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lqc.com.pis.dto.request.user.UserUpdateRequest;
import lqc.com.pis.dto.response.profile.FollowResponse;
import lqc.com.pis.dto.response.user.UserUpdateResponse;
import lqc.com.pis.entity.User;
import lqc.com.pis.exception.AppException;
import lqc.com.pis.exception.ErrorCode;
import lqc.com.pis.mapper.UserMapper;
import lqc.com.pis.repository.FriendShipRepository;
import lqc.com.pis.repository.UserRepository;
import lqc.com.pis.service.inter.FileService;
import lqc.com.pis.service.inter.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    FileService fileService;
    FriendShipRepository friendShipRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->new AppException(ErrorCode.USER_NOT_EXISTED));
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserUpdateResponse updateUserPartial(Long userId, UserUpdateRequest userUpdateRequest) {
        User user = getUserById(userId);

        if(userUpdateRequest.getFirstName() != null) {
            user.setFirstName(userUpdateRequest.getFirstName());
        }
        if(userUpdateRequest.getLastName() != null) {
            user.setLastName(userUpdateRequest.getLastName());
        }
        if(userUpdateRequest.getEmail() != null) {
            user.setEmail(userUpdateRequest.getEmail());
        }
        if(userUpdateRequest.getIsActive() != null) {
            user.setIsActive(userUpdateRequest.getIsActive());
        }
        if(userUpdateRequest.getBirthday() != null) {
            user.setBirthday(userUpdateRequest.getBirthday());
        }

        UserUpdateResponse userUpdateResponse = userMapper.toUserUpdateResponse(user);

        userRepository.save(user);

        return userUpdateResponse;
    }

    @Override
    public UserUpdateResponse updateAvatar(Long userId, MultipartFile avatarFile) throws IOException {
        User user = getUserById(userId);

        String url = fileService.uploadFile(avatarFile);

        user.setAvatar(url);

        userRepository.save(user);

        return userMapper.toUserUpdateResponse(user);
    }

    @Override
    public FollowResponse getFollow(Long userId) {
        Long followers = friendShipRepository.countByUserIdAndFriendType(userId,"FOLLOW");
        Long following = friendShipRepository.countByFriendIdAndFriendType(userId,"FOLLOW");

        return FollowResponse.builder()
                .followers(Math.toIntExact(followers))
                .followingNumbers(Math.toIntExact(following))
                .build();
    }
}
