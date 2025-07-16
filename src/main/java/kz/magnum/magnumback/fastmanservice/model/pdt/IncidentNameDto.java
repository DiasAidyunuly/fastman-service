package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncidentNameDto {
    private Long id;
    private String incidentName;
}
