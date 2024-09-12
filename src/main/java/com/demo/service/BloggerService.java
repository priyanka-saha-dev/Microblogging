package com.demo.service;

import com.demo.entity.Blogger;

import java.util.List;
import java.util.Optional;

public interface BloggerService {

    Blogger registerUser(String username, String password);

    Optional<Blogger> findUserByUsername(String username);

    void followUser(Blogger follower, Blogger followee);

    void unfollowUser(Blogger follower, Blogger followee);

    List<Blogger> findAllBloggers();

}
