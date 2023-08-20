package com.test.socialmedia.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String text;
    private String photoLink;
    private UserPostResponse author;
}
