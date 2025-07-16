package kz.magnum.magnumback.fastmanservice.model.pdt;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompletedItemDto {
    private Short statusCode;
    private Short actionCode;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+5")
    private Date dateAction;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+5")
    private Date dateCompletion;
    private String userChange;
    private List<ExecutionTaskDto> executionTask;
}