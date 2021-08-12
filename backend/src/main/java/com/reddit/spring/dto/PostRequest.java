package com.reddit.spring.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PostRequest {
    @NotNull(message = "field subreddit cannot be null")
    @NotBlank(message = "field subreddit cannot be empty")
    private String subredditName;

    @NotNull(message = "field post cannot be null")
    @NotBlank(message = "field post cannot be empty")
    private String postName;

    @NotNull(message = "field description cannot be null")
    @NotBlank(message = "field description cannot be empty")
    @Size(min = 10, max = 255, message = "field description must be between 10 and 255 characters")
    private String description;

    @NotNull(message = "field url cannot be null")
    @NotBlank(message = "field url cannot be empty")
    private String url;
}
