package kz.magnum.magnumback.fastmanservice.model.pdt;

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
public class FilialDirectorDto {
    private Short id;
    private Short territorialDirectorId;
    private Integer codeFilial;
    private String filialDirectorName;
    private String filialDirectorTab;
    private Byte isOpen;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+5")
    private Date dateOpen;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+5")
    private Date dateClose;
}
