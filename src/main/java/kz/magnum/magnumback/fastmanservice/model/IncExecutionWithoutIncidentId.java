package kz.magnum.magnumback.fastmanservice.model;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.Execution;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncExecutionWithoutIncidentId {
    private Execution execution;
    private Byte mandatory;
    private Byte allowCheck;
}
