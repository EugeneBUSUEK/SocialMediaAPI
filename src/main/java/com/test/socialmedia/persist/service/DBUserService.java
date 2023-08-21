package com.test.socialmedia.persist.service;

import com.test.socialmedia.exception.NotFoundException;
import com.test.socialmedia.persist.UserRepository;
import com.test.socialmedia.persist.entity.UserEntity;
import com.test.socialmedia.support.constraint.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DBUserService {

    private final UserRepository userRepository;

    public UserEntity getUserByLogin(String userLogin) {
        return userRepository.findByLogin(userLogin)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.USER_BY_EMAIL_NOT_FUND, userLogin));
    }
}
