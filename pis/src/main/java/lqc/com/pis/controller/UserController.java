package lqc.com.pis.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lqc.com.pis.dto.request.user.UserUpdateRequest;
import lqc.com.pis.dto.response.ApiResponse;
import lqc.com.pis.dto.response.profile.FollowResponse;
import lqc.com.pis.dto.response.user.UserUpdateResponse;
import lqc.com.pis.service.inter.FileService;
import lqc.com.pis.service.inter.UserService;
import lqc.com.pis.entity.User;
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

}
