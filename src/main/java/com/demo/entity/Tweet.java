package com.demo.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Tweet")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "TWEET_RELATIONS",
            joinColumns = @JoinColumn(name = "BLOGGER_ID"),
            inverseJoinColumns = @JoinColumn(name = "TWEET_ID")
    )
    private Blogger blogger;

    private String content;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "TWEET_RELATIONS",
            joinColumns = @JoinColumn(name = "LIKED_BY_ID", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "TWEET_ID", nullable = false)
    )
    private Set<Blogger> likedBy = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "TWEET_RELATIONS",
            joinColumns = @JoinColumn(name = "RETWEET_ID", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "TWEET_ID", nullable = false)
    )
    private Set<Blogger> retweetedBy = new HashSet<>();

}
