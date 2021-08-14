package com.reddit.spring.controller;

import com.reddit.spring.dto.SubredditRequest;
import com.reddit.spring.dto.SubredditResponse;
import com.reddit.spring.service.SubredditService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
public class SubredditController {
    private final static Logger LOGGER = LoggerFactory.getLogger(SubredditController.class);
    private final SubredditService subredditService;

    @ApiOperation(value = "create subreddit forum",
            notes = "this endpoint create a new subreddit forum",
            nickname = "createNewSubreddit")
    @PostMapping
    public ResponseEntity<Void> createSubreddit(@RequestBody @Valid SubredditRequest subreddit) {
        subredditService.save(subreddit);
        LOGGER.debug("método createSubreddit executado: " + subreddit.toString());
        return new ResponseEntity<>(CREATED);
    }

    @ApiOperation(value = "find all the subreddit forum", notes = "this endpoint find all subreddit forum", nickname = "findAllSubreddit")
    @GetMapping
    public ResponseEntity<List<SubredditResponse>> findAll() {
        List<SubredditResponse> subreddit = subredditService.findAll();
        LOGGER.debug("método findAll executado");
        return new ResponseEntity<>(subreddit, OK);
    }

    @ApiOperation(value = "find subreddit by id",
            notes = "this endpoint find one subreddit forum by id",
            nickname = "findByIdSubreddit")
    @GetMapping("/{id}")
    public ResponseEntity<SubredditResponse> findById(@PathVariable Long id) {
        SubredditResponse subreddit = subredditService.findById(id);
        LOGGER.debug("método findById executado: " + id);
        return new ResponseEntity<>(subreddit, OK);
    }

    @ApiOperation(value = "find subreddit by user id",
            notes = "this endpoint find all subreddit by user id",
            nickname = "findSubredditByUserId")
    @GetMapping("/user/{username}")
    public ResponseEntity<List<SubredditResponse>> findByUser(@PathVariable String username) {
        List<SubredditResponse> subreddit = subredditService.findByUser(username);
        LOGGER.debug("método findByUser executado");
        return new ResponseEntity<>(subreddit, OK);
    }

}
