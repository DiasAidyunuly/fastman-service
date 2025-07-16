package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncActionLocalDto {
    private Long incidentId;
    private List<IncActionWithoutIncidentIdDto> incActions;
}
