package com.reddit.spring.service;

import com.reddit.spring.dto.CommentDto;
import com.reddit.spring.exception.SpringRedditException;
import com.reddit.spring.mapper.CommentMapper;
import com.reddit.spring.model.*;
import com.reddit.spring.repository.CommentRepository;
import com.reddit.spring.repository.PostRepository;
import com.reddit.spring.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CommentService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MailContentBuild mailContentBuild;
    private final MailService mailService;

    public void save(CommentDto commentDTO) {
        Post post = postRepository.findById(commentDTO.getPostId())
                .orElseThrow(() -> new SpringRedditException("post not found"));
        Comment map = commentMapper.map(commentDTO, post, authService.getCurrentUser());
        commentRepository.save(map);

//        String message = mailContentBuild.build(post.getUser().getUsername() + " posted a comment on your post.");
//        sendCommentNotification(message, post.getUser());
    }

    private void sendCommentNotification(String msg, User user) {
        mailService.sendMail(new NotificationEmail(
                user.getUsername() + " Commented on your post",
                user.getEmail(),
                msg
        ));
    }

    public List<CommentDto> getAllCommentForPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new SpringRedditException("post not found"));
        User user = post.getUser();
        Subreddit subreddit = post.getSubreddit();
        System.out.println(subreddit.getName() + "\n" + user.getUsername());

        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<CommentDto> getAllCommentForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
