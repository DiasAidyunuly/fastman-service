package kz.magnum.magnumback.fastmanservice.exception;

import lombok.Getter;

@Getter
public class FastmanInternalServerErrorException extends RuntimeException {
    public FastmanInternalServerErrorException(String message) {
        super(message);
    }
}
