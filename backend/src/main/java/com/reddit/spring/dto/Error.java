package com.reddit.spring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private List<Field> field;

    public Error(LocalDateTime timestamp, int status, String error, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.field = null;
    }

    @Getter
    @Setter
    private static class Field {
        private String field;
        private String message;
    }
}
