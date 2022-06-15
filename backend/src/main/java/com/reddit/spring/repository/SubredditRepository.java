package com.reddit.spring.repository;

import com.reddit.spring.model.Subreddit;
import com.reddit.spring.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit, Long> {

    Optional<Subreddit> findByName(String name);

    boolean existsSubredditByName(String name);

    Page<Subreddit> findAllByUser(Pageable pageable, User user);

    Page<Subreddit> findAll(Pageable pageable);
}
