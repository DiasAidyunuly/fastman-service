package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncExecutionLocalDto {
    private Long incidentId;
    private List<IncExecutionWithoutIncidentIdDto> incExecutions;
}
