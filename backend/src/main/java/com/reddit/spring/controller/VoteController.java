package com.reddit.spring.controller;

import com.reddit.spring.dto.VoteDto;
import com.reddit.spring.service.VoteService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vote")
@AllArgsConstructor
public class VoteController {
    private final static Logger LOGGER = LoggerFactory.getLogger(VoteController.class);
    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<Void> savePost(@RequestBody VoteDto vote) {
        voteService.vote(vote);
        LOGGER.debug("saving new vote: " + vote.toString());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
