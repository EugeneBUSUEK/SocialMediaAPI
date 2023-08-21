package com.test.socialmedia.api.controller;

import com.test.socialmedia.model.request.PostRequest;
import com.test.socialmedia.model.response.PageableResponse;
import com.test.socialmedia.model.response.PostResponse;
import com.test.socialmedia.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@Tag(name = "Posts", description = "Posts' resources")
public class PostController {

    private final PostService postService;

    @Operation(summary = "create user post")
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PostResponse> createPost(
            @RequestBody PostRequest postRequest,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        PostResponse postResponse = postService.createUserPost(postRequest, userDetails);

        return new ResponseEntity<>(postResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "update user post")
    @PutMapping(
            value = "/{postId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PostResponse> updatePost(
            @PathVariable(name = "postId") Long postId,
            @RequestBody PostRequest postRequest,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        PostResponse postResponse = postService.updateUserPost(postId, postRequest, userDetails);

        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @Operation(summary = "delete user post")
    @DeleteMapping(value = "/{postId}")
    public ResponseEntity<?> deletePost(
            @PathVariable(name = "postId") Long postId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        postService.deleteUserPost(postId, userDetails);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "get user posts")
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PageableResponse<PostResponse>> getPosts(
            @RequestParam(name = "date_sort") String dateSort,
            @RequestParam(name = "page") Integer pageNumber,
            @RequestParam(name = "size") Integer pageSize,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        PageableResponse<PostResponse> response = postService.getUserPosts(userDetails, dateSort, pageNumber, pageSize);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
