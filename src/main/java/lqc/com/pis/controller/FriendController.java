package lqc.com.pis.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lqc.com.pis.dto.request.friend.FriendActionRequest;
import lqc.com.pis.dto.request.friend.FriendProfileRequest;
import lqc.com.pis.dto.response.ApiResponse;
import lqc.com.pis.dto.response.post.PostResponse;
import lqc.com.pis.dto.response.user.UserResponse;
import lqc.com.pis.service.inter.FriendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FriendController {
    FriendService friendService;

    @GetMapping("/{userId}")
    ResponseEntity<ApiResponse<List<UserResponse>>> getPrivateFriends(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<UserResponse>>builder()
                        .code(2000)
                        .data(friendService.getFriendListByUserId(userId)).build()
        );
    }


    @PostMapping("/profile")
    ResponseEntity<ApiResponse<UserResponse>> getProfileOfUser(@RequestBody FriendProfileRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<UserResponse>builder()
                        .code(2000)
                        .data(friendService.getProfileOfUser(request)).build()
        );
    }

    // follow
    @PostMapping("/follow")
    ResponseEntity<ApiResponse<Void>> followFriend(@RequestBody FriendActionRequest request) {
        friendService.followFriend(request);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Void>builder()
                        .code(2000)
                        .message("Follow Success")
                        .build()
        );
    }


    // unfollow
    @DeleteMapping("/unfollow")
    ResponseEntity<ApiResponse<Void>> unFollowFriend(@RequestBody FriendActionRequest request) {
        friendService.unfollowFriend(request);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Void>builder()
                        .code(2000)
                        .message("Unfollow Success").build()
        );
    }


    //  friend request
    @PostMapping("/friendRequest")
    ResponseEntity<ApiResponse<Void>> friendRequest(@RequestBody FriendActionRequest request) {
        friendService.friendRequest(request);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Void>builder()
                        .code(2000)
                        .message("Friend Request Success").build()
        );
    }

    // cancel friend request
    @DeleteMapping("/cancelFriendRequest")
    ResponseEntity<ApiResponse<Void>> cancelFriendRequest(@RequestBody FriendActionRequest request) {
        friendService.cancelFriendRequest(request);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Void>builder()
                        .code(2000)
                        .message("Cancel Friend Request Success").build()
        );
    }


    // unfriend
    @DeleteMapping("/unfriend")
    ResponseEntity<ApiResponse<Void>> unfriend(@RequestBody FriendActionRequest request) {
        friendService.unfriend(request);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Void>builder()
                        .code(2000)
                        .message("Unfriend Success").build()
        );
    }


    // accept
    @PutMapping("/acceptFriend")
    ResponseEntity<ApiResponse<Void>> acceptFriend(@RequestBody FriendActionRequest request) {
        friendService.acceptFriend(request);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Void>builder()
                        .code(2000)
                        .message("Accept Friend Success").build()
        );
    }


    // reject
    @PutMapping("/rejectFriend")
    ResponseEntity<ApiResponse<Void>> rejectFriend(@RequestBody FriendActionRequest request) {
        friendService.rejectFriend(request);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Void>builder()
                        .code(2000)
                        .message("Reject Friend Success").build()
        );
    }


    // block
    @PutMapping("/blockFriend")
    ResponseEntity<ApiResponse<Void>> blockFriend(@RequestBody FriendActionRequest request) {
        friendService.blockFriend(request);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Void>builder()
                        .code(2000)
                        .message("Block Friend Success").build()
        );
    }


    // unblock
    @PutMapping("/unblockFriend")
    ResponseEntity<ApiResponse<Void>> unblockFriend(@RequestBody FriendActionRequest request) {
        friendService.unblockFriend(request);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Void>builder()
                        .code(2000)
                        .message("Unblock Friend Success").build()
        );
    }

    //get list request friend
    @GetMapping("/requestFriends/{userId}")
    ResponseEntity<ApiResponse<List<UserResponse>>> getRequestFriends(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<UserResponse>>builder()
                        .code(2000)
                        .data(friendService.getRequestFriends(userId)).build()
        );
    }

    //get list block friend
    @GetMapping("/blockFriends/{userId}")
    ResponseEntity<ApiResponse<List<UserResponse>>> getBlockFriends(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<UserResponse>>builder()
                        .code(2000)
                        .data(friendService.getBlockFriends(userId)).build()
        );
    }



}
