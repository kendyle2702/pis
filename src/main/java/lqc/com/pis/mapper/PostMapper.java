package lqc.com.pis.mapper;

import lqc.com.pis.dto.response.post.PostCreationResponse;
import org.mapstruct.Mapper;
import lqc.com.pis.entity.Post;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostCreationResponse toPostCreationResponse(Post post);
}
