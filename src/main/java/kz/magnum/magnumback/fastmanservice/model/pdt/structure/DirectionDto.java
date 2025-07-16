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
public class DirectionDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("code")
    private Long codeDirection;
    @JsonProperty("name_short")
    private String shortNameDirection;
    @JsonProperty("name_long")
    private String longNameDirection;
    @JsonProperty("str_level")
    private Short strLevelDirection;
    @JsonProperty("parent_code")
    private Long parentCodeHead;
    @JsonProperty("value_reception")
    private Double valueReceptionDirection;
    @JsonProperty("value_critical")
    private Double valueCriticalDirection;
    @JsonProperty("activ")
    private boolean isActiveDirection;
    @JsonProperty("children")
    private List<DepartmentDto> departments;
}