package com.reddit.spring.model;

import com.reddit.spring.appuser.AppUser;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Subreddit {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long subredditId;
    @NotBlank(message = "Community name is required")
    private String name;
    @NotBlank(message = "Description is required")
    private String description;
    @OneToMany
    private List<Post> posts;
    private Instant createdDate;
    @ManyToOne
    @JoinColumn(name = "id_do_user", referencedColumnName = "userId")
    private AppUser user;
}
