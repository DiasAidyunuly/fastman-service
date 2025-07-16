package kz.magnum.magnumback.fastmanservice.model.pdt.odstructure;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilialDirectorModel {
    private Short filialDirectorId;
    private String filialDirectorName;
    private String filialDirectorTab;
    private Short parentId;
    private FilialModel filialModel;
    private Byte isOpen;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateOpen;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateClose;
}
