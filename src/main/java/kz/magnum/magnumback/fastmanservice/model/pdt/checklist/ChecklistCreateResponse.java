package kz.magnum.magnumback.fastmanservice.model.pdt.checklist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistCreateResponse {
    @JsonProperty("Success save")
    private ChecklistCreateSuccess successSave;
    @JsonProperty("Error save")
    private ChecklistCreateFail failSave;
}