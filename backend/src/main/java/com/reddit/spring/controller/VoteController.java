package com.reddit.spring.controller;

import com.reddit.spring.dto.Error;
import com.reddit.spring.dto.VoteDto;
import com.reddit.spring.service.VoteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/vote")
@AllArgsConstructor
public class VoteController {
    private final static Logger LOGGER = LoggerFactory.getLogger(VoteController.class);
    private final VoteService voteService;

    @ApiOperation(value = "${VoteController.vote.value}", notes = "${VoteController.vote.notes}", nickname = "${VoteController.vote.nickname}")
    @ApiResponses({
            @ApiResponse(code = 201, message = "resource created successfully"),
            @ApiResponse(code = 400, message = "client bad request", response = Error.class),
            @ApiResponse(code = 401, message = "client bad credentials", response = Error.class),
            @ApiResponse(code = 403, message = "client does not have permission", response = Error.class),
            @ApiResponse(code = 404, message = "resource not found", response = Error.class)})
    @PostMapping
    public ResponseEntity<Void> vote(@RequestBody @Valid VoteDto vote) {
        voteService.vote(vote);
        LOGGER.debug("método vote executado " + vote.toString());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
