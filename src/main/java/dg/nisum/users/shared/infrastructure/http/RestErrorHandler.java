package dg.nisum.users.shared.infrastructure.http;

import dg.nisum.users.shared.domain.DomainError;
import dg.nisum.users.shared.domain.ForbiddenError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestErrorHandler {
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return sendErrorResponse(ex.getLocalizedMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = {DomainError.class})
    public ResponseEntity<ErrorResponse> handleDomainError(DomainError ex, WebRequest request) {
        ex.getStackTrace();
        return sendErrorResponse(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = {ForbiddenError.class})
    public ResponseEntity<ErrorResponse> handleDomainError(ForbiddenError ex, WebRequest request) {
        return sendErrorResponse(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    protected ResponseEntity<ErrorResponse> sendErrorResponse(String error, HttpStatus status) {

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(new ErrorResponse(error), httpHeaders, status);
    }
}
