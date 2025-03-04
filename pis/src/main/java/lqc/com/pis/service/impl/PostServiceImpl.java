package lqc.com.pis.service.impl;

import jakarta.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lqc.com.pis.dto.request.post.*;
import lqc.com.pis.dto.response.post.*;
import lqc.com.pis.entity.*;
import lqc.com.pis.exception.AppException;
import lqc.com.pis.exception.ErrorCode;
import lqc.com.pis.mapper.PostMapper;
import lqc.com.pis.repository.*;
import lqc.com.pis.service.inter.FileService;
import lqc.com.pis.service.inter.PostService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostServiceImpl implements PostService {

    PostRepository postRepository;
    ImagePostRepository imagePostRepository;
    CommentRepository commentRepository;
    ReactionRepository reactionRepository;
    FriendShipRepository friendShipRepository;
    EntityManager entityManager;
    FileService fileService;

    PostMapper postMapper;





    @Override
    public List<PublicPostResponse> getPublicPostListByUserId(Long userId) {
        List<Post> sortedPosts = postRepository.findByMode("Public").stream()
                .sorted(Comparator.comparing(Post::getCreateAt).reversed())
                .toList();

        return sortedPosts.stream()
                .map(post -> PublicPostResponse.builder()
                        .id(Long.valueOf(post.getId()))
                        .userPostResponse(new UserPostResponse(
                                post.getUser().getId(),
                                post.getUser().getUsername(),
                                post.getUser().getAvatar(),
                                Math.toIntExact(friendShipRepository.countByUserIdAndFriendType(Long.valueOf(post.getUser().getId()), "FOLLOW")),
                                friendShipRepository.existsFriendship(Long.valueOf(post.getUser().getId()),userId, "FOLLOW") >0
                        ))
                        .caption(post.getContent())
                        .images(Optional.ofNullable(imagePostRepository.findAllByPostId(Long.valueOf(post.getId()))).orElse(Collections.emptyList()).stream().map(image
                                ->new ImagePostReponse(Long.valueOf(image.getId()), image.getUrl())).collect(Collectors.toList()))
                        .likes(Math.toIntExact(reactionRepository.countByPostId(Long.valueOf(post.getId())))) // Nếu null, gán 0
                        .comments(Math.toIntExact(commentRepository.countByPostId(Long.valueOf(post.getId()))))
                        .createTime(timeAgo(post.getCreateAt()))
                        .type(post.getType())
                        .isLike(reactionRepository.existsByPostIdAndUserId(Long.valueOf(post.getId()), userId))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentLevel1Response> getCommentLevel1(CommentLevel1Request commentLevel1Request) {
        List<Comment> comments = commentRepository.findByPostIdAndParentCommentId(commentLevel1Request.getPostId(),null);
        comments.sort(Comparator.comparing(Comment::getCreateAt).reversed());

        return  comments.stream()
                .map(comment -> CommentLevel1Response.builder()
                        .id(Long.valueOf(comment.getId()))
                        .userPostResponse(new UserPostResponse(
                                comment.getUser().getId(),
                                comment.getUser().getUsername(),
                                comment.getUser().getAvatar(),
                                Math.toIntExact(friendShipRepository.countByUserIdAndFriendType(Long.valueOf(comment.getUser().getId()), "FOLLOW")),
                                friendShipRepository.existsFriendship(Long.valueOf(comment.getUser().getId()),commentLevel1Request.getUserId(),  "FOLLOW") >0
                        ))
                        .content(comment.getContent())
                        .url(comment.getUrl())
                        .likes(Math.toIntExact(reactionRepository.countByCommentId(Long.valueOf(comment.getId())))) // Nếu null, gán 0
                        .comments(Math.toIntExact(commentRepository.countByParentCommentId(Long.valueOf(comment.getId()))))
                        .createTime(timeAgo(comment.getCreateAt()))
                        .type(comment.getType())
                        .isLike(reactionRepository.existsByCommentIdAndUserId(Long.valueOf(comment.getId()), commentLevel1Request.getUserId()))
                        .build())
                .collect(Collectors.toList());

    }

    @Override
    public CommentLevel2Response getCommentLevel2(CommentLevel2Request commentLevel2Request) {
        Comment comment = commentRepository.findById(commentLevel2Request.getCommentId()).orElseThrow(()-> new AppException(ErrorCode.COMMENT_NOT_EXISTED));


        List<Comment> commentsLevel2 = commentRepository.findByParentCommentId(commentLevel2Request.getCommentId());

        List<ElementCommentLevel2Response> elementCommentLevel2Responses = commentsLevel2.stream().map(childComment -> ElementCommentLevel2Response.builder()
                .id(Long.valueOf(childComment.getId()))
                .userPostResponse(new UserPostResponse(
                        childComment.getUser().getId(),
                        childComment.getUser().getUsername(),
                        childComment.getUser().getAvatar(),
                        Math.toIntExact(friendShipRepository.countByUserIdAndFriendType(Long.valueOf(childComment.getUser().getId()), "FOLLOW")),
                        friendShipRepository.existsFriendship( Long.valueOf(childComment.getUser().getId()), commentLevel2Request.getUserId(),"FOLLOW") >0
                ))
                .content(childComment.getContent())
                .url(childComment.getUrl())
                .likes(Math.toIntExact(reactionRepository.countByCommentId(Long.valueOf(childComment.getId())))) // Nếu null, gán 0
                .createTime(timeAgo(childComment.getCreateAt()))
                .type(childComment.getType())
                .isLike(reactionRepository.existsByCommentIdAndUserId(Long.valueOf(childComment.getId()), commentLevel2Request.getUserId()))
                .build()).collect(Collectors.toList());

        return  CommentLevel2Response.builder()
                .id(commentLevel2Request.getCommentId())
                .userPostResponse(new UserPostResponse(
                        comment.getUser().getId(),
                        comment.getUser().getUsername(),
                        comment.getUser().getAvatar(),
                        Math.toIntExact(friendShipRepository.countByUserIdAndFriendType(Long.valueOf(comment.getUser().getId()), "FOLLOW")),
                        friendShipRepository.existsFriendship( Long.valueOf(comment.getUser().getId()), commentLevel2Request.getUserId(),"FOLLOW") > 0
                ))
                .content(comment.getContent())
                .url(comment.getUrl())
                .likes(Math.toIntExact(reactionRepository.countByCommentId(Long.valueOf(comment.getId()))))
                .comments(Math.toIntExact(commentRepository.countByParentCommentId(Long.valueOf(comment.getId()))))
                .createTime(timeAgo(comment.getCreateAt()))
                .type(comment.getType())
                .isLike(reactionRepository.existsByCommentIdAndUserId(Long.valueOf(comment.getId()), commentLevel2Request.getUserId()))
                .elementCommentLevel2(elementCommentLevel2Responses)
                .build();
    }

    @Override
    public List<PublicPostResponse> getPublicPostsOfUserId(Long userId) {
        List<Post> sortedPosts = postRepository.findByUserIdAndMode(Math.toIntExact(userId), "Public").stream()
                .sorted(Comparator.comparing(Post::getCreateAt).reversed())
                .toList();

        return sortedPosts.stream()
                .map(post -> PublicPostResponse.builder()
                        .id(Long.valueOf(post.getId()))
                        .userPostResponse(new UserPostResponse(
                                post.getUser().getId(),
                                post.getUser().getUsername(),
                                post.getUser().getAvatar(),
                                Math.toIntExact(friendShipRepository.countByUserIdAndFriendType(Long.valueOf(post.getUser().getId()), "FOLLOW")),
                                friendShipRepository.existsFriendship(Long.valueOf(post.getUser().getId()),userId,  "FOLLOW") >0
                        ))
                        .caption(post.getContent())
                        .images(imagePostRepository.findAllByPostId(Long.valueOf(post.getId())).stream().map(image
                                ->new ImagePostReponse(Long.valueOf(image.getId()), image.getUrl())).collect(Collectors.toList()))
                        .likes(Math.toIntExact(reactionRepository.countByPostId(Long.valueOf(post.getId())))) // Nếu null, gán 0
                        .comments(Math.toIntExact(commentRepository.countByPostId(Long.valueOf(post.getId()))))
                        .createTime(timeAgo(post.getCreateAt()))
                        .type(post.getType())
                        .isLike(reactionRepository.existsByPostIdAndUserId(Long.valueOf(post.getId()), userId))
                        .build())
                .collect(Collectors.toList());

    }

    @Override
    public List<PublicPostResponse> getPrivatePostsOfUserId(Long userId) {
        List<Post> sortedPosts = postRepository.findByUserIdAndMode(Math.toIntExact(userId), "Private").stream()
                .sorted(Comparator.comparing(Post::getCreateAt).reversed())
                .toList();

        return sortedPosts.stream()
                .map(post -> PublicPostResponse.builder()
                        .id(Long.valueOf(post.getId()))
                        .userPostResponse(new UserPostResponse(
                                post.getUser().getId(),
                                post.getUser().getUsername(),
                                post.getUser().getAvatar(),
                                Math.toIntExact(friendShipRepository.countByUserIdAndFriendType(Long.valueOf(post.getUser().getId()), "FOLLOW")),
                                friendShipRepository.existsFriendship( Long.valueOf(post.getUser().getId()),userId, "FOLLOW") >0
                        ))
                        .caption(post.getContent())
                        .images(imagePostRepository.findAllByPostId(Long.valueOf(post.getId())).stream().map(image
                                ->new ImagePostReponse(Long.valueOf(image.getId()), image.getUrl())).collect(Collectors.toList()))
                        .likes(Math.toIntExact(reactionRepository.countByPostId(Long.valueOf(post.getId())))) // Nếu null, gán 0
                        .comments(Math.toIntExact(commentRepository.countByPostId(Long.valueOf(post.getId()))))
                        .createTime(timeAgo(post.getCreateAt()))
                        .type(post.getType())
                        .isLike(reactionRepository.existsByPostIdAndUserId(Long.valueOf(post.getId()), userId))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public PostResponse getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);

        return PostResponse.builder()
                .postId(Long.valueOf(post.getId()))
                .type(post.getType())
                .pinned(post.getPinned())
                .urls(imagePostRepository.findAllByPostId(Long.valueOf(post.getId())).stream().map(image
                        ->new ImagePostReponse(Long.valueOf(image.getId()), image.getUrl())).collect(Collectors.toList()))
                .content(post.getContent())
                .createAt(timeAgo(post.getCreateAt()))
                .mode(post.getMode())
                .build();
    }

    @Override
    public ReactionResponse likePost(PostReactionRequest reactionRequest) {
        User user = entityManager.getReference(User.class, reactionRequest.getUserId());
        Post post = entityManager.getReference(Post.class, reactionRequest.getPostId());

        Reaction reaction = Reaction.builder()
                .user(user)
                .post(post)
                .comment(null)
                .createdAt(Instant.now())
                .build();
        reactionRepository.save(reaction);

        return ReactionResponse.builder()
                .numberLike(Math.toIntExact(reactionRepository.countByPostId(Long.valueOf(reactionRequest.getPostId()))))
                .build();
    }

    @Override
    public ReactionResponse disLikePost(PostReactionRequest reactionRequest) {
        boolean isExit = reactionRepository.existsByPostIdAndUserId(reactionRequest.getPostId(), reactionRequest.getUserId());
        if (isExit) {
            reactionRepository.deleteByPostIdAndUserId(reactionRequest.getPostId(), reactionRequest.getUserId());
        }
        else{
            throw new AppException(ErrorCode.NOT_HAVE_REACTION);
        }
        return ReactionResponse.builder()
                .numberLike(Math.toIntExact(reactionRepository.countByPostId(Long.valueOf(reactionRequest.getPostId()))))
                .build();
    }

    @Override
    public ReactionResponse likeComment(CommentReactionRequest commentReactionRequest) {
        User user = entityManager.getReference(User.class, commentReactionRequest.getUserId());
        Comment comment = entityManager.getReference(Comment.class, commentReactionRequest.getCommentId());

        Reaction reaction = Reaction.builder()
                .user(user)
                .post(null)
                .comment(comment)
                .createdAt(Instant.now())
                .build();
        reactionRepository.save(reaction);
        return ReactionResponse.builder()
                .numberLike(Math.toIntExact(reactionRepository.countByCommentId(Long.valueOf(commentReactionRequest.getCommentId()))))
                .build();
    }

    @Override
    public ReactionResponse disLikeComment(CommentReactionRequest commentReactionRequest) {

        boolean isExit = reactionRepository.existsByCommentIdAndUserId(commentReactionRequest.getCommentId(), commentReactionRequest.getUserId());
        if (isExit) {
            reactionRepository.deleteByCommentIdAndUserId(commentReactionRequest.getCommentId(), commentReactionRequest.getUserId());
        }
        else{
            throw new AppException(ErrorCode.NOT_HAVE_REACTION);
        }
        return ReactionResponse.builder()
                .numberLike(Math.toIntExact(reactionRepository.countByCommentId(Long.valueOf(commentReactionRequest.getCommentId()))))
                .build();
    }

    @Override
    public PostCreationResponse addPost(PostCreationRequest postCreationRequest) throws IOException {
        String mode = postCreationRequest.getMode();

        User user = entityManager.getReference(User.class, postCreationRequest.getUserId());

        Post newPost = Post.builder()
                .user(user)
                .type(postCreationRequest.getType())
                .content(postCreationRequest.getContent())
                .mode(postCreationRequest.getMode())
                .createAt(Instant.now())
                .pinned(false)
                .build();

        Post savedPost = postRepository.save(newPost);
        PostCreationResponse response = postMapper.toPostCreationResponse(savedPost);
        response.setUserId(savedPost.getUser().getId());
        response.setPostId(savedPost.getId());

        List<String> urls = new ArrayList<>();

        if(mode.equals("Public")){
            // image
            if(postCreationRequest.getType().equals("Image")){
                // have image
                if(postCreationRequest.getFiles() != null && postCreationRequest.getFiles().length>0 && !postCreationRequest.getFiles()[0].isEmpty()){
                    for (MultipartFile file : postCreationRequest.getFiles()) {
                        String url = fileService.uploadFile(file);
                        urls.add(url);
                        ImagePost imagePost = ImagePost.builder()
                                .post(savedPost)
                                .url(url)
                                .build();
                        imagePostRepository.save(imagePost);

                    }
                }
                // not have image
                else{

                }
            }
            // voice
            else{
                for (MultipartFile file : postCreationRequest.getFiles()) {
                    String url = fileService.uploadFile(file);
                    urls.add(url);
                    ImagePost imagePost = ImagePost.builder()
                            .post(savedPost)
                            .url(url)
                            .build();
                    imagePostRepository.save(imagePost);
                }
            }
        }
        else{
            for (MultipartFile file : postCreationRequest.getFiles()) {
                String url = fileService.uploadFile(file);
                urls.add(url);
                ImagePost imagePost = ImagePost.builder()
                        .post(savedPost)
                        .url(url)
                        .build();
                imagePostRepository.save(imagePost);
            }
        }
        response.setUrls(urls);


        return response;
    }

    private String timeAgo(Instant createTime) {
        Instant now = Instant.now();

        LocalDateTime createDateTime = LocalDateTime.ofInstant(createTime, ZoneOffset.UTC);
        LocalDateTime nowDateTime = LocalDateTime.ofInstant(now, ZoneOffset.UTC);

        Duration duration = Duration.between(createDateTime, nowDateTime);
        //fd
        long seconds = duration.getSeconds();
        if (seconds < 60) return seconds + " seconds";
        long minutes = duration.toMinutes();
        if (minutes < 60) return minutes + " minutes";
        long hours = duration.toHours();
        if (hours < 24) return hours + " hours";
        long days = duration.toDays();
        if (days < 7) return days + " days";

        ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");
        LocalDateTime displayDateTime = createDateTime.atZone(ZoneOffset.UTC).withZoneSameInstant(zoneId).toLocalDateTime();

        DateTimeFormatter formatter;
        if (createDateTime.getYear() == nowDateTime.getYear()) {
            formatter = DateTimeFormatter.ofPattern("dd MMM");
        } else {
            formatter = DateTimeFormatter.ofPattern("dd MMM, yyyy");
        }

        return displayDateTime.format(formatter);
    }



}

