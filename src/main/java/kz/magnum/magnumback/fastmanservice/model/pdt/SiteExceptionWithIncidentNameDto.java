package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SiteExceptionWithIncidentNameDto {
    private Long id;
    private String incidentName;
    private Short locationId;
    private Integer excQtyTask;
    private String siteName;
}
