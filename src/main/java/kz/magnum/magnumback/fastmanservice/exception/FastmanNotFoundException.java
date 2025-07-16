package kz.magnum.magnumback.fastmanservice.exception;

import lombok.Getter;

@Getter
public class FastmanNotFoundException extends RuntimeException {
    public FastmanNotFoundException(String message) {
        super(message);
    }
}
