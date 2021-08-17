package com.reddit.spring.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UsernameAndPasswordRequest {
    @Email
    @NotEmpty(message = "username must not be null or empty")
    private String username;

    @NotEmpty(message = "password must not be null or empty")
    @Size(min = 6, max = 20, message = "password must be between {min} and {max} characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%&]).{6,20}$", message = "password must contain letters (upper and lower case), numbers and special chars without space")
    private String password;
}
