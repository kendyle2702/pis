package lqc.com.pis.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lqc.com.pis.dto.request.UserCreationRequest;
import lqc.com.pis.dto.request.UserUpdateRequest;
import lqc.com.pis.entity.User;
import lqc.com.pis.exception.AppException;
import lqc.com.pis.exception.ErrorCode;
import lqc.com.pis.mapper.UserMapper;
import lqc.com.pis.repository.UserRepository;
import lqc.com.pis.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    @Override
    public User createUser(UserCreationRequest userCreationRequest) {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        userCreationRequest.setHashPassword(passwordEncoder.encode(userCreationRequest.getHashPassword()));

        User user = userMapper.toUser(userCreationRequest);

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->new AppException(ErrorCode.USER_NOT_EXISTED));
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(id).orElseThrow(() ->new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(user, userUpdateRequest);

        return userRepository.save(user);
    }


    @Override
    public void deleteUserById(Long id) {
        userRepository.findById(id).orElseThrow(() ->new AppException(ErrorCode.USER_NOT_EXISTED));
        userRepository.deleteById(id);
    }
}
