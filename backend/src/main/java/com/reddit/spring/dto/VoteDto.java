package com.reddit.spring.dto;

import com.reddit.spring.model.VoteType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class VoteDto {
    private VoteType voteType;
    private Long postId;
}
