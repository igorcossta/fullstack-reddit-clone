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

import java.util.List;

import static java.lang.String.format;

@RestController
@RequestMapping("/api/post")
@AllArgsConstructor
public class PostController {
    private final static Logger LOGGER = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> savePost(@RequestBody PostRequest post) {
        postService.save(post);
        LOGGER.debug("saving new post: " + post.toString());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> findAll() {
        List<PostResponse> post = postService.findAll();
        LOGGER.debug("listing all post");
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> findById(@PathVariable Long id) {
        PostResponse post = postService.findById(id);
        LOGGER.debug(format("listing specific post: %s ", id) + post.toString());
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("by-subreddit/{id}")
    public ResponseEntity<List<PostResponse>> findAllBySubredditId(@PathVariable Long id) {
        List<PostResponse> post = postService.findAllBySubredditId(id);
        LOGGER.debug("listing all post by subreddit id: " + id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("by-user/{username}")
    public ResponseEntity<List<PostResponse>> findAllByUsername(@PathVariable String username) {
        List<PostResponse> post = postService.findAllByUsername(username);
        LOGGER.debug("listing all post by username: " + username);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
}
