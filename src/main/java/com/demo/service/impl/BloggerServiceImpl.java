package com.demo.service.impl;

import com.demo.entity.Blogger;
import com.demo.repository.BloggerJpaRepository;
import com.demo.service.BloggerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BloggerServiceImpl implements BloggerService {

    private final BloggerJpaRepository bloggerJpaRepository;

    public BloggerServiceImpl(BloggerJpaRepository bloggerJpaRepository) {
        this.bloggerJpaRepository = bloggerJpaRepository;
    }

    @Override
    public Blogger registerUser(String username, String password) {
        Blogger blogger = Blogger.builder()
                .username(username)
                .password(password)
                .build();
        return bloggerJpaRepository.saveAndFlush(blogger);
    }

    @Override
    public Optional<Blogger> findUserByUsername(String username) {
        Blogger blogger = bloggerJpaRepository.findByUsername(username);
        return Optional.ofNullable(blogger);
    }

    @Override
    public void followUser(Blogger follower, Blogger followee) {
        follower.getFollowees().add(followee);
        followee.getFollowers().add(follower);
        bloggerJpaRepository.saveAndFlush(follower);
        bloggerJpaRepository.saveAndFlush(followee);
    }

    @Override
    public void unfollowUser(Blogger follower, Blogger followee) {
        follower.getFollowees().remove(followee);
        followee.getFollowers().remove(follower);
        bloggerJpaRepository.saveAndFlush(follower);
        bloggerJpaRepository.saveAndFlush(followee);
    }
}
