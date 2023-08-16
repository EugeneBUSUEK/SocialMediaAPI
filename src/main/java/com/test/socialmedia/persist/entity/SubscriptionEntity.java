package com.test.socialmedia.persist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tblSubscription",
        uniqueConstraints = {
                @UniqueConstraint(name = "UniqueUserFromAndUserTo", columnNames = {"user_from", "user_to"})
        })
public class SubscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_from")
    private UserEntity userFrom;

    @ManyToOne
    @JoinColumn(name = "user_to")
    private UserEntity userTo;

    private Boolean isApproved = false;
}
