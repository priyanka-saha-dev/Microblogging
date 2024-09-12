package com.demo.repository;

import com.demo.entity.Blogger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloggerJpaRepository extends JpaRepository<Blogger, Long> {
    Blogger findByUsername(String username);

    Page<Blogger> findAllBloggers(Pageable pageable);
}
