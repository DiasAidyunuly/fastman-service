package kz.magnum.magnumback.fastmanservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    private int status;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private ZonedDateTime timestamp;
}
