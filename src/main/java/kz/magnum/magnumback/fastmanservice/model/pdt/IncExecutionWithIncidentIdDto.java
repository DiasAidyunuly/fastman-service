package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncExecutionWithIncidentIdDto {
    private Long id;
    private Long incidentId;
    private ExecutionDto execution;
    private Byte mandatory;
    private Byte allowCheck;
}
