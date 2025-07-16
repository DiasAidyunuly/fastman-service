package kz.magnum.magnumback.fastmanservice.model.pdt.checklist;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetChecklistsParam {
    private Integer site;
    private List<Long> status;
    private List<Long> type;
    private List<Long> temp;
    private List<String> performer;
    private List<String> role;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
}