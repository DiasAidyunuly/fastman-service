package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListByStatusAnalyticDto {
    private Short statusCode;
    private Integer itemsCountDone;
    private Integer itemsCountTotal;
}
