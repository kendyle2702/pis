package lqc.com.pis.service.inter;

import lqc.com.pis.dto.request.post.CommentLevel1Request;
import lqc.com.pis.dto.request.post.CommentLevel2Request;
import lqc.com.pis.dto.request.post.CommentReactionRequest;
import lqc.com.pis.dto.request.post.PostReactionRequest;
import lqc.com.pis.dto.response.post.CommentLevel1Response;
import lqc.com.pis.dto.response.post.CommentLevel2Response;
import lqc.com.pis.dto.response.post.PostResponse;
import lqc.com.pis.dto.response.post.PublicPostResponse;

import java.util.List;

public interface PostService {
    List<PublicPostResponse> getPublicPostListByUserId(Long userId);

    List<CommentLevel1Response> getCommentLevel1(CommentLevel1Request commentLevel1Request);

    CommentLevel2Response getCommentLevel2(CommentLevel2Request commentLevel2Request);

    List<PublicPostResponse> getPublicPostsOfUserId(Long userId);

    List<PublicPostResponse> getPrivatePostsOfUserId(Long userId);

    PostResponse getPostById(Long postId);

    void likePost(PostReactionRequest reactionRequest);
    void disLikePost(PostReactionRequest reactionRequest);
    void likeComment(CommentReactionRequest commentReactionRequest);
    void disLikeComment(CommentReactionRequest commentReactionRequest);
}
