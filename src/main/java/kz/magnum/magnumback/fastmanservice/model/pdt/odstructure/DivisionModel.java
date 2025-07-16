package kz.magnum.magnumback.fastmanservice.model.pdt.odstructure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DivisionModel {
    private Short divisionId;
    private String divisionName;
    private List<RegionalDirectorModel> regionalDirectorModels;
}
