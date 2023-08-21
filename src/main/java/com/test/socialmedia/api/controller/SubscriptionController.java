package com.test.socialmedia.api.controller;

import com.test.socialmedia.model.response.UserResponse;
import com.test.socialmedia.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
@Tag(name = "Subscriptions", description = "Subscriptions' resources")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Operation(summary = "subscribe on user by user id")
    @PostMapping(value = "/{userId}")
    public ResponseEntity<?> subscribeOnUser(
            @PathVariable(name = "userId") Long userId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        subscriptionService.subscribeOnUserById(userId, userDetails.getUsername());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "unsubscribe on user by user id")
    @PutMapping(value = "/{userId}")
    public ResponseEntity<?> unsubscribeOnUser(
            @PathVariable(name = "userId") Long userId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        subscriptionService.unsubscribeOnUserById(userId, userDetails.getUsername());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "get user friends")
    @GetMapping(value = "/friends/{userId}")
    public ResponseEntity<List<UserResponse>> getUserFriends(
            @PathVariable(name = "userId") Long userId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        List<UserResponse> response = subscriptionService.getUserFriends(userId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "get user subscribers")
    @GetMapping(value = "/subscribers/{userId}")
    public ResponseEntity<List<UserResponse>> getUserSubscribers(
            @PathVariable(name = "userId") Long userId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        List<UserResponse> response = subscriptionService.getUserSubscribers(userId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "get user subscriptions")
    @GetMapping(value = "/subscriptions/{userId}")
    public ResponseEntity<List<UserResponse>> getUserSubscriptions(
            @PathVariable(name = "userId") Long userId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        List<UserResponse> response = subscriptionService.getUserSubscriptions(userId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
