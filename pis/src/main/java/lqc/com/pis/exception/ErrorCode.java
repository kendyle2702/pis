package lqc.com.pis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),

    INVALID_KEY(4001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USER_EXISTED(4002, "User existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(4005, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(4006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(4007, "You do not have permission", HttpStatus.FORBIDDEN),
    USER_BANNED(4008, "User banned or deleted", HttpStatus.BAD_REQUEST),

    COMMENT_NOT_EXISTED(4009, "Comment not existed", HttpStatus.NOT_FOUND),
    NOT_HAVE_CONVERSATION(4010, "Not have conversation", HttpStatus.BAD_REQUEST),
    NOT_HAVE_REACTION(4011, "Not have reaction", HttpStatus.BAD_REQUEST),

    HAVE_FOLLOWING(4012, "You have followed this user!", HttpStatus.BAD_REQUEST),
    HAVE_NOT_FOLLOWING(4013,"You have not followed this user!",HttpStatus.BAD_REQUEST),
    HAVE_FRIEND(4014, "You are already friends with this user!", HttpStatus.BAD_REQUEST),
    HAVE_REQUEST_FRIEND(4015, "You have sent a friend request to this person!", HttpStatus.BAD_REQUEST),
    HAVE_NOT_REQUEST_FRIEND(4016, "You have not sent a friend request to this person yet!", HttpStatus.BAD_REQUEST),
    HAVE_NOT_FRIEND(4017, "You are not friends with this user yet!", HttpStatus.BAD_REQUEST),
    HAVE_BLOCK(4018,"You and this person have blocked each other!",HttpStatus.BAD_REQUEST),
    HAVE_NOT_BLOCK(4019,"You and this person have not blocked each other!",HttpStatus.BAD_REQUEST)
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
