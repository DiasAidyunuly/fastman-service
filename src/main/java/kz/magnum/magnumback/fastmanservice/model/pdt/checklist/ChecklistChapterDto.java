package kz.magnum.magnumback.fastmanservice.model.pdt.checklist;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistChapterDto {
    private Long id;
    private String chapterName;
    private Boolean isGrouped;
    private ChecklistHeadTempDto checklistHeadTempDto;
}