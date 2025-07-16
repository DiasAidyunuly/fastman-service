package kz.magnum.magnumback.fastmanservice.model.pdt;

import com.fasterxml.jackson.annotation.JsonFormat;
import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.ExecutionTask;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompletedItemModel {
    private Short statusCode;
    private Short actionCode;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+5")
    private Date dateAction;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+5")
    private Date dateCompletion;
    private String userChange;
    private List<ExecutionTask> executionTask;
}