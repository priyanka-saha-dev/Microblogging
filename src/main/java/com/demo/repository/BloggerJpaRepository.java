package com.demo.repository;

import com.demo.entity.Blogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloggerJpaRepository extends JpaRepository<Blogger, Long> {
    Blogger findByUsername(String username);
}
