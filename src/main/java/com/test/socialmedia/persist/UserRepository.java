package com.test.socialmedia.persist;

import com.test.socialmedia.persist.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
