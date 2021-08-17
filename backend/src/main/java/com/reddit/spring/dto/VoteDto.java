package com.reddit.spring.dto;

import com.reddit.spring.model.VoteType;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class VoteDto {
    @NotNull(message = "post id must not be null")
    @Size(min = 6, max = 8, message = "vote type must be between {min} and {max} characters")
    @Pattern(regexp = "^[A-Z]{6,8}$", message = "vote type must contain only letters (uppercase) without space")
    private VoteType voteType;

    @NotEmpty(message = "post id must not be null or empty")
    @Min(value = 1, message = "post id must be greater than 0")
    @Max(value = Long.MAX_VALUE, message = "post id must not greater than {value}")
    private Long postId;
}
