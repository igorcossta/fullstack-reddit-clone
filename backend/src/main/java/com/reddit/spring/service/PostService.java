package com.reddit.spring.service;

import com.reddit.spring.dto.PostRequest;
import com.reddit.spring.dto.PostResponse;
import com.reddit.spring.exception.PostExistsException;
import com.reddit.spring.exception.PostNotFoundException;
import com.reddit.spring.exception.SubredditNotFoundException;
import com.reddit.spring.mapper.PostMapper;
import com.reddit.spring.model.Post;
import com.reddit.spring.model.Subreddit;
import com.reddit.spring.model.User;
import com.reddit.spring.repository.PostRepository;
import com.reddit.spring.repository.SubredditRepository;
import com.reddit.spring.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.data.domain.PageRequest.of;

@Service
@AllArgsConstructor
@Transactional
public class PostService {
    private final SubredditRepository subredditRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final UserService userService;
    private final PostMapper postMapper;

    public void save(PostRequest postRequest) {
        boolean present = postRepository.existsPostByPostName(postRequest.getPostName());
        if (present) throw new PostExistsException("the post already exists");

        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName()).orElseThrow(() -> new SubredditNotFoundException("subreddit cannot be found"));
        Post post = postMapper.map(postRequest, subreddit, userService.getCurrentUser());
        postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> findAll(Integer page) {
        return postRepository.findAll(of(page, 10)).stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public PostResponse findById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("post cannot be found"));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> findAllBySubredditId(Long subredditId, Integer page) {
        Subreddit subreddit = subredditRepository.findById(subredditId).orElseThrow(() -> new SubredditNotFoundException("subreddit cannot be found"));
        List<Post> posts = postRepository.findAllBySubreddit(of(page, 10), subreddit).stream().collect(toList());
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    // TODO: change find by email to find by name
    @Transactional(readOnly = true)
    public List<PostResponse> findAllByUsername(String username, Integer page) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("username cannot be found"));
        return postRepository.findAllByUser(of(page, 10), user).stream().map(postMapper::mapToDto).collect(toList());
    }
}
