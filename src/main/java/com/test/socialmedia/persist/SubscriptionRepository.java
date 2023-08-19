package com.test.socialmedia.persist;

import com.test.socialmedia.persist.entity.SubscriptionEntity;
import com.test.socialmedia.persist.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {
    @Query(value = """
select s.* from tbl_subscription s where s.is_approved = true and (s.user_from = :userId or s.user_to = :userId)
""",
    nativeQuery = true,
    countQuery = """
select count(s.*) from tbl_subscription s where s.is_approved = true and (s.user_from = :userId or s.user_to = :userId)
""")
    Page<SubscriptionEntity> findSubscriptionEntitiesByUserFromOrUserToEqualsAndIsApprovedIsTrue(Long userId, Pageable pageable);

    Page<SubscriptionEntity> findSubscriptionEntitiesByUserFromIsAndIsApprovedIsFalse(UserEntity userFrom, Pageable pageable);
    Page<SubscriptionEntity> findSubscriptionEntitiesByUserToIsAndIsApprovedIsFalse(UserEntity userTo, Pageable pageable);

    @Query(value = """
select s.* from tbl_subscription s where s.user_from = :userId or s.user_to = :userId
""",
    nativeQuery = true)
    Optional<SubscriptionEntity> findByUserFromOrUserToIs(Long userId);
}
