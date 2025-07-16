package kz.magnum.magnumback.fastmanservice.model.pdt.odstructure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilialModel {
    private Integer codeFilial;
    private Integer codeSiteTz;
    private String nameSiteTz;
    private Integer codeSiteSp;
    private String nameSiteSp;
    private String format;
    private String nameFil;
    private String city;
    private String addressFil;
}
