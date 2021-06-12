package com.reddit.spring.repository;

import com.reddit.spring.appuser.AppUser;
import com.reddit.spring.model.Post;
import com.reddit.spring.model.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);
    List<Post> findByUser(AppUser user);
}
