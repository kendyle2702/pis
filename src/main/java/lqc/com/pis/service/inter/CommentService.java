package lqc.com.pis.service.inter;

import lqc.com.pis.dto.request.comment.CommentCreationRequest;
import lqc.com.pis.dto.response.comment.CommentCreationResponse;

import java.io.IOException;

public interface CommentService {
    CommentCreationResponse  createComment(CommentCreationRequest commentCreationRequest) throws IOException;
}
