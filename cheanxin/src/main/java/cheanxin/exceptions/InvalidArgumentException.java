package cheanxin.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/**
 * Created by Jawinton on 16/12/22.
 */
public class InvalidArgumentException extends RuntimeException implements ErrorResponseException {
    private ErrorResponse errorResponse;

    public InvalidArgumentException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public InvalidArgumentException(String errorMessage) {
        this.errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), errorMessage);
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
