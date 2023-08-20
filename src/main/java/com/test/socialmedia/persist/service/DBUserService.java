package com.test.socialmedia.persist.service;

import com.test.socialmedia.persist.UserRepository;
import com.test.socialmedia.persist.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DBUserService {

    private final UserRepository userRepository;

    public UserEntity getUserByLogin(String userLogin) {
        return userRepository.findByLogin(userLogin)
                .orElseThrow(() -> new RuntimeException("User with login " + userLogin + "not found"));
    }
}
