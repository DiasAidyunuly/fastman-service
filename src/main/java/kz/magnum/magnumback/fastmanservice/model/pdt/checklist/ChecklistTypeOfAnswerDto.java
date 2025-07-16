package kz.magnum.magnumback.fastmanservice.model.pdt.checklist;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistTypeOfAnswerDto {
    private Long id;
    private String typeName;
    private Short elementUIType;
}
