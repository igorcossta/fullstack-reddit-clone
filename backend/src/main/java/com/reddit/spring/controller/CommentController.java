package com.reddit.spring.controller;

import com.reddit.spring.dto.CommentRequest;
import com.reddit.spring.dto.CommentResponse;
import com.reddit.spring.dto.Error;
import com.reddit.spring.service.CommentService;
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
@RequestMapping("/api/comment")
@AllArgsConstructor
public class CommentController {
    private final static Logger LOGGER = LoggerFactory.getLogger(CommentController.class);
    private final CommentService commentService;

    @ApiOperation(value = "${CommentController.create.value}", notes = "${CommentController.create.notes}", nickname = "${CommentController.create.nickname}")
    @ApiResponses({
            @ApiResponse(code = 201, message = "resource created successfully"),
            @ApiResponse(code = 400, message = "client bad request", response = Error.class),
            @ApiResponse(code = 401, message = "client bad credentials", response = Error.class),
            @ApiResponse(code = 403, message = "client does not have permission", response = Error.class),
            @ApiResponse(code = 404, message = "resource not found", response = Error.class)})
    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody @Valid CommentRequest comment) {
        commentService.save(comment);
        LOGGER.debug("método createComment executado: " + comment.toString());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "${CommentController.findAllCommentByPostId.value}", notes = "${CommentController.findAllCommentByPostId.notes}", nickname = "${CommentController.findAllCommentByPostId.nickname}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "request accepted successfully", response = CommentResponse[].class),
            @ApiResponse(code = 400, message = "client bad request", response = Error.class),
            @ApiResponse(code = 401, message = "client bad credentials", response = Error.class),
            @ApiResponse(code = 403, message = "client does not have permission", response = Error.class),
            @ApiResponse(code = 404, message = "resource not found", response = Error.class)})
    @GetMapping("/by-post/{id}")
    public ResponseEntity<List<CommentResponse>> findAllCommentByPostId(@PathVariable Long id) {
        List<CommentResponse> comment = commentService.findAllCommentByPostId(id);
        LOGGER.debug("método findAllCommentByPostId executado: " + id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @ApiOperation(value = "${CommentController.findAllCommentByUsername.value}", notes = "${CommentController.findAllCommentByUsername.notes}", nickname = "${CommentController.findAllCommentByUsername.nickname}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "request accepted successfully", response = CommentResponse[].class),
            @ApiResponse(code = 400, message = "client bad request", response = Error.class),
            @ApiResponse(code = 401, message = "client bad credentials", response = Error.class),
            @ApiResponse(code = 403, message = "client does not have permission", response = Error.class),
            @ApiResponse(code = 404, message = "resource not found", response = Error.class)})
    @GetMapping("/by-user/{username}")
    public ResponseEntity<List<CommentResponse>> findAllCommentByUsername(@PathVariable String username) {
        List<CommentResponse> comment = commentService.findAllCommentByUsername(username);
        LOGGER.debug("método findAllCommentByUsername executado: " + username);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

}
