package com.reddit.spring.repository;

import com.reddit.spring.model.AppUser;
import com.reddit.spring.model.Post;
import com.reddit.spring.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, AppUser currentUser);
}
