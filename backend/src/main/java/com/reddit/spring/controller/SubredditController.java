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

    @ApiOperation(value = "create subreddit", notes = "this endpoint create a new subreddit", nickname = "createNewSubreddit")
    @PostMapping
    public ResponseEntity<Void> createSubreddit(@RequestBody @Valid SubredditRequest subreddit) {
        subredditService.save(subreddit);
        LOGGER.debug("método createSubreddit executado: " + subreddit);
        return new ResponseEntity<>(CREATED);
    }

    @ApiOperation(value = "find all subreddit", notes = "this endpoint find all subreddit", nickname = "findAllSubreddit")
    @GetMapping
    public ResponseEntity<List<SubredditResponse>> findAll() {
        List<SubredditResponse> subreddit = subredditService.findAll();
        LOGGER.debug("método findAll executado");
        return new ResponseEntity<>(subreddit, OK);
    }

    @ApiOperation(value = "find subreddit by id", notes = "this endpoint find one subreddit by id", nickname = "findSubredditById")
    @GetMapping("/by-id/{id}")
    public ResponseEntity<SubredditResponse> findById(@PathVariable Long id) {
        SubredditResponse subreddit = subredditService.findById(id);
        LOGGER.debug("método findById executado: " + id);
        return new ResponseEntity<>(subreddit, OK);
    }

    @ApiOperation(value = "find subreddit by name", notes = "this endpoint find one subreddit by name", nickname = "findSubredditByName")
    @GetMapping("/by-name/{name}")
    public ResponseEntity<SubredditResponse> findByName(@PathVariable String name) {
        SubredditResponse subreddit = subredditService.findByName(name);
        LOGGER.debug("método findByName executado: " + name);
        return new ResponseEntity<>(subreddit, OK);
    }

    @ApiOperation(value = "find all subreddit by username", notes = "this endpoint find all subreddit by username", nickname = "findAllSubredditByUsername")
    @GetMapping("/by-user/{username}")
    public ResponseEntity<List<SubredditResponse>> findByUser(@PathVariable String username) {
        List<SubredditResponse> subreddit = subredditService.findByUser(username);
        LOGGER.debug("método findByUser executado");
        return new ResponseEntity<>(subreddit, OK);
    }

}
