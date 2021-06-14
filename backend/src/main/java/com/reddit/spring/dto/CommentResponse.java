package com.reddit.spring.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CommentResponse {
    private Long id;
    private Long postId;
    private Instant createdDate;
    private String text;
    private String username;
}

