package com.reddit.spring.model;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Entity
public class Post {
    @SequenceGenerator(name = "post_sequence", sequenceName = "post_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "post_sequence")
    private Long postId;

    private String postName;

    private String url;

    private String description;

    private Integer voteCount = 0;

    @ManyToOne
    @JoinColumn(name = "id_do_user", referencedColumnName = "userId")
    private User user;

    private Instant createdDate;

    @ManyToOne
    @JoinColumn(name = "id_do_subreddit", referencedColumnName = "subredditId")
    private Subreddit subreddit;
}
