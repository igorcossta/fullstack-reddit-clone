package com.reddit.spring.controller;

import com.reddit.spring.dto.CommentDto;
import com.reddit.spring.service.CommentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@AllArgsConstructor
public class CommentController {
    private final static Logger LOGGER = LoggerFactory.getLogger(CommentController.class);
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> saveComment(@RequestBody CommentDto comment) {
        commentService.save(comment);
        LOGGER.debug("saving new comment: " + comment.toString());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/by-post/{id}")
    public ResponseEntity<List<CommentDto>> findAllCommentByPostId(@PathVariable Long id) {
        List<CommentDto> comment = commentService.findAllCommentByPostId(id);
        LOGGER.debug("listing all comment by post id: " + id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("/by-user/{username}")
    public ResponseEntity<List<CommentDto>> findAllCommentByUsername(@PathVariable String username) {
        List<CommentDto> comment = commentService.findAllCommentByUsername(username);
        LOGGER.debug("listing all comment by username: " + username);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

}
