package com.reddit.spring.service;

import com.reddit.spring.appuser.AppUser;
import com.reddit.spring.appuser.AppUserRepository;
import com.reddit.spring.appuser.AppUserService;
import com.reddit.spring.dto.CommentDto;
import com.reddit.spring.exception.SpringRedditException;
import com.reddit.spring.mapper.CommentMapper;
import com.reddit.spring.model.Comment;
import com.reddit.spring.model.Post;
import com.reddit.spring.model.Subreddit;
import com.reddit.spring.repository.CommentRepository;
import com.reddit.spring.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CommentService {
    private final PostRepository postRepository;
    private final AppUserRepository userRepository;
    private final AppUserService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;

    public void save(CommentDto commentDTO) {
        Post post = postRepository.findById(commentDTO.getPostId())
                .orElseThrow(() -> new SpringRedditException("post not found"));
        Comment map = commentMapper.map(commentDTO, post, authService.getCurrentUser());
        commentRepository.save(map);

    }

    public List<CommentDto> getAllCommentForPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new SpringRedditException("post not found"));
        AppUser user = post.getUser();
        Subreddit subreddit = post.getSubreddit();
        System.out.println(subreddit.getName() + "\n" + user.getEmail());

        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toUnmodifiableList());
    }

    // TODO: change find by email to find by name
    public List<CommentDto> getAllCommentForUser(String username) {
        AppUser user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
