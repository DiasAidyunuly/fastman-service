package kz.magnum.magnumback.fastmanservice.model.pdt;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskParamModel {
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