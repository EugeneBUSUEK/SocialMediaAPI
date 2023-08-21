package com.test.socialmedia.exception.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static com.test.socialmedia.support.constraint.DateFormat.API_ERROR_FORMAT;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String path;
    private String message;
    private HttpStatus status;
    private Integer statusCode;
    @JsonFormat(shape = STRING, pattern = API_ERROR_FORMAT)
    private Date time;
}
