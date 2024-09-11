package com.demo.service;

import com.demo.entity.Blogger;
import com.demo.entity.Tweet;

import java.util.List;
import java.util.Optional;

public interface TweetService {

    Tweet postTweet(Blogger user, String content);

    List<Tweet> getTimeline(Blogger user);

    void likeTweet(Blogger user, Tweet tweet);

    void retweet(Blogger user, Tweet tweet);

    Optional<Tweet> findTweetById(Long id);

}
