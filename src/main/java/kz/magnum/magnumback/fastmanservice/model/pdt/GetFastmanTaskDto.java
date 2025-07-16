package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetFastmanTaskDto {
    private Long fastmanTaskId;
    private String itemCode;
    private Short siteCode;
    private String supplierName;
    private Long incidentCode;
    private Short statusCode;
    private Short actionCode;
    private Date date;
    private Double price;
    private Double stockQty;
    private LocalDateTime dateAction;
    private String executor;
    private String description;
    private LocalDateTime dateCreate;
    private LocalDateTime dateEnd;
    private LocalDateTime dateCompletion;
    private String photoCreate;
    private String photoOriginal;
    private String fileTask;
    private LocalDateTime dateCheck;
    private Byte taskCheck;
    private List<ExecutionTaskDto> executionTasks;
}