package lqc.com.pis.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lqc.com.pis.dto.request.post.CommentLevel1Request;
import lqc.com.pis.dto.request.post.CommentLevel2Request;
import lqc.com.pis.dto.request.post.CommentReactionRequest;
import lqc.com.pis.dto.request.post.PostReactionRequest;
import lqc.com.pis.dto.response.ApiResponse;
import lqc.com.pis.dto.response.post.CommentLevel1Response;
import lqc.com.pis.dto.response.post.CommentLevel2Response;
import lqc.com.pis.dto.response.post.PostResponse;
import lqc.com.pis.dto.response.post.PublicPostResponse;
import lqc.com.pis.service.inter.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {

    PostService postService;

    @GetMapping("/{userId}")
    ResponseEntity<ApiResponse<List<PublicPostResponse>>> getPublicPosts(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<PublicPostResponse>>builder()
                        .code(2001)
                        .data(postService.getPublicPostListByUserId(userId)).build()
        );
    }

    @PostMapping("/comments/level1")
    ResponseEntity<ApiResponse<List<CommentLevel1Response>>> GetCommentsLevel1(@RequestBody CommentLevel1Request request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<CommentLevel1Response>>builder()
                        .code(2001)
                        .data(postService.getCommentLevel1(request)).build()
        );
    }

    @PostMapping("/comments/level2")
    ResponseEntity<ApiResponse<CommentLevel2Response>> GetCommentsLevel2(@RequestBody CommentLevel2Request request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<CommentLevel2Response>builder()
                        .code(2001)
                        .data(postService.getCommentLevel2(request)).build()
        );
    }

    @GetMapping("/{userId}/public")
    ResponseEntity<ApiResponse<List<PublicPostResponse>>> getPublicPostsOfUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<PublicPostResponse>>builder()
                        .code(2001)
                        .data(postService.getPublicPostsOfUserId(userId)).build()
        );
    }

    @GetMapping("/{userId}/private")
    ResponseEntity<ApiResponse<List<PublicPostResponse>>> getPrivatePostsOfUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<PublicPostResponse>>builder()
                        .code(2001)
                        .data(postService.getPrivatePostsOfUserId(userId)).build()
        );
    }

    @GetMapping("/detail/{postId}")
    ResponseEntity<ApiResponse<PostResponse>> getDetailPostsByPostId(@PathVariable("postId") Long postId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<PostResponse>builder()
                        .code(2001)
                        .data(postService.getPostById(postId)).build()
        );
    }

    @PostMapping("/like")
    ResponseEntity<ApiResponse<Void>> likePost(@RequestBody PostReactionRequest request) {
        postService.likePost(request);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Void>builder()
                        .code(2001)
                        .message("Like Success").build()
        );
    }

    @PostMapping("/dislike")
    ResponseEntity<ApiResponse<Void>> unLikePost(@RequestBody PostReactionRequest request) {
        postService.disLikePost(request);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Void>builder()
                        .code(2001)
                        .message("Dislike Success").build()
        );
    }

    @PostMapping("/comments/like")
    ResponseEntity<ApiResponse<Void>> likeComment(@RequestBody CommentReactionRequest request) {
        postService.likeComment(request);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Void>builder()
                        .code(2001)
                        .message("Like Success").build()
        );
    }

    @PostMapping("/comments/dislike")
    ResponseEntity<ApiResponse<Void>> unLikeComment(@RequestBody CommentReactionRequest request) {
        postService.disLikeComment(request);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Void>builder()
                        .code(2001)
                        .message("Dislike Success").build()
        );
    }
}
