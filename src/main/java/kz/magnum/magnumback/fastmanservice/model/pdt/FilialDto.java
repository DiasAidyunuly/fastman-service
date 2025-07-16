package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilialDto {
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