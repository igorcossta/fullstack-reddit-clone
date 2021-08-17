package com.reddit.spring.dto;

import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PostRequest {
    @NotEmpty(message = "subreddit name must not be null or empty")
    @Size(min = 5, max = 30, message = "subreddit name must be between {min} and {max} characters")
    @Pattern(regexp = "^[a-zA-Z]{5,30}$", message = "subreddit name must contain only letters without space")
    private String subredditName;

    @NotEmpty(message = "post name must not be null or empty")
    @Size(min = 5, max = 30, message = "post name must be between {min} and {max} characters")
    @Pattern(regexp = "^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$", message = "post name must contain only letters (upper and lower case), numbers and space")
    private String postName;

    @NotEmpty(message = "description must not be null or empty")
    @Size(min = 5, max = 120, message = "description must be between {min} and {max} characters")
    @Pattern(regexp = "^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$", message = "description must contain only letters (upper and lower case), numbers and space")
    private String description;

    @NotEmpty(message = "url must not be null or empty")
    @URL(message = "URL must be a URL")
    private String url;
}
