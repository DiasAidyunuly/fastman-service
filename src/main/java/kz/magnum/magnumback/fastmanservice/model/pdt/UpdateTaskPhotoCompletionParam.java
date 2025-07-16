package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskPhotoCompletionParam {
    private Long executionTaskId;
    private Long executionId;
    private String photoCompletion;
    private String photoExtension;
}