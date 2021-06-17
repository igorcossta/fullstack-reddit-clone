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
@EqualsAndHashCode
@ToString
@Builder
@Entity
public class Subreddit {
    @SequenceGenerator(name = "subreddit_sequence", sequenceName = "subreddit_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "subreddit_sequence")
    private Long subredditId;

    private String name;

    private String description;

    @OneToMany
    private List<Post> posts;

    private Instant createdDate;

    @ManyToOne
    @JoinColumn(name = "id_do_user", referencedColumnName = "userId")
    private AppUser user;
}
