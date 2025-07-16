package kz.magnum.magnumback.fastmanservice.model.pdt.checklist;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistHeadDtoParam {
    private List<Integer> site;
    private Long idTemplate;
    private String tabNumCreator;
    private String nameCreator;
    private String roleCreator;
    private String commentCreator;
    private String rolePerformer;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+5")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+5")
    private Date startEndDate;
}