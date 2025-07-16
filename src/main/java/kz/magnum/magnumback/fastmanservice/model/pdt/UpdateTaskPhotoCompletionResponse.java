package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskPhotoCompletionResponse {
    private Long fastmanTaskId;
    private Long executionTaskId;
    private Long executionId;
    private String executionValue;
}