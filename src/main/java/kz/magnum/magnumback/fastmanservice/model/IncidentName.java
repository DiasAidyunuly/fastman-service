package kz.magnum.magnumback.fastmanservice.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncidentName {
    private Long id;
    private String incidentName;
}
