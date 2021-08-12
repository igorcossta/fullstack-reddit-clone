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
@Builder
@ToString
public class SubredditRequest {
    @NotNull(message = "field name cannot be null")
    @NotBlank(message = "field name cannot be empty")
    private String name;

    @NotNull(message = "field description cannot be null")
    @NotBlank(message = "field description cannot be empty")
    @Size(min = 10, max = 255, message = "field description must be between 10 and 255 characters")
    private String description;
}
