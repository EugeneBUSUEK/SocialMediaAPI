package com.test.socialmedia.support.mapper;

import com.test.socialmedia.exception.model.ErrorResponse;
import com.test.socialmedia.support.helper.DateHelper;
import com.test.socialmedia.support.helper.ServletPathHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

public class ErrorResponseMapper {
    public static ResponseEntity<ErrorResponse> errorToEntity(HttpStatus status, String message, WebRequest request) {
        ErrorResponse response = ErrorResponse.builder()
                .path(ServletPathHelper.getServletPath(request))
                .message(message)
                .status(status)
                .statusCode(status.value())
                .time(DateHelper.getCurrentUtilDate())
                .build();

        return new ResponseEntity<>(response, status);
    }
}
