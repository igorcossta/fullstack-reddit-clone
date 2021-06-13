package com.reddit.spring.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
public class SubredditDto {
    private Long subredditId;
    private String name;
    private String description;
    private Integer numberOfPosts;
}
