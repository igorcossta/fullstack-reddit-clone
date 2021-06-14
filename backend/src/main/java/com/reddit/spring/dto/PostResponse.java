package com.reddit.spring.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PostResponse {
    private Long id;
    private String subredditName;
    private String postName;
    private String description;
    private String username;
    private Integer voteCount;
    private Integer commentCount;
    private boolean upVote;
    private boolean downVote;
    private String duration;
    private String url;
}
