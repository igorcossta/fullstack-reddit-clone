package com.reddit.spring.controller;

import com.reddit.spring.dto.PostRequest;
import com.reddit.spring.dto.PostResponse;
import com.reddit.spring.service.PostService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/post")
@AllArgsConstructor
public class PostController {
    private final static Logger LOGGER = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody @Valid PostRequest post) {
        postService.save(post);
        LOGGER.debug("método createPost executado" + post.toString());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> findAll() {
        List<PostResponse> post = postService.findAll();
        LOGGER.debug("método findAll executado");
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> findById(@PathVariable Long id) {
        PostResponse post = postService.findById(id);
        LOGGER.debug("método findById executado: " + id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("by-subreddit/{id}")
    public ResponseEntity<List<PostResponse>> findAllBySubredditId(@PathVariable Long id) {
        List<PostResponse> post = postService.findAllBySubredditId(id);
        LOGGER.debug("método findAllBySubredditId executado: " + id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("by-user/{username}")
    public ResponseEntity<List<PostResponse>> findAllByUsername(@PathVariable String username) {
        List<PostResponse> post = postService.findAllByUsername(username);
        LOGGER.debug("método findAllByUsername executado: " + username);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
}
