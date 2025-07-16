package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListBySiteAnalyticDto {
    private Short siteCode;
    private Integer itemsCountDone;
    private Integer itemsCountTotal;
}
