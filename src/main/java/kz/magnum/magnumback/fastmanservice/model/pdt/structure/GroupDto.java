package kz.magnum.magnumback.fastmanservice.model.pdt.structure;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("code")
    private Long codeGroup;
    @JsonProperty("name_short")
    private String shortNameGroup;
    @JsonProperty("name_long")
    private String longNameGroup;
    @JsonProperty("str_level")
    private Short strLevelGroup;
    @JsonProperty("parent_code")
    private Long parentCodeDepartment;
    @JsonProperty("value_reception")
    private Double valueReceptionGroup;
    @JsonProperty("value_critical")
    private Double valueCriticalGroup;
    @JsonProperty("activ")
    private boolean isActiveGroup;
    @JsonProperty("children")
    private List<SubgroupDto> subgroups;
}