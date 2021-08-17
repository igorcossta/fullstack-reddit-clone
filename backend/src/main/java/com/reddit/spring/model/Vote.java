package com.reddit.spring.model;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "vote")
public class Vote {
    @SequenceGenerator(name = "vote_sequence", sequenceName = "vote_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "vote_sequence")
    private Long voteId;

    @Column(nullable = false)
    private VoteType voteType;

    @ManyToOne
    @JoinColumn(name = "postId", referencedColumnName = "postId", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    private User user;
}
