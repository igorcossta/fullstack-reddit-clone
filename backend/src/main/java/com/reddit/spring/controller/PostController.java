package com.reddit.spring.controller;

import com.reddit.spring.dto.Error;
import com.reddit.spring.dto.PostRequest;
import com.reddit.spring.dto.PostResponse;
import com.reddit.spring.service.PostService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "${PostController.create.value}", notes = "${PostController.create.notes}", nickname = "${PostController.create.nickname}")
    @ApiResponses({
            @ApiResponse(code = 201, message = "post successfully created"),
            @ApiResponse(code = 400, message = "client bad request", response = Error.class),
            @ApiResponse(code = 401, message = "client bad credentials", response = Error.class),
            @ApiResponse(code = 403, message = "client does not have permission", response = Error.class)})
    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody @Valid PostRequest post) {
        postService.save(post);
        LOGGER.debug("método createPost executado" + post.toString());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "${PostController.findAll.value}", notes = "${PostController.findAll.notes}", nickname = "${PostController.findAll.nickname}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "request accepted successfully", response = PostResponse[].class),
            @ApiResponse(code = 400, message = "client bad request", response = Error.class),
            @ApiResponse(code = 401, message = "client bad credentials", response = Error.class),
            @ApiResponse(code = 403, message = "client does not have permission", response = Error.class)})
    @GetMapping
    public ResponseEntity<List<PostResponse>> findAll() {
        List<PostResponse> post = postService.findAll();
        LOGGER.debug("método findAll executado");
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @ApiOperation(value = "${PostController.findById.value}", notes = "${PostController.findById.notes}", nickname = "${PostController.findById.nickname}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "request accepted successfully", response = PostResponse.class),
            @ApiResponse(code = 400, message = "client bad request", response = Error.class),
            @ApiResponse(code = 401, message = "client bad credentials", response = Error.class),
            @ApiResponse(code = 403, message = "client does not have permission", response = Error.class),
            @ApiResponse(code = 404, message = "resource not found", response = Error.class)})
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> findById(@PathVariable Long id) {
        PostResponse post = postService.findById(id);
        LOGGER.debug("método findById executado: " + id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @ApiOperation(value = "${PostController.findAllBySubredditId.value}", notes = "${PostController.findAllBySubredditId.notes}", nickname = "${PostController.findAllBySubredditId.nickname}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "request accepted successfully", response = PostResponse[].class),
            @ApiResponse(code = 400, message = "client bad request", response = Error.class),
            @ApiResponse(code = 401, message = "client bad credentials", response = Error.class),
            @ApiResponse(code = 403, message = "client does not have permission", response = Error.class),
            @ApiResponse(code = 404, message = "resource not found", response = Error.class)})
    @GetMapping("by-subreddit/{id}")
    public ResponseEntity<List<PostResponse>> findAllBySubredditId(@PathVariable Long id) {
        List<PostResponse> post = postService.findAllBySubredditId(id);
        LOGGER.debug("método findAllBySubredditId executado: " + id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @ApiOperation(value = "${PostController.findAllByUsername.value}", notes = "${PostController.findAllByUsername.notes}", nickname = "${PostController.findAllByUsername.nickname}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "request accepted successfully", response = PostResponse[].class),
            @ApiResponse(code = 400, message = "client bad request", response = Error.class),
            @ApiResponse(code = 401, message = "client bad credentials", response = Error.class),
            @ApiResponse(code = 403, message = "client does not have permission", response = Error.class),
            @ApiResponse(code = 404, message = "resource not found", response = Error.class)})
    @GetMapping("by-user/{username}")
    public ResponseEntity<List<PostResponse>> findAllByUsername(@PathVariable String username) {
        List<PostResponse> post = postService.findAllByUsername(username);
        LOGGER.debug("método findAllByUsername executado: " + username);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
}
