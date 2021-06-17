package com.reddit.spring.model;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity(name = "user")
public class AppUser {
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "user_sequence")
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Boolean locked = false;
    private Boolean enabled = false;

    public AppUser(String firstName,
                   String lastName,
                   String email,
                   String password,
                   Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
