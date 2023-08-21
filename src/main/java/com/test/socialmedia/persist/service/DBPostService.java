package com.test.socialmedia.persist.service;

import com.test.socialmedia.persist.PostRepository;
import com.test.socialmedia.persist.entity.PostEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class DBPostService {

    private final PostRepository postRepository;

    public Page<PostEntity> getUsersPosts(Collection<Long> userIds, String dateSort, Integer pageNumber, Integer pageSize) {
        Sort.Direction direction;
        try {
            direction = Sort.Direction.fromString(dateSort);
        } catch (Exception e) {
            throw new RuntimeException("Invalid direction (ASC/DESC)"); //TODO exception
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, direction, "createdWhen");
        return postRepository.findByUserIdIn(userIds, pageable);
    }
}
