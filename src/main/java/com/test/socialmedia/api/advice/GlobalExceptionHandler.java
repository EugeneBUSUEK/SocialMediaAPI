package com.test.socialmedia.api.advice;

import com.test.socialmedia.exception.FileException;
import com.test.socialmedia.exception.InaccessibleOperationException;
import com.test.socialmedia.exception.InvalidDirectionFormat;
import com.test.socialmedia.exception.NotFoundException;
import com.test.socialmedia.exception.model.ErrorResponse;
import com.test.socialmedia.support.mapper.ErrorResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FileException.class)
    public ResponseEntity<ErrorResponse> handleFileException(FileException e, WebRequest request) {

        return ErrorResponseMapper.errorToEntity(HttpStatus.BAD_REQUEST, e.getMessage(), request);
    }

    @ExceptionHandler(InaccessibleOperationException.class)
    public ResponseEntity<ErrorResponse> handleFileException(InaccessibleOperationException e, WebRequest request) {

        return ErrorResponseMapper.errorToEntity(HttpStatus.FORBIDDEN, e.getMessage(), request);
    }

    @ExceptionHandler(InvalidDirectionFormat.class)
    public ResponseEntity<ErrorResponse> handleFileException(InvalidDirectionFormat e, WebRequest request) {

        return ErrorResponseMapper.errorToEntity(HttpStatus.BAD_REQUEST, e.getMessage(), request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleFileException(NotFoundException e, WebRequest request) {

        return ErrorResponseMapper.errorToEntity(HttpStatus.BAD_REQUEST, e.getMessage(), request);
    }
}
