package kz.magnum.magnumback.fastmanservice.model.pdt.checklist;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistQuestionStatusDto {
    private Long id;
    private String questionStatusName;
}