package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompletedItemParam {
    private Short statusCode;
    private Short actionCode;
    private String userChange;
    private List<ExecutionTaskDto> executionTasks;
}