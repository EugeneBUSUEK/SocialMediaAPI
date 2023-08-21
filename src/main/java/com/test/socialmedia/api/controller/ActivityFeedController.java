package com.test.socialmedia.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/feed")
@RequiredArgsConstructor
@Tag(name = "Feed", description = "Feed's resources")
public class ActivityFeedController {

//    private final FeedService feedService;
//
//    @Operation(summary = "get user activity feed")
//    @GetMapping(
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
//    public ResponseEntity<PageableResponse> getPosts(
//            @RequestParam(name = "date_sort") String dateSort,
//            @RequestParam(name = "page") Integer pageNumber,
//            @RequestParam(name = "size") Integer pageSize,
//            @AuthenticationPrincipal UserDetails userDetails
//    ) {
//        PageableResponse response = feedService.getUserFeed(userDetails, dateSort, pageNumber, pageSize);
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
}
