package com.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Blogger")
public class Blogger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "BLOGGER_RELATIONS",
            joinColumns = @JoinColumn(name = "FOLLOWER_ID", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "BLOGGER_ID", nullable = false)
    )
    private Set<Blogger> followers = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "BLOGGER_RELATIONS",
            joinColumns = @JoinColumn(name = "FOLLOWEE_ID", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "BLOGGER_ID", nullable = false)
    )
    private Set<Blogger> followees = new HashSet<>();

}
