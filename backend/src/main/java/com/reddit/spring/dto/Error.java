package com.reddit.spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

import static java.time.ZonedDateTime.now;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Error {
    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private ZonedDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private List<Field> fields;

    public Error(int status, String error, String message, String path, List<Field> fields) {
        this.timestamp = now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.fields = fields;
    }

    public Error(int status, String error, String message, String path) {
        this.timestamp = now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.fields = null;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Field {
        private String field;
        private String message;
    }
}
