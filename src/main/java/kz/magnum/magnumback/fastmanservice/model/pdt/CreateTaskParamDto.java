package kz.magnum.magnumback.fastmanservice.model.pdt;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskParamDto {
    private List<String> itemCodes;
    private Long incidentCode;
    private String description;
    private String executor;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateEnd;
    private byte taskCheck;
    private String photoCreate;
    private String photoExtension;
    private String fileCreate;
    private String fileExtension;
    private String userChange;
    private List<Short> listSiteCode;
    private Short actionCode;
}