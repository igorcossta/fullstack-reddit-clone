package com.reddit.spring.repository;

import com.reddit.spring.model.Subreddit;
import com.reddit.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit, Long> {
    Optional<Subreddit> findByName(String subredditName);

    List<Subreddit> findAllByUser(User user);
}
