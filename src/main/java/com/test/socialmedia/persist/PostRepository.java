package com.test.socialmedia.persist;

import com.test.socialmedia.persist.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
