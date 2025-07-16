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
public class DepartmentDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("code")
    private Long codeDepartment;
    @JsonProperty("name_short")
    private String shortNameDepartment;
    @JsonProperty("name_long")
    private String longNameDepartment;
    @JsonProperty("str_level")
    private Short strLevelDepartment;
    @JsonProperty("parent_code")
    private Long parentCodeDirection;
    @JsonProperty("value_reception")
    private Double valueReceptionDepartment;
    @JsonProperty("value_critical")
    private Double valueCriticalDepartment;
    @JsonProperty("activ")
    private boolean isActiveDepartment;
    @JsonProperty("children")
    private List<GroupDto> groups;
}