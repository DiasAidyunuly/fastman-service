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
public class TerritorialDirectorModel {
    private Short territorialDirectorId;
    private String territorialDirectorName;
    private String territorialDirectorTab;
    private String region;
    private Short parentId;
    private List<FilialDirectorModel> filialDirectorModels;
}
