package kz.magnum.magnumback.fastmanservice.model.pdt.checklist;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistTemplateDto {
    private Long id;
    private String templateName;
}
