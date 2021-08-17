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
public class RegisterRequest {
    @NotEmpty(message = "first name must not be null or empty")
    @Size(min = 3, max = 16, message = "first name must be between {min} and {max} characters")
    @Pattern(regexp = "^[a-zA-Z]{3,16}$", message = "first name must contain only letters without space")
    private String firstName;

    @NotEmpty(message = "last name must not be null or empty")
    @Size(min = 3, max = 16, message = "last name must be between {min} and {max} characters")
    @Pattern(regexp = "^[a-zA-Z]{3,16}$", message = "last name must contain only letters without space")
    private String lastName;

    @Email(message = "email must be a valid email address")
    @NotEmpty(message = "email must not be null or empty")
    private String email;

    @NotEmpty(message = "password must not be null or empty")
    @Size(min = 6, max = 20, message = "password must be between {min} and {max} characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%&]).{6,20}$", message = "password must contain letters (upper and lower case), numbers and special chars without space")
    private String password;
}
