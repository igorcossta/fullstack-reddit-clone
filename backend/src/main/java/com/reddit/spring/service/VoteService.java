package com.reddit.spring.service;

import com.reddit.spring.dto.VoteDto;
import com.reddit.spring.exception.PostNotFoundException;
import com.reddit.spring.exception.SpringRedditException;
import com.reddit.spring.model.Post;
import com.reddit.spring.model.Vote;
import com.reddit.spring.model.VoteType;
import com.reddit.spring.repository.PostRepository;
import com.reddit.spring.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final UserService userService;

    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(format("post %s not found ", voteDto.getPostId())));
        Optional<Vote> vote = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, userService.getCurrentUser());
        if (vote.isPresent() && vote.get().getVoteType().equals(voteDto.getVoteType())) {
            throw new SpringRedditException("you have already voted on this post");
        }
        if (VoteType.UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }

        voteRepository.save(mapToVote(voteDto, post));
        postRepository.save(post);
    }

    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(userService.getCurrentUser())
                .build();
    }
}
