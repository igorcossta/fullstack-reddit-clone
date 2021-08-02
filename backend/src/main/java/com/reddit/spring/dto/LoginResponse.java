package com.reddit.spring.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LoginResponse {
    private String firstName;
    private String lastName;
    private String username; // email
}
