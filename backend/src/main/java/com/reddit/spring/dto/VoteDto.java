package com.reddit.spring.dto;

import com.reddit.spring.model.VoteType;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class VoteDto {
    @NotNull(message = "field voteType cannot be null")
    private VoteType voteType;

    @NotNull(message = "field post cannot be null")
    private Long postId;
}
