package lqc.com.pis.mapper;

import javax.annotation.processing.Generated;
import lqc.com.pis.dto.response.post.PostCreationResponse;
import lqc.com.pis.entity.Post;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-10T08:50:25+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.2-ea (Private Build)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public PostCreationResponse toPostCreationResponse(Post post) {
        if ( post == null ) {
            return null;
        }

        PostCreationResponse.PostCreationResponseBuilder postCreationResponse = PostCreationResponse.builder();

        postCreationResponse.type( post.getType() );
        postCreationResponse.content( post.getContent() );
        postCreationResponse.mode( post.getMode() );
        postCreationResponse.createAt( post.getCreateAt() );
        if ( post.getPinned() != null ) {
            postCreationResponse.pinned( post.getPinned() );
        }

        return postCreationResponse.build();
    }
}
