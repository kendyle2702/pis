package lqc.com.pis.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lqc.com.pis.dto.response.post.PostResponse;
import lqc.com.pis.dto.response.user.UserResponse;
import lqc.com.pis.entity.Friendship;
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

    @Override
    public List<UserResponse> getFriendListByUserId(Long userId) {
        List<Friendship> filteredFriendships = friendShipRepository.findByUserId(userId).stream()
                .filter(f -> "FRIEND".equals(f.getId().getFriendType()))
                .toList();
        return filteredFriendships.stream().map(
                friendship -> userMapper.toUserResponse(friendship.getFriend())
        ).toList();
    }
}
