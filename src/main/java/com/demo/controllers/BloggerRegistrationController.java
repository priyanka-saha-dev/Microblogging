package com.demo.controllers;

import com.demo.config.JwtTokenUtil;
import com.demo.entity.Blogger;
import com.demo.model.BloggerAuthResponse;
import com.demo.model.BloggerRegistrationRequest;
import com.demo.service.BloggerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/bloggers/auth")
public class BloggerRegistrationController {

    private final BloggerService bloggerService;

    private final JwtTokenUtil jwtTokenUtil;

    private final AuthenticationManager authenticationManager;


    public BloggerRegistrationController(BloggerService bloggerService, JwtTokenUtil jwtTokenUtil,
                                         AuthenticationManager authenticationManager) {
        this.bloggerService = bloggerService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(
            value = "/register",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Blogger> registerUser(@RequestBody BloggerRegistrationRequest bloggerRegistrationRequest) {
        Blogger blogger = bloggerService.registerUser(bloggerRegistrationRequest.getUsername(), bloggerRegistrationRequest.getPassword());
        return ResponseEntity.ok(blogger);
    }

    @PostMapping(
            value = "/token",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<BloggerAuthResponse> generateAuthToken(@RequestBody BloggerRegistrationRequest authRequest) {
        Authentication result = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        BloggerAuthResponse registrationResponse = Optional.ofNullable(result).map(Authentication::getPrincipal).map(principal -> {
            User user = (User) principal;
            String token = "Bearer " + jwtTokenUtil.generateToken(user.getUsername());
            return BloggerAuthResponse.builder()
                    .token(token)
                    .build();
        }).orElse(BloggerAuthResponse.builder().build());

        return ResponseEntity.ok(registrationResponse);
    }

}
