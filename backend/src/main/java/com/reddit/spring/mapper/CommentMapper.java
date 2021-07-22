package com.reddit.spring.mapper;

import com.reddit.spring.dto.CommentRequest;
import com.reddit.spring.dto.CommentResponse;
import com.reddit.spring.model.User;
import com.reddit.spring.model.Comment;
import com.reddit.spring.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "commentId", ignore = true)
    @Mapping(target = "text", source = "commentDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    Comment map(CommentRequest commentDto, Post post, User user);

    @Mapping(target = "id", source = "commentId")
    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "username", expression = "java(comment.getUser().getUsername())")
    CommentResponse mapToDto(Comment comment);
}
