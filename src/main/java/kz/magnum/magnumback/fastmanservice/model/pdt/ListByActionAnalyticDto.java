package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListByActionAnalyticDto {
    private Short actionCode;
    private Integer itemsCountDone;
    private Integer itemsCountTotal;
}
