package kz.magnum.magnumback.fastmanservice.model.pdt.checklist;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistHeadTempDto {
    private Long id;
    private String headName;
    private ChecklistTemplateDto checklistTemplateDto;
    private Short periodForManualTasks;
    private boolean isOnlyTheCreator;
    private boolean isNeedPlanning;
    private boolean isPhotoGallery;
    private String rolePerformer;
}