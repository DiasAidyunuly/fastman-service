package kz.magnum.magnumback.fastmanservice.model.pdt.structure;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubgroupDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("code")
    private Long codeSubgroup;
    @JsonProperty("name_short")
    private String shortNameSubgroup;
    @JsonProperty("name_long")
    private String longNameSubgroup;
    @JsonProperty("str_level")
    private Short strLevelSubgroup;
    @JsonProperty("parent_code")
    private Long parentCodeGroup;
    @JsonProperty("value_reception")
    private Double valueReceptionSubgroup;
    @JsonProperty("value_critical")
    private Double valueCriticalSubgroup;
    @JsonProperty("activ")
    private boolean isActiveSubgroup;
}