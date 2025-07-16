package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListByIncidentAnalyticDto {
    private Long incidentCode;
    private Integer itemsCountDone;
    private Integer itemsCountTotal;
}
