package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegionalDirectorDto {
    private Short id;
    private Short divisionId;
    private String regionalDirectorName;
    private String regionalDirectorTab;
}
