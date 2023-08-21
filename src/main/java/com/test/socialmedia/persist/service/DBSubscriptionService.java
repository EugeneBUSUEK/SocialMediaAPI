package com.test.socialmedia.persist.service;

import com.test.socialmedia.persist.SubscriptionRepository;
import com.test.socialmedia.persist.entity.SubscriptionEntity;
import com.test.socialmedia.persist.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DBSubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public List<UserEntity> getSubscriptionMembersOfUserByUserIdAndIsApprovedWithUserSide(Long userId, Boolean isApproved, Side side) {
        List<SubscriptionEntity> subscriptions = subscriptionRepository.findSubscriptionEntitiesByUserFromOrUserToEqualsAndIsApprovedIs(userId, isApproved);

        List<UserEntity> subscriptionMembers;
        switch (side) {
            case ANY -> {
                subscriptionMembers = subscriptions.stream().map(subscriptionEntity -> {
                    if (subscriptionEntity.getUserFrom().getId().equals(userId)) {
                        return subscriptionEntity.getUserTo();
                    } else {
                        return subscriptionEntity.getUserFrom();
                    }
                }).toList();
            }
            case LEFT -> {
                subscriptionMembers = subscriptions.stream()
                        .filter(subscriptionEntity -> subscriptionEntity.getUserFrom().getId().equals(userId))
                        .map(SubscriptionEntity::getUserTo).toList();
            }
            case RIGHT -> {
                subscriptionMembers = subscriptions.stream()
                        .filter(subscriptionEntity -> subscriptionEntity.getUserTo().getId().equals(userId))
                        .map(SubscriptionEntity::getUserFrom).toList();
            }
            default -> subscriptionMembers = new ArrayList<>();
        }

        return subscriptionMembers;
    }

    public enum Side {
        LEFT,
        RIGHT,
        ANY
    }
}
