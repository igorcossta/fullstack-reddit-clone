package com.reddit.spring.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PostRequest {
    private Long postID;
    private String subredditName;
    private String postName;
    private String url;
    private String description;
}
