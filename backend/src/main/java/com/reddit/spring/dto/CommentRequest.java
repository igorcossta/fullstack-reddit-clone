package com.reddit.spring.dto;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CommentRequest {
    @NotNull(message = "post id must not be null")
    @Min(value = 1, message = "post id must be greater than 0")
    @Max(value = Long.MAX_VALUE, message = "post id must not greater than {value}")
    private Long postId;

    @NotEmpty(message = "comment must not be null or empty")
    @Size(min = 3, max = 2000, message = "comment must be between {min} and {max} characters")
    @Pattern(regexp = "^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$", message = "comment must contain only letters (upper and lower case), numbers and space")
    private String text;

    @Email(message = "email must be a valid email address")
    @NotEmpty(message = "email must not be null or empty")
    private String username;
}
