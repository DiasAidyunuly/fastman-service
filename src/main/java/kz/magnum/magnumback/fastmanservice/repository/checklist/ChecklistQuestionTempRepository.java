package kz.magnum.magnumback.fastmanservice.repository.checklist;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistQuestionTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChecklistQuestionTempRepository extends JpaRepository<ChecklistQuestionTemp, Long> {
    @Query("SELECT c FROM ChecklistQuestionTemp c WHERE c.checklistChapter.checklistHeadTemp.id = :id")
    List<ChecklistQuestionTemp> findByChecklistHeadTempId(Long id);

    @Query("SELECT count (c.checklistChapter.id) FROM ChecklistQuestionTemp c WHERE c.checklistChapter.id in (:checklistChapterIds)")
    Integer findCountByChecklistChapterIds(List<Long> checklistChapterIds);
}