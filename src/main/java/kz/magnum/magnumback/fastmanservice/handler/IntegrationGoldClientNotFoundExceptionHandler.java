package kz.magnum.magnumback.fastmanservice.handler;

import kz.magnum.magnumback.fastmanservice.exception.IntegrationGoldClientNotFoundException;
import kz.magnum.magnumback.fastmanservice.model.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@Slf4j
@RestControllerAdvice
public class IntegrationGoldClientNotFoundExceptionHandler {
    @ExceptionHandler(IntegrationGoldClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleException(IntegrationGoldClientNotFoundException e) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ApiError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .timestamp(ZonedDateTime.now())
                .build());
    }
}
