package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncActionDto {
    private Long id;
    private IncidentDto incident;
    private ActionDto action;
}
