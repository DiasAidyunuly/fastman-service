package kz.magnum.magnumback.fastmanservice.handler;

import kz.magnum.magnumback.fastmanservice.exception.FastmanInternalServerErrorException;
import kz.magnum.magnumback.fastmanservice.model.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZonedDateTime;

@Slf4j
@ControllerAdvice
public class FastmanInternalServerErrorExceptionHandler {
    @ExceptionHandler(FastmanInternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiError> handleException(FastmanInternalServerErrorException e) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ApiError.builder()
                .message(e.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(ZonedDateTime.now())
                .build());
    }
}
