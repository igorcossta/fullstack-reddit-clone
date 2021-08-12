package com.reddit.spring.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UsernameAndPasswordRequest {
    @Email
    @NotNull(message = "field username cannot be null")
    @NotBlank(message = "field username cannot be empty")
    private String username;

    @NotNull(message = "field password cannot be null")
    @NotBlank(message = "field password cannot be empty")
    @Size(min = 6, max = 20, message = "field password must be between 6 and 20 characters")
    private String password;
}
