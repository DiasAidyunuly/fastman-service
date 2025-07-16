package kz.magnum.magnumback.fastmanservice.model;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncActionLocal {
    private Long incidentId;
    private List<IncActionWithoutIncidentId> incActions;
}
