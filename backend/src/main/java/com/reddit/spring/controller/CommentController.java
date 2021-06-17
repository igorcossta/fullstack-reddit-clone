package com.reddit.spring.controller;

import com.reddit.spring.dto.CommentRequest;
import com.reddit.spring.dto.CommentResponse;
import com.reddit.spring.service.CommentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
@AllArgsConstructor
public class CommentController {
    private final static Logger LOGGER = LoggerFactory.getLogger(CommentController.class);
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody @Valid CommentRequest comment) {
        commentService.save(comment);
        LOGGER.debug("método createComment executado: " + comment.toString());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/by-post/{id}")
    public ResponseEntity<List<CommentResponse>> findAllCommentByPostId(@PathVariable Long id) {
        List<CommentResponse> comment = commentService.findAllCommentByPostId(id);
        LOGGER.debug("método findAllCommentByPostId executado: " + id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("/by-user/{username}")
    public ResponseEntity<List<CommentResponse>> findAllCommentByUsername(@PathVariable String username) {
        List<CommentResponse> comment = commentService.findAllCommentByUsername(username);
        LOGGER.debug("método findAllCommentByUsername executado: " + username);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

}
