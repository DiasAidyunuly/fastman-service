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
public class StructureOdFilterParam {
    private List<Short> filials;
    private List<String> formats;
    private List<String> cities;
    private List<String> regionalDirectors;
    private List<String> territorialDirectors;
    private List<String> filialDirectors;
    private Byte isOpen;
}
