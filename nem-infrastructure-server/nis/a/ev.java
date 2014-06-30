package org.nem.nis.a;

import java.util.MissingResourceException;
import org.nem.core.connect.ErrorResponse;
import org.nem.nis.controller.interceptors.UnauthorizedAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@ControllerAdvice
public class ev {
    @ExceptionHandler(value={MissingResourceException.class})
    public ResponseEntity<ErrorResponse> a(Exception exception) {
        return ev.a(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value={IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> b(Exception exception) {
        return ev.a(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value={UnauthorizedAccessException.class})
    public ResponseEntity<ErrorResponse> g(Exception exception) {
        return ev.a(exception, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<ErrorResponse> c(Exception exception) {
        return ev.a(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static ResponseEntity<ErrorResponse> a(Exception exception, HttpStatus httpStatus) {
        return new ResponseEntity((Object)new ErrorResponse(exception, httpStatus), httpStatus);
    }
}
