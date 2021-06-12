package com.reddit.spring.model;

import com.reddit.spring.appuser.AppUser;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Post {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long postId;
    @NotBlank(message = "Post Name cannot be empty or Null")
    private String postName;
    private String url;
    @Lob
    private String description;
    private Integer voteCount = 0;
    @ManyToOne
    @JoinColumn(name = "id_do_user", referencedColumnName = "userId")
    private AppUser user;
    private Instant createdDate;
    @ManyToOne
    @JoinColumn(name = "id_do_subreddit", referencedColumnName = "subredditId")
    private Subreddit subreddit;
}
