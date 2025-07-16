package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncExecutionDto {
    private Long id;
    private IncidentDto incident;
    private ExecutionDto execution;
    private Byte mandatory;
    private Byte allowCheck;
}
