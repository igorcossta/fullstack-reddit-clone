package com.reddit.spring.service;

import com.reddit.spring.dto.CommentRequest;
import com.reddit.spring.dto.CommentResponse;
import com.reddit.spring.exception.SpringRedditException;
import com.reddit.spring.mapper.CommentMapper;
import com.reddit.spring.model.AppUser;
import com.reddit.spring.model.Comment;
import com.reddit.spring.model.Post;
import com.reddit.spring.repository.CommentRepository;
import com.reddit.spring.repository.PostRepository;
import com.reddit.spring.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;

    public void save(CommentRequest commentDTO) {
        Post post = postRepository.findById(commentDTO.getPostId())
                .orElseThrow(() -> new SpringRedditException("post not found"));
        Comment map = commentMapper.map(commentDTO, post, userService.getCurrentUser());
        commentRepository.save(map);
    }

    public List<CommentResponse> findAllCommentByPostId(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new SpringRedditException("post not found"));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toUnmodifiableList());
    }

    // TODO: change find by email to find by name
    public List<CommentResponse> findAllCommentByUsername(String username) {
        AppUser user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
