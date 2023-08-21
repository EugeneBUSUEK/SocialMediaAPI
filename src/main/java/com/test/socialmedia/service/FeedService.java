package com.test.socialmedia.service;

import com.test.socialmedia.model.response.PageableResponse;
import com.test.socialmedia.model.response.PostResponse;
import com.test.socialmedia.model.response.UserPostResponse;
import com.test.socialmedia.persist.entity.PostEntity;
import com.test.socialmedia.persist.entity.UserEntity;
import com.test.socialmedia.persist.service.DBPostService;
import com.test.socialmedia.persist.service.DBSubscriptionService;
import com.test.socialmedia.persist.service.DBUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {

    private final DBUserService dbUserService;
    private final DBSubscriptionService dbSubscriptionService;
    private final DBPostService dbPostService;

    public PageableResponse<PostResponse> getUserFeed(
            UserDetails userDetails,
            String dateSort,
            Integer pageNumber,
            Integer pageSize
    ) {
        UserEntity user = dbUserService.getUserByLogin(userDetails.getUsername());
        List<UserEntity> users = dbSubscriptionService
                .getSubscriptionMembersOfUserByUserIdAndIsApprovedWithUserSide(
                        user.getId(),
                        null,
                        DBSubscriptionService.Side.LEFT
                );
        Page<PostEntity> page = dbPostService.getUsersPosts(
                users.stream().map(UserEntity::getId).toList(),
                dateSort,
                pageNumber,
                pageSize
        );

        return new PageableResponse<PostResponse>()
                .setContent(page.get().map(postEntity -> {
                    UserPostResponse postAuthor = UserPostResponse.builder()
                            .id(postEntity.getUser().getId())
                            .login(postEntity.getUser().getLogin())
                            .build();

                    return PostResponse.builder()
                            .id(postEntity.getId())
                            .title(postEntity.getTitle())
                            .text(postEntity.getText())
                            .photoLink(postEntity.getPhotoLink())
                            .author(postAuthor)
                            .build();
                }).toList())
                .setPage(page.getNumber())
                .setTotalPages(page.getTotalPages());
    }
}
