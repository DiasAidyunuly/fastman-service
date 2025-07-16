package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncExecutionWithoutIncidentIdDto {
    private ExecutionDto execution;
    private Byte mandatory;
    private Byte allowCheck;
}
