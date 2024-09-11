package com.demo.service.impl;

import com.demo.entity.Blogger;
import com.demo.entity.Tweet;
import com.demo.repository.TweetJpaRepository;
import com.demo.service.TweetService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetServiceImpl implements TweetService {

    private final TweetJpaRepository tweetJpaRepository;

    public TweetServiceImpl(TweetJpaRepository tweetJpaRepository) {
        this.tweetJpaRepository = tweetJpaRepository;
    }

    @Override
    public Tweet postTweet(Blogger blogger, String content) {
        Tweet tweet = Tweet.builder()
                .blogger(blogger)
                .content(content)
                .build();
        return tweetJpaRepository.saveAndFlush(tweet);
    }

    @Override
    public List<Tweet> getTimeline(Blogger user) {
        return tweetJpaRepository.findByBloggerInOrderByCreatedAtDesc(
                user.getFollowees().stream().toList()
        );
    }

    @Override
    public void likeTweet(Blogger user, Tweet tweet) {
        tweet.getLikedBy().add(user);
        tweetJpaRepository.saveAndFlush(tweet);
    }

    @Override
    public void retweet(Blogger user, Tweet tweet) {
        tweet.getRetweetedBy().add(user);
        tweetJpaRepository.saveAndFlush(tweet);
    }

    @Override
    public Optional<Tweet> findTweetById(Long id) {
        return this.tweetJpaRepository.findById(id);
    }
}
