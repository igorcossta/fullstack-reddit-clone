package com.reddit.spring.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegisterRequest {
    @NotNull(message = "field firstName cannot be null")
    @NotBlank(message = "field firstName cannot be empty")
    @Size(min = 3, max = 30, message = "first name must be between 3 and 30 characters")
    private String firstName;

    @NotNull(message = "field lastName cannot be null")
    @NotBlank(message = "field lastName cannot be empty")
    @Size(min = 3, max = 30, message = "first name must be between 3 and 3 characters")
    private String lastName;

    @NotNull(message = "field email cannot be null")
    @NotBlank(message = "field email cannot be empty")
    private String email;

    @NotNull(message = "field password cannot be null")
    @NotBlank(message = "field password cannot be empty")
    private String password;
}
