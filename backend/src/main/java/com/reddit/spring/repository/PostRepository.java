package com.reddit.spring.repository;

import com.reddit.spring.model.Post;
import com.reddit.spring.model.Subreddit;
import com.reddit.spring.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllBySubreddit(Pageable pageable, Subreddit subreddit);

    int countAllBySubreddit(Subreddit subreddit);

    Page<Post> findAllByUser(Pageable pageable, User user);

    boolean existsPostByPostName(String postName);

    Page<Post> findAll(Pageable pageable);
}
