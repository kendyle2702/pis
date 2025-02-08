package lqc.com.pis.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lqc.com.pis.dto.request.UserCreationRequest;
import lqc.com.pis.dto.request.UserUpdateRequest;
import lqc.com.pis.dto.response.ApiResponse;
import lqc.com.pis.service.inter.UserService;
import lqc.com.pis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping
    ResponseEntity<ApiResponse<User>> addUser(@RequestBody UserCreationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<User>builder()
                        .code(2001)
                        .data(userService.createUser(request)).build()
        );
    }

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

    @PutMapping("/{userId}")
    ResponseEntity<ApiResponse<User>> updateUserById(@PathVariable("userId") Long userId, @RequestBody UserUpdateRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<User>builder()
                        .code(2000)
                        .data(userService.updateUser(userId,request)).build()
        );
    }

    @DeleteMapping("/{userId}")
    ResponseEntity<Void> deleteUserById(@PathVariable("userId") Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }
}
