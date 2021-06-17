package com.reddit.spring.model;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Entity
public class Vote {
    @SequenceGenerator(name = "vote_sequence", sequenceName = "vote_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "vote_sequence")
    private Long voteId;

    private VoteType voteType;

    @ManyToOne
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private AppUser user;
}
