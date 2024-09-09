package com.as.rest.webservices.exception;

import com.as.rest.webservices.dto.DefaultErrorResponse;
import com.as.rest.webservices.dto.ValidationFailureResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class SocialAppCustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger responseEntityExceptionLogger = LoggerFactory.getLogger("SocialAppCustomResponseEntityExceptionHandler");

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DefaultErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
        DefaultErrorResponse response = DefaultErrorResponse.builder()
                .errorMessage(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        responseEntityExceptionLogger.error("handleAllExceptions | status : error | request : {} | trace : ", request,ex);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<DefaultErrorResponse> handleUserNotFound(Exception ex, WebRequest request) {
        DefaultErrorResponse response = DefaultErrorResponse.builder()
                .errorMessage(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        responseEntityExceptionLogger.error("handleUserNotFound | status : error | request : {} | trace : ", request,ex);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, @Nullable HttpHeaders headers, @Nullable HttpStatusCode status, @Nullable WebRequest request) {

        ValidationFailureResponse validationFailureResponse = ValidationFailureResponse.builder()
                .timestamp(LocalDateTime.now())
                .message("Rules have been validated")
                .build();

        validationFailureResponse.formatValidationResults(ex.getBindingResult());
        responseEntityExceptionLogger.error("handleMethodArgumentNotValid | status : error | HttpStatusCode {} " +
                " ||request : {} | Headers {}| trace : ", status, request, headers, ex);
        return new ResponseEntity<>(validationFailureResponse, HttpStatus.BAD_REQUEST);
    }


}
