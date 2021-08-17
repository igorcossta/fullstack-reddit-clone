package com.reddit.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "comment")
public class Comment {
    @SequenceGenerator(name = "comment_sequence", sequenceName = "comment_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "comment_sequence")
    private Long commentId;

    @Column(name = "comment", nullable = false)
    @Lob
    private String text;

    @ManyToOne
    @JoinColumn(name = "postId", referencedColumnName = "postId", nullable = false)
    private Post post;

    @Column(nullable = false)
    private Instant createdDate;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    private User user;
}




