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
@Entity
public class Comment {
    @SequenceGenerator(name = "comment_sequence", sequenceName = "comment_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "comment_sequence")
    private Long commentId;

    private String text;

    @ManyToOne
    @JoinColumn(name = "id_do_post", referencedColumnName = "postId")
    private Post post;

    private Instant createdDate;

    @ManyToOne
    @JoinColumn(name = "id_do_user", referencedColumnName = "userId")
    private User user;
}




