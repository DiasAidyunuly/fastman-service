package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SiteExceptionDto {
    private Long id;
    private Integer excQtyTask;
    private IncidentDto incident;
    private Short locationId;
    private String siteName;
}
