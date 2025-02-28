package lqc.com.pis.service.inter;

import lqc.com.pis.dto.request.post.CommentLevel1Request;
import lqc.com.pis.dto.request.post.CommentLevel2Request;
import lqc.com.pis.dto.request.post.CommentReactionRequest;
import lqc.com.pis.dto.request.post.PostReactionRequest;
import lqc.com.pis.dto.response.post.*;

import java.util.List;

public interface PostService {
    List<PublicPostResponse> getPublicPostListByUserId(Long userId);

    List<CommentLevel1Response> getCommentLevel1(CommentLevel1Request commentLevel1Request);

    CommentLevel2Response getCommentLevel2(CommentLevel2Request commentLevel2Request);

    List<PublicPostResponse> getPublicPostsOfUserId(Long userId);

    List<PublicPostResponse> getPrivatePostsOfUserId(Long userId);

    PostResponse getPostById(Long postId);

    ReactionResponse likePost(PostReactionRequest reactionRequest);
    ReactionResponse disLikePost(PostReactionRequest reactionRequest);
    ReactionResponse likeComment(CommentReactionRequest commentReactionRequest);
    ReactionResponse disLikeComment(CommentReactionRequest commentReactionRequest);
}
