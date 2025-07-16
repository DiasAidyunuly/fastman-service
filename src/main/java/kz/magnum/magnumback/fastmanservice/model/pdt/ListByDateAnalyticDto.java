package kz.magnum.magnumback.fastmanservice.model.pdt;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListByDateAnalyticDto {
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+5")
    private Date date;
    private Integer itemsCountDone;
    private Integer itemsCountTotal;
}
