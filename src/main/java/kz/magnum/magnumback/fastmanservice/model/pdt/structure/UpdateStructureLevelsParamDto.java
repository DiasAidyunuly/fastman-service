package kz.magnum.magnumback.fastmanservice.model.pdt.structure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStructureLevelsParamDto {
    private Long id;
    private Short strLevel;
    private Double valueReception;
    private Double valueCritical;
    private boolean isActive;
}