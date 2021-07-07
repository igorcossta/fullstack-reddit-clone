package com.reddit.spring.controller;

import com.reddit.spring.dto.Error;
import com.reddit.spring.dto.SubredditRequest;
import com.reddit.spring.dto.SubredditResponse;
import com.reddit.spring.service.SubredditService;
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
@RequestMapping("/api/subreddit")
@AllArgsConstructor
public class SubredditController {
    private final static Logger LOGGER = LoggerFactory.getLogger(SubredditController.class);
    private final SubredditService subredditService;

    @ApiOperation(value = "${SubredditController.createSubreddit.value}", notes = "${SubredditController.createSubreddit.notes}", nickname = "${SubredditController.createSubreddit.nickname}")
    @ApiResponses({
            @ApiResponse(code = 201, message = "resource created successfully"),
            @ApiResponse(code = 400, message = "client bad request", response = Error.class),
            @ApiResponse(code = 401, message = "client bad credentials", response = Error.class),
            @ApiResponse(code = 403, message = "client does not have permission", response = Error.class)})
    @PostMapping
    public ResponseEntity<Void> createSubreddit(@RequestBody @Valid SubredditRequest subreddit) {
        subredditService.save(subreddit);
        LOGGER.debug("método createSubreddit executado: " + subreddit.toString());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "${SubredditController.findAll.value}", notes = "${SubredditController.findAll.notes}", nickname = "${SubredditController.findAll.nickname}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "request accepted successfully", response = SubredditResponse[].class),
            @ApiResponse(code = 401, message = "client bad credentials", response = Error.class),
            @ApiResponse(code = 403, message = "client does not have permission", response = Error.class)})
    @GetMapping
    public ResponseEntity<List<SubredditResponse>> findAll() {
        List<SubredditResponse> subreddit = subredditService.findAll();
        LOGGER.debug("método findAll executado");
        return new ResponseEntity<>(subreddit, HttpStatus.OK);
    }

    @ApiOperation(value = "${SubredditController.findById.value}", notes = "${SubredditController.findById.notes}", nickname = "${SubredditController.findById.nickname}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "request accepted successfully", response = SubredditResponse.class),
            @ApiResponse(code = 401, message = "client bad credentials", response = Error.class),
            @ApiResponse(code = 403, message = "client does not have permission", response = Error.class),
            @ApiResponse(code = 404, message = "resource not found", response = Error.class)})
    @GetMapping("/{id}")
    public ResponseEntity<SubredditResponse> findById(@PathVariable Long id) {
        SubredditResponse subreddit = subredditService.findById(id);
        LOGGER.debug("método findById executado: " + id);
        return new ResponseEntity<>(subreddit, HttpStatus.OK);
    }

}
