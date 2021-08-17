package com.reddit.spring.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "subreddit")
public class Subreddit {
    @SequenceGenerator(name = "subreddit_sequence", sequenceName = "subreddit_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "subreddit_sequence")
    private Long subredditId;

    @Column(nullable = false, length = 30, unique = true)
    private String name;

    @Column(nullable = false, length = 120)
    private String description;

    @OneToMany
    private List<Post> posts;

    @Column(nullable = false)
    private Instant createdDate;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    private User user;
}
