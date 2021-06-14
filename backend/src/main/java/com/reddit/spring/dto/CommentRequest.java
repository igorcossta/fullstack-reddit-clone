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
public class CommentRequest {
    @NotNull(message = "field post cannot be null")
    private Long postId;

    @NotNull(message = "field text cannot be null")
    @NotBlank(message = "field text cannot be empty")
    @Size(min = 10, max = 2000, message = "field text must be between 10 and 2000 characters")
    private String text;

    @NotNull(message = "field username cannot be null")
    @NotBlank(message = "field username cannot be empty")
    private String username;
}
