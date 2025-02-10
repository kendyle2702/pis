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
