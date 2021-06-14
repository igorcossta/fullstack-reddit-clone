package com.reddit.spring.controller;

import com.reddit.spring.dto.SubredditDto;
import com.reddit.spring.service.SubredditService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.String.format;

@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
public class SubredditController {
    private final static Logger LOGGER = LoggerFactory.getLogger(SubredditController.class);
    private final SubredditService subredditService;

    @PostMapping
    public ResponseEntity<Void> createSubreddit(@RequestBody SubredditDto subreddit) {
        subredditService.save(subreddit);
        LOGGER.debug("creating new subreddit: " + subreddit.toString());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SubredditDto>> findAll() {
        List<SubredditDto> subreddit = subredditService.findAll();
        LOGGER.debug("listing all subreddit");
        return new ResponseEntity<>(subreddit, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubredditDto> findById(@PathVariable Long id) {
        SubredditDto subreddit = subredditService.findById(id);
        LOGGER.debug(format("listing specific subreddit: %s ", id) + subreddit.toString());
        return new ResponseEntity<>(subreddit, HttpStatus.OK);
    }

}
