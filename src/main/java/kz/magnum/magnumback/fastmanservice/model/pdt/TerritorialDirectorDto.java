package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TerritorialDirectorDto {
    private Short id;
    private Short regionalDirectorId;
    private String territorialDirectorName;
    private String territorialDirectorTab;
    private String region;
}
