package com.reddit.spring.service;

import com.reddit.spring.dto.SubredditRequest;
import com.reddit.spring.dto.SubredditResponse;
import com.reddit.spring.exception.SpringRedditException;
import com.reddit.spring.mapper.SubredditMapper;
import com.reddit.spring.model.Subreddit;
import com.reddit.spring.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubredditService {
    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;

    @Transactional
    public void save(SubredditRequest subredditDto) {
        subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
    }

    @Transactional(readOnly = true)
    public List<SubredditResponse> findAll() {
        return subredditRepository.findAll()
                .stream().map(subredditMapper::mapSubredditToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public SubredditResponse findById(Long id) {
        Optional<Subreddit> subreddit = subredditRepository.findById(id);
        subreddit.orElseThrow(() -> new SpringRedditException("Id not found: " + id));
        return subredditMapper.mapSubredditToDto(subreddit.get());
    }
}
