package com.demo.controllers;

import com.demo.entity.Blogger;
import com.demo.service.BloggerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/bloggers")
public class BloggerController {

    private final BloggerService bloggerService;


    public BloggerController(BloggerService bloggerService) {
        this.bloggerService = bloggerService;
    }

    @PostMapping(
            value = "/{username}/follow",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<String> followUser(@PathVariable(value = "username") String username,
                                             @RequestParam(value = "followeeUsername") String followeeUsername) {
        Optional<Blogger> follower = bloggerService.findUserByUsername(username);
        Optional<Blogger> followee = bloggerService.findUserByUsername(followeeUsername);

        if(follower.isPresent() && followee.isPresent()) {
            bloggerService.followUser(follower.get(), followee.get());
            return ResponseEntity.ok("User " + username + " followed user " + followeeUsername);
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(
            value = "/{username}/unfollow",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<String> unfollowUser(@PathVariable(value = "username") String username,
                                               @RequestParam(value = "followeeUsername")  String followeeUsername) {
        Optional<Blogger> follower = bloggerService.findUserByUsername(username);
        Optional<Blogger> followee = bloggerService.findUserByUsername(followeeUsername);

        if(follower.isPresent() && followee.isPresent()) {
            bloggerService.unfollowUser(follower.get(), followee.get());
            return ResponseEntity.ok("User " + username + " unfollowed user " + followeeUsername);
        }

        return ResponseEntity.badRequest().build();

    }
}
