package com.reddit.spring.model;

import com.reddit.spring.dto.CommentDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Comment {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotEmpty
    private String text;
    @ManyToOne
    @JoinColumn(name = "id_do_post", referencedColumnName = "postId")
    private Post post;
    private Instant createdDate;
    @ManyToOne
    @JoinColumn(name = "id_do_user", referencedColumnName = "userId")
    private User user;
}
