package com.reddit.spring.controller;

import com.reddit.spring.dto.SubredditRequest;
import com.reddit.spring.dto.SubredditResponse;
import com.reddit.spring.service.SubredditService;
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

    @PostMapping
    public ResponseEntity<Void> createSubreddit(@RequestBody @Valid SubredditRequest subreddit) {
        subredditService.save(subreddit);
        LOGGER.debug("método createSubreddit executado: " + subreddit.toString());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SubredditResponse>> findAll() {
        List<SubredditResponse> subreddit = subredditService.findAll();
        LOGGER.debug("método findAll executado");
        return new ResponseEntity<>(subreddit, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubredditResponse> findById(@PathVariable Long id) {
        SubredditResponse subreddit = subredditService.findById(id);
        LOGGER.debug("método findById executado: " + id);
        return new ResponseEntity<>(subreddit, HttpStatus.OK);
    }

}
