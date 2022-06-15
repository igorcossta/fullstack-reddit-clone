package com.reddit.spring.service;

import com.reddit.spring.dto.SubredditRequest;
import com.reddit.spring.dto.SubredditResponse;
import com.reddit.spring.exception.SubredditExistsException;
import com.reddit.spring.exception.SubredditNotFoundException;
import com.reddit.spring.mapper.SubredditMapper;
import com.reddit.spring.model.Subreddit;
import com.reddit.spring.model.User;
import com.reddit.spring.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.data.domain.PageRequest.of;

@Service
@AllArgsConstructor
public class SubredditService {
    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;
    private final UserService userService;

    @Transactional
    public void save(SubredditRequest dto) {
        boolean present = subredditRepository.existsSubredditByName(dto.getName());
        if (present) throw new SubredditExistsException("the subreddit already exists");

        subredditRepository.save(subredditMapper.mapDtoToSubreddit(dto));
    }

    @Transactional(readOnly = true)
    public List<SubredditResponse> findAll(Integer page) {
        return subredditRepository.findAll(of(page, 10))
                .stream().map(subredditMapper::mapSubredditToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public SubredditResponse findById(Long id) {
        Subreddit subreddit = subredditRepository.findById(id).orElseThrow(() -> new SubredditNotFoundException("subreddit cannot be found"));
        return subredditMapper.mapSubredditToDto(subreddit);
    }

    @Transactional(readOnly = true)
    public SubredditResponse findByName(String name) {
        Subreddit subreddit = subredditRepository.findByName(name).orElseThrow(() -> new SubredditNotFoundException("subreddit cannot be found"));
        return subredditMapper.mapSubredditToDto(subreddit);
    }

    @Transactional(readOnly = true)
    public List<SubredditResponse> findByUser(String username, Integer page) {
        User user = userService.findByUsername(username);
        return subredditRepository.findAllByUser(of(page, 10), user).stream().map(subredditMapper::mapSubredditToDto).collect(toList());
    }
}
