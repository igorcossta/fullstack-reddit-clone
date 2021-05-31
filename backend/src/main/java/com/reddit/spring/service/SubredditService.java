package com.reddit.spring.service;

import com.reddit.spring.dto.SubredditDto;
import com.reddit.spring.exception.SpringRedditException;
import com.reddit.spring.mapper.SubredditMapper;
import com.reddit.spring.model.Subreddit;
import com.reddit.spring.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {
    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit save = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
        subredditDto.setSubredditId(save.getSubredditId());
        return subredditDto;
    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream().map(subredditMapper::mapSubredditToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public SubredditDto getById(Long id) {
        Optional<Subreddit> subreddit = subredditRepository.findById(id);
        subreddit.orElseThrow(() -> new SpringRedditException("Id not found: " + id));
        return subredditMapper.mapSubredditToDto(subreddit.get());
    }
}
