package com.reddit.spring.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
public class SubredditRequest {
    @NotEmpty(message = "name must not be null or empty")
    @Size(min = 5, max = 30, message = "name must be between {min} and {max} characters")
    @Pattern(regexp = "^[a-zA-Z]{5,30}$", message = "name must contain only letters without space")
    private String name;

    @NotEmpty(message = "description must not be null or empty")
    @Size(min = 10, max = 120, message = "description must be between {min} and {max} characters")
    @Pattern(regexp = "^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$", message = "description must contain only letters (upper and lower case), numbers and space")
    private String description;
}
