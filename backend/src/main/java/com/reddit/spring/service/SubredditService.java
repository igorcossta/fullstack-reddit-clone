package com.reddit.spring.service;

import com.reddit.spring.dto.SubredditRequest;
import com.reddit.spring.dto.SubredditResponse;
import com.reddit.spring.exception.SubredditNotFoundException;
import com.reddit.spring.mapper.SubredditMapper;
import com.reddit.spring.model.Subreddit;
import com.reddit.spring.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubredditService {
    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;
    private final UserService userService;

    @Transactional
    public void save(SubredditRequest subredditDto) {
        subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto, userService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public List<SubredditResponse> findAll() {
        return subredditRepository.findAll()
                .stream().map(subredditMapper::mapSubredditToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public SubredditResponse findById(Long id) {
        Subreddit subreddit = subredditRepository.findById(id).orElseThrow(() -> new SubredditNotFoundException("subreddit cannot be found"));
        return subredditMapper.mapSubredditToDto(subreddit);
    }
}
