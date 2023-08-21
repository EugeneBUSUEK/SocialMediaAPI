package com.test.socialmedia.service;

import com.test.socialmedia.model.response.PageableResponse;
import com.test.socialmedia.model.response.UserResponse;
import com.test.socialmedia.persist.SubscriptionRepository;
import com.test.socialmedia.persist.entity.SubscriptionEntity;
import com.test.socialmedia.persist.entity.UserEntity;
import com.test.socialmedia.persist.service.DBSubscriptionService;
import com.test.socialmedia.persist.service.DBUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final DBSubscriptionService dbSubscriptionService;
    private final DBUserService dbUserService;


    public void subscribeOnUserById(Long userId, String username) {
        UserEntity user = dbUserService.getUserByLogin(username);
        Optional<SubscriptionEntity> subscriptionOpt = subscriptionRepository.findByUserFromIdAndUserToIdIn(userId, user.getId());

        if (subscriptionOpt.isEmpty()) {
            SubscriptionEntity subscription = new SubscriptionEntity()
                    .setUserFrom(user)
                    .setUserTo(UserEntity.builder()
                            .id(userId)
                            .build())
                    .setIsApproved(false);

            subscriptionRepository.save(subscription);
            return;
        }
        SubscriptionEntity subscription = subscriptionOpt.get();

        if (subscription.getUserTo().getId().equals(user.getId())) {
            subscription.setIsApproved(true);

            subscriptionRepository.save(subscription);
        }
    }

    public void unsubscribeOnUserById(Long userId, String username) {
        UserEntity user = dbUserService.getUserByLogin(username);
        Optional<SubscriptionEntity> subscriptionOpt = subscriptionRepository.findByUserFromIdAndUserToIdIn(userId, user.getId());

        if (subscriptionOpt.isEmpty()) {
            throw new RuntimeException("No subscription between user with id=" + user.getId() +
                    " and user with id=" + userId + " not found");
        }
        SubscriptionEntity subscription =subscriptionOpt.get();

        if (subscription.getIsApproved().equals(true)) {
            if (subscription.getUserFrom().getId().equals(user.getId())) {
                subscription.setUserFrom(UserEntity.builder()
                        .id(userId)
                        .build())
                        .setUserTo(user)
                        .setIsApproved(false);
            } else if (subscription.getUserTo().getId().equals(user.getId())) {
                subscription.setIsApproved(false);
            }

            subscriptionRepository.save(subscription);
        } else {
            if (subscription.getUserFrom().getId().equals(user.getId())) {
                subscriptionRepository.delete(subscription);
            }
        }
    }

    public List<UserResponse> getUserFriends(Long userId) {
        List<UserEntity> friends = dbSubscriptionService
                .getSubscriptionMembersOfUserByUserIdAndIsApprovedWithUserSide(
                        userId,
                        true,
                        DBSubscriptionService.Side.ANY
                );

        return friends.stream().map(userEntity -> UserResponse.builder()
                .id(userEntity.getId())
                .login(userEntity.getLogin())
                .email(userEntity.getEmail())
                .build()).toList();
    }

    public List<UserResponse> getUserSubscribers(Long userId) {
        List<UserEntity> subscribers = dbSubscriptionService
                .getSubscriptionMembersOfUserByUserIdAndIsApprovedWithUserSide(
                        userId,
                        false,
                        DBSubscriptionService.Side.RIGHT
                );

        return subscribers.stream().map(userEntity -> UserResponse.builder()
                .id(userEntity.getId())
                .login(userEntity.getLogin())
                .email(userEntity.getEmail())
                .build()).toList();
    }

    public List<UserResponse> getUserSubscriptions(Long userId) {
        List<UserEntity> subscriptions = dbSubscriptionService
                .getSubscriptionMembersOfUserByUserIdAndIsApprovedWithUserSide(
                        userId,
                        false,
                        DBSubscriptionService.Side.LEFT
                );

        return subscriptions.stream().map(userEntity -> UserResponse.builder()
                .id(userEntity.getId())
                .login(userEntity.getLogin())
                .email(userEntity.getEmail())
                .build()).toList();
    }
}
