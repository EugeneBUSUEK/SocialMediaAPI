package com.test.socialmedia.persist;

import com.test.socialmedia.persist.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    Page<PostEntity> findByUserIdIs(Long user_id, Pageable pageable);
}
