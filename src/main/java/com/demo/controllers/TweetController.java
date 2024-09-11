package com.demo.controllers;

import com.demo.entity.Blogger;
import com.demo.entity.Tweet;
import com.demo.model.TweetRequest;
import com.demo.service.BloggerService;
import com.demo.service.TweetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tweets")
public class TweetController {

    private final TweetService tweetService;
    private final BloggerService bloggerService;

    public TweetController(TweetService tweetService, BloggerService bloggerService) {
        this.tweetService = tweetService;
        this.bloggerService = bloggerService;
    }

    @PostMapping(
            value = "/{username}/post",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Tweet> postTweet(@PathVariable(value = "username") String username,
                                           @RequestBody TweetRequest tweetRequest) {
        Optional<Blogger> blogger = bloggerService.findUserByUsername(username);
        if(blogger.isPresent()) {
            Tweet tweet = tweetService.postTweet(blogger.get(), tweetRequest.getContent());
            return ResponseEntity.ok(tweet);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping(
            value = "/{username}/timeline",
            produces = "application/json"
    )
    public ResponseEntity<List<Tweet>> getTimeline(@PathVariable(value = "username") String username) {
        Optional<Blogger> blogger = bloggerService.findUserByUsername(username);
        if(blogger.isPresent()) {
            List<Tweet> tweets = tweetService.getTimeline(blogger.get());
            return ResponseEntity.ok(tweets);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(
            value = "/{tweetId}/like",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<String> likeTweet(@PathVariable(value = "tweetId") Long tweetId,
                                            @RequestParam(value = "username") String username) {
        Optional<Blogger> blogger = bloggerService.findUserByUsername(username);
        Optional<Tweet> tweet = tweetService.findTweetById(tweetId);
        if(blogger.isPresent() && tweet.isPresent()) {
            tweetService.likeTweet(blogger.get(), tweet.get());
            return ResponseEntity.ok("User " + username + " liked tweet " + tweetId);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping(
            value = "/{tweetId}/retweet",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<String> retweet(@PathVariable(value = "tweetId") Long tweetId,
                                          @RequestParam(value = "username") String username) {

        Optional<Blogger> blogger = bloggerService.findUserByUsername(username);
        Optional<Tweet> tweet = tweetService.findTweetById(tweetId);
        if(blogger.isPresent() && tweet.isPresent()) {
            tweetService.retweet(blogger.get(), tweet.get());
            return ResponseEntity.ok("User " + username + " retweeted tweet " + tweetId);
        }
        return ResponseEntity.badRequest().build();
    }

}
