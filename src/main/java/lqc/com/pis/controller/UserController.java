package lqc.com.pis.controller;
import lqc.com.pis.entity.User;
import lqc.com.pis.entity.Conversation;
import lqc.com.pis.entity.Friendship;
import lqc.com.pis.entity.FriendshipId;
import lqc.com.pis.entity.ImageComment;
import lqc.com.pis.entity.ImagePost;
import lqc.com.pis.entity.InvalidAccessToken;
import lqc.com.pis.entity.Message;
import lqc.com.pis.entity.Post;
import lqc.com.pis.entity.Reaction;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lqc.com.pis.dto.request.user.UserUpdateRequest;
import lqc.com.pis.dto.response.ApiResponse;
import lqc.com.pis.dto.response.post.UserPostResponse;
import lqc.com.pis.dto.response.profile.FollowResponse;
import lqc.com.pis.dto.response.user.UserUpdateResponse;
import lqc.com.pis.service.inter.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @GetMapping
    ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<User>>builder()
                        .code(2001)
                        .data(userService.getUsers()).build()
        );
    }

    @GetMapping("/{userId}")
    ResponseEntity<ApiResponse<User>> getUserById(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<User>builder()
                        .code(2000)
                        .data(userService.getUserById(userId)).build()
        );
    }

    @PatchMapping("/{userId}")
    ResponseEntity<ApiResponse<UserUpdateResponse>> updateUserPartial(@PathVariable("userId") Long userId, @RequestBody UserUpdateRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<UserUpdateResponse>builder()
                        .code(2000)
                        .data(userService.updateUserPartial(userId,request)).build()
        );
    }

    @PostMapping("/avatar/{userId}")
    public ResponseEntity<ApiResponse<UserUpdateResponse>> updateAvatar(@PathVariable("userId") Long userId, @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<UserUpdateResponse>builder()
                        .code(2000)
                        .data(userService.updateAvatar(userId,file)).build()
        );
    }
    @GetMapping("/follow/{userId}")
    ResponseEntity<ApiResponse<FollowResponse>> getFollowing(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<FollowResponse>builder()
                        .code(2000)
                        .data(userService.getFollow(userId)).build()
        );
    }

    @GetMapping("/search")
    ResponseEntity<ApiResponse<List<UserPostResponse>>> searchUsersInPublic(@RequestParam("text") String request, @RequestParam("userId") String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<UserPostResponse>>builder()
                        .code(2000)
                        .data(userService.searchUsersInPublic(request,userId)).build()
        );
    }

    @PostMapping("/qr/{userId}")
    public ResponseEntity<ApiResponse<UserUpdateResponse>> updateQr(@PathVariable("userId") Long userId, @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<UserUpdateResponse>builder()
                        .code(2000)
                        .data(userService.updateQr(userId,file)).build()
        );
    }
}
