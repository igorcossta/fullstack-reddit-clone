package com.reddit.spring.controller;

import com.reddit.spring.dto.SubredditDto;
import com.reddit.spring.exception.SpringRedditException;
import com.reddit.spring.service.SubredditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
@Slf4j
public class SubredditController {
    private final SubredditService subredditService;

    @PostMapping
    public ResponseEntity<SubredditDto> createSubreddit(@RequestBody SubredditDto subredditDto) {
        SubredditDto obj = subredditService.save(subredditDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(obj);
    }

    @GetMapping
    public ResponseEntity<List<SubredditDto>> getAllSubreddits() {
        List<SubredditDto> all = subredditService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubredditDto> getById(@PathVariable Long id) {
        try {
            SubredditDto obj = subredditService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(obj);
        } catch (SpringRedditException ex) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

}
