package com.reddit.spring.controller;

import com.reddit.spring.dto.CommentDto;
import com.reddit.spring.model.Comment;
import com.reddit.spring.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentDto commentDTO) {
        commentService.save(commentDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/by-post/{id}")
    public ResponseEntity<List<CommentDto>> getAllCommentForPost(@PathVariable Long id) {
        List<CommentDto> allCommentForPost = commentService.getAllCommentForPost(id);
        return ResponseEntity.status(HttpStatus.OK).body(allCommentForPost);
    }

    @GetMapping("/by-user/{username}")
    public ResponseEntity<List<CommentDto>> getAllCommentForUser(@PathVariable String username) {
        List<CommentDto> allCommentForUser = commentService.getAllCommentForUser(username);
        return ResponseEntity.status(HttpStatus.OK).body(allCommentForUser);

    }

}
