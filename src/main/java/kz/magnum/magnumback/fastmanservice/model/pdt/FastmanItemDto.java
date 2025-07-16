package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FastmanItemDto {
    private Long fastmanTaskId;
    private String itemCode;
    private Short siteCode;
    private String supplierName;
    private Long incidentCode;
    private Short statusCode;
    private Short actionCode;
    private Date date;
    private Double stockQty;
    private LocalDateTime dateAction;
    private String executor;
    private String description;
    private LocalDateTime dateCreate;
    private LocalDateTime dateEnd;
    private LocalDateTime dateCompletion;
    private LocalDateTime dateCheck;
    private Byte taskCheck;
}