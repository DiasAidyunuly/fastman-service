package kz.magnum.magnumback.fastmanservice.model.pdt;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FastmanTaskDto {
    private String itemCode;
    private Long incidentCode;
    private String description;
    private String executor;
    private Short statusCode;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateEnd;
    private byte taskCheck;
    private String photoCreate;
    private String fileTask;
    private String userChange;
    private Short siteCode;
    private boolean isManual;
    private Short actionCode;
}