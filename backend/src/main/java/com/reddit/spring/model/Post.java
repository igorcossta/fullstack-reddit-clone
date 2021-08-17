package com.reddit.spring.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "post")
public class Post {
    @SequenceGenerator(name = "post_sequence", sequenceName = "post_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "post_sequence")
    private Long postId;

    @Column(nullable = false, length = 30, unique = true)
    private String postName;

    @Column(nullable = false, unique = true)
    private String url;

    @Column(nullable = false, length = 120)
    private String description;

    @Column(nullable = false)
    private Integer voteCount = 0;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    private User user;

    @Column(nullable = false)
    private Instant createdDate;

    @ManyToOne
    @JoinColumn(name = "subredditId", referencedColumnName = "subredditId")
    private Subreddit subreddit;
}
