package org.example.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private HttpStatus httpStatus;
    private String message;
    private Date timestamp;

    public ErrorResponse(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.timestamp = new Date();
    }
}
