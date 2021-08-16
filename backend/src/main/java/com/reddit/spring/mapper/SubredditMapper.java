package com.reddit.spring.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.reddit.spring.dto.SubredditRequest;
import com.reddit.spring.dto.SubredditResponse;
import com.reddit.spring.model.Subreddit;
import com.reddit.spring.repository.PostRepository;
import com.reddit.spring.service.UserService;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class SubredditMapper {
    @Autowired
    protected UserService userService;
    @Autowired
    protected PostRepository postRepository;

    @Mapping(target = "numberOfPosts", expression = "java(postRepository.countAllBySubreddit(subreddit))")
    @Mapping(target = "createdDate", expression = "java(getDuration(subreddit))")
    public abstract SubredditResponse mapSubredditToDto(Subreddit subreddit);

    String getDuration(Subreddit subreddit) {
        return TimeAgo.using(subreddit.getCreatedDate().toEpochMilli());
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "user", expression = "java(userService.getCurrentUser())")
    public abstract Subreddit mapDtoToSubreddit(SubredditRequest dto);

}
