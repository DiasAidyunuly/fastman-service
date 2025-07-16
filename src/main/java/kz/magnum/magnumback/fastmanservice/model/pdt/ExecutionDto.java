package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecutionDto {
    private Long id;
    private String executionName;
    private Long typeOfExecutionId;
}
