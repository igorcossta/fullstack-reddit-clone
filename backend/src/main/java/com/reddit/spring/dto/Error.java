package com.reddit.spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static com.reddit.spring.dto.Error.Field.getBindingResult;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Error {
    @JsonSerialize(using = OffsetDateTimeSerializer.class)
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime timestamp;
    private int status;
    private String message;
    private List<Field> fields;

    public Error(String message, Integer status) {
        this.timestamp = OffsetDateTime.now();
        this.status = status;
        this.message = message;
        this.fields = null;
    }

    public Error(String message, Integer status, List<Field> fields) {
        this.timestamp = OffsetDateTime.now();
        this.status = status;
        this.message = message;
        this.fields = fields;
    }

    public static void createResponse(HttpServletResponse res, int status, String message) throws IOException {
        res.setStatus(status);
        res.setContentType(APPLICATION_JSON_VALUE);

        var body = new Error(message, status);

        new ObjectMapper().writeValue(res.getOutputStream(), body);
    }

    public static Error createResponse(Exception ex, HttpStatus status) {
        if (ex instanceof MethodArgumentNotValidException)
            return new Error("some fields are incorrect", status.value(), getBindingResult((MethodArgumentNotValidException) ex));
        return new Error(ex.getMessage(), status.value());
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Field {
        private String field;
        private String message;

        static List<Field> getBindingResult(MethodArgumentNotValidException ex) {
            return ex.getBindingResult().getFieldErrors().stream().map(e -> new Field(e.getField(), e.getDefaultMessage())).collect(toList());
        }
    }
}
