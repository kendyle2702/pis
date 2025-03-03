package lqc.com.pis.service.inter;

import lqc.com.pis.dto.request.post.*;
import lqc.com.pis.dto.response.post.*;

import java.io.IOException;
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

    PostCreationResponse addPost(PostCreationRequest postCreationRequest) throws IOException;
}
