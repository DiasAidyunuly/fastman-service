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
public class RegionalDirectorModel {
    private Short regionalDirectorId;
    private String regionalDirectorName;
    private String regionalDirectorTab;
    private Short parentId;
    private List<TerritorialDirectorModel> territorialDirectorModels;
}
