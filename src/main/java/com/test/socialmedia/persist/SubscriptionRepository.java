package com.test.socialmedia.persist;

import com.test.socialmedia.persist.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {
    @Query(value = """
            select s.* from tbl_subscription s where s.is_approved = coalesce(:isApproved, s.is_approved) and (s.user_from = :userId or s.user_to = :userId)
            """,
            nativeQuery = true)
    List<SubscriptionEntity> findSubscriptionEntitiesByUserFromOrUserToEqualsAndIsApprovedIs(Long userId, Boolean isApproved);

    @Query(value = """
            select s.* from tbl_subscription s where s.user_from = :userId or s.user_to = :userId
            """,
            nativeQuery = true)
    Optional<SubscriptionEntity> findByUserFromOrUserToIs(Long userId);

    @Query(value = """
            select s.* from tbl_subscription s 
            where (s.user_from = :userFrom_id or s.user_from = :userTo_id)
            and (s.user_to = :userFrom_id or s.user_to = :userTo_id)
            """,
            nativeQuery = true)
    Optional<SubscriptionEntity> findByUserFromIdAndUserToIdIn(Long userFrom_id, Long userTo_id);
}
