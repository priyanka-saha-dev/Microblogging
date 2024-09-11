package com.demo.service;

import com.demo.entity.Blogger;
import com.demo.repository.BloggerJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final BloggerJpaRepository bloggerJpaRepository;

    public CustomUserDetailsService(BloggerJpaRepository bloggerJpaRepository) {
        this.bloggerJpaRepository = bloggerJpaRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Blogger> blogger = Optional.ofNullable(bloggerJpaRepository.findByUsername(username));

        return blogger.map(b -> new org.springframework.security.core.userdetails.User(
                b.getUsername(),
                b.getPassword(),
                new ArrayList<>()  // Add roles or authorities if required
        )).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
