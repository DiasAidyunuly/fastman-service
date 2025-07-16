package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecutionTaskDto {
    private Long id;
    private Long executionId;
    private String executionValue;
}