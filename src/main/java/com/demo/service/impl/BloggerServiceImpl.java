package com.demo.service.impl;

import com.demo.entity.Blogger;
import com.demo.repository.BloggerJpaRepository;
import com.demo.service.BloggerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BloggerServiceImpl implements BloggerService {

    private final BloggerJpaRepository bloggerJpaRepository;
    private final PasswordEncoder passwordEncoder;

    public BloggerServiceImpl(BloggerJpaRepository bloggerJpaRepository, PasswordEncoder passwordEncoder) {
        this.bloggerJpaRepository = bloggerJpaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Blogger registerUser(String username, String password) {
        Blogger blogger = Blogger.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
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

    @Override
    public List<Blogger> findAllBloggers() {
        PageRequest pageable = PageRequest.of(0, 20, Sort.by("username").ascending());
        Page<Blogger> bloggers = this.bloggerJpaRepository.findAllBloggers(pageable);
        return bloggers.getContent();
    }
}
