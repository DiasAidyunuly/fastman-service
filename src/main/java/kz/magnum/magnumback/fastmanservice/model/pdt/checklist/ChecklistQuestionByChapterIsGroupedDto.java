package kz.magnum.magnumback.fastmanservice.model.pdt.checklist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistQuestionByChapterIsGroupedDto {
    private List<ChecklistChapterIsGroupedTrueDto> checklistChapterIsGroupedTrueList;
    private List<ChecklistChapterIsGroupedFalseDto> checklistChapterIsGroupedFalseList;
}