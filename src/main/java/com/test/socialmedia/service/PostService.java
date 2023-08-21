package com.test.socialmedia.service;

import com.test.socialmedia.exception.InaccessibleOperationException;
import com.test.socialmedia.exception.NotFoundException;
import com.test.socialmedia.model.request.PostRequest;
import com.test.socialmedia.model.response.PageableResponse;
import com.test.socialmedia.model.response.PostResponse;
import com.test.socialmedia.model.response.UserPostResponse;
import com.test.socialmedia.persist.PostRepository;
import com.test.socialmedia.persist.entity.PostEntity;
import com.test.socialmedia.persist.entity.UserEntity;
import com.test.socialmedia.persist.service.DBPostService;
import com.test.socialmedia.persist.service.DBUserService;
import com.test.socialmedia.support.constraint.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final DBPostService dbPostService;
    private final DBUserService dbUserService;


    public PostResponse createUserPost(PostRequest postRequest, UserDetails userDetails) {
        UserEntity author = dbUserService.getUserByLogin(userDetails.getUsername());
        PostEntity postEntity = new PostEntity()
                .setTitle(postRequest.getTitle())
                .setText(postRequest.getText())
                .setPhotoLink(postRequest.getPhotoLink())
                .setUser(author);
        postEntity = postRepository.save(postEntity);

        UserPostResponse postAuthor = UserPostResponse.builder()
                .id(author.getId())
                .login(author.getLogin())
                .build();

        return PostResponse.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .text(postEntity.getText())
                .photoLink(postEntity.getPhotoLink())
                .author(postAuthor)
                .build();
    }

    public PostResponse updateUserPost(Long postId, PostRequest postRequest, UserDetails userDetails) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.POST_BY_ID_NOT_FUND, postId));
        UserEntity author = dbUserService.getUserByLogin(userDetails.getUsername());

        if (!author.getId().equals(postEntity.getUser().getId())) {
            throw new InaccessibleOperationException(ErrorMessages.INACCESSIBLE_OPERATION);
        }

        postEntity.setText(postRequest.getText() != null ? postRequest.getText() : postEntity.getText())
                .setTitle(postRequest.getTitle() != null ? postRequest.getTitle() : postEntity.getTitle())
                .setPhotoLink(postRequest.getPhotoLink() != null ? postRequest.getPhotoLink() : postEntity.getPhotoLink())
                .setCreatedWhen(System.currentTimeMillis());

        postRepository.save(postEntity);

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
    }

    public void deleteUserPost(Long postId, UserDetails userDetails) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.POST_BY_ID_NOT_FUND, postId));
        UserEntity author = dbUserService.getUserByLogin(userDetails.getUsername());

        if (!author.getId().equals(postEntity.getUser().getId())) {
            throw new InaccessibleOperationException(ErrorMessages.INACCESSIBLE_OPERATION);
        }

        postRepository.delete(postEntity);
    }

    public PageableResponse<PostResponse> getUserPosts(UserDetails userDetails, String dateSort, Integer pageNumber, Integer pageSize) {
        UserEntity user = dbUserService.getUserByLogin(userDetails.getUsername());
        Page<PostEntity> page = dbPostService.getUsersPostsByUserIds(List.of(user.getId()), dateSort, pageNumber, pageSize);

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
