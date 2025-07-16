package kz.magnum.magnumback.fastmanservice.model.pdt.structure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetGoldDataMapToSubgroupModel {
    private Long sobcint;
    private String sobcext;
    private String tsobdesc;
    private Long strpere;
    private String sobcextin;
}