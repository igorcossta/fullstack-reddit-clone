package com.reddit.spring.controller;

import com.reddit.spring.dto.PostRequest;
import com.reddit.spring.dto.PostResponse;
import com.reddit.spring.service.PostService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "create post",
            notes = "this endpoint create a new post",
            nickname = "createNewPost")
    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody @Valid PostRequest post) {
        postService.save(post);
        LOGGER.debug("método createPost executado" + post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "find all post", notes = "this endpoint find all post of whole subreddit", nickname = "findAllPost")
    @GetMapping
    public ResponseEntity<List<PostResponse>> findAll() {
        List<PostResponse> post = postService.findAll();
        LOGGER.debug("método findAll executado");
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @ApiOperation(value = "find post by id", notes = "this endpoint find post by id", nickname = "findByIdPost")
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> findById(@PathVariable Long id) {
        PostResponse post = postService.findById(id);
        LOGGER.debug("método findById executado: " + id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @ApiOperation(value = "find all post by subreddit id", notes = "this endpoint find all post by subreddit id", nickname = "findAllBySubredditId")
    @GetMapping("by-subreddit/{id}")
    public ResponseEntity<List<PostResponse>> findAllBySubredditId(@PathVariable Long id) {
        List<PostResponse> post = postService.findAllBySubredditId(id);
        LOGGER.debug("método findAllBySubredditId executado: " + id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @ApiOperation(value = "find all post by username", notes = "this endpoint find all post by username", nickname = "findAllByUsername")
    @GetMapping("by-user/{username}")
    public ResponseEntity<List<PostResponse>> findAllByUsername(@PathVariable String username) {
        List<PostResponse> post = postService.findAllByUsername(username);
        LOGGER.debug("método findAllByUsername executado: " + username);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
}
