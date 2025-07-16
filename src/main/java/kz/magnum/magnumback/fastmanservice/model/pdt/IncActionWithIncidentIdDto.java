package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncActionWithIncidentIdDto {
    private Long id;
    private Long incidentId;
    private ActionDto action;
}
