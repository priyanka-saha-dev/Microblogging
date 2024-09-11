package com.demo.repository;

import com.demo.entity.Blogger;
import com.demo.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetJpaRepository extends JpaRepository<Tweet, Long> {
    List<Tweet> findByBloggerInOrderByCreatedAtDesc(List<Blogger> followees);
}
