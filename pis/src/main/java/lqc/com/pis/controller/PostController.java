package lqc.com.pis.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lqc.com.pis.dto.request.comment.CommentCreationRequest;
import lqc.com.pis.dto.request.post.*;
import lqc.com.pis.dto.response.ApiResponse;
import lqc.com.pis.dto.response.comment.CommentCreationResponse;
import lqc.com.pis.dto.response.post.*;
import lqc.com.pis.service.inter.CommentService;
import lqc.com.pis.service.inter.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {

    PostService postService;
    CommentService commentService;

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
    ResponseEntity<ApiResponse<ReactionResponse>> likePost(@RequestBody PostReactionRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<ReactionResponse>builder()
                        .code(2001)
                        .data(postService.likePost(request))
                        .message("Like Success").build()
        );
    }

    @PostMapping("/dislike")
    ResponseEntity<ApiResponse<ReactionResponse>> unLikePost(@RequestBody PostReactionRequest request) {

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<ReactionResponse>builder()
                        .code(2001)
                        .data( postService.disLikePost(request))
                        .message("Dislike Success").build()
        );
    }

    @PostMapping("/comments/like")
    ResponseEntity<ApiResponse<ReactionResponse>> likeComment(@RequestBody CommentReactionRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<ReactionResponse>builder()
                        .code(2001)
                        .data(postService.likeComment(request))
                        .message("Like Success").build()
        );
    }

    @PostMapping("/comments/dislike")
    ResponseEntity<ApiResponse<ReactionResponse>> unLikeComment(@RequestBody CommentReactionRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<ReactionResponse>builder()
                        .code(2001)
                        .data(postService.disLikeComment(request))
                        .message("Dislike Success").build()
        );
    }


    @PostMapping()
    ResponseEntity<ApiResponse<PostCreationResponse>> createPost(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("userId") String userId,
            @RequestParam("type") String type,
            @RequestParam("content") String content,
            @RequestParam("mode") String mode
    ) throws IOException {
        PostCreationRequest request = PostCreationRequest.builder()
                .userId(Integer.parseInt(userId))
                .type(type)
                .content(content)
                .mode(mode)
                .files(files)
                .build();


        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<PostCreationResponse>builder()
                        .code(2001)
                        .data(postService.addPost(request))
                        .build()
        );
    }


    // comment => formdata
    @PostMapping("/comments")
    ResponseEntity<ApiResponse<CommentCreationResponse>> createComment(
            @RequestParam("file") MultipartFile file,
            @RequestParam("postId") String postId,
            @RequestParam("parentCommentId") String parentCommentId,
            @RequestParam("userId") String userId,
            @RequestParam("content") String content,
            @RequestParam("type") String type
    ) throws IOException {
        int parentCommentIdInt;
        if(parentCommentId == null || parentCommentId.equals("")){
            parentCommentIdInt = -1;
        }
        else{
            parentCommentIdInt = Integer.parseInt(parentCommentId);
        }

        if(file == null || file.isEmpty()){
            file = null;
        }

        log.info(String.valueOf(file));
        log.info(postId);
        log.info(String.valueOf(parentCommentIdInt));
        log.info(userId);
        log.info(content);
        log.info(type);
        CommentCreationRequest request = CommentCreationRequest.builder()
                .postId(Integer.parseInt(postId))
                .parentCommentId(parentCommentIdInt)
                .userId(Integer.parseInt(userId))
                .content(content)
                .file(file)
                .type(type)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<CommentCreationResponse>builder()
                        .code(2001)
                        .data(commentService.createComment(request))
                        .build()
        );
    }
}
