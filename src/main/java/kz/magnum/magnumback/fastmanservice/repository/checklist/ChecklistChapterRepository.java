package kz.magnum.magnumback.fastmanservice.repository.checklist;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistChapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChecklistChapterRepository extends JpaRepository<ChecklistChapter, Long> {
    @Query("SELECT c FROM ChecklistChapter c WHERE c.checklistHeadTemp.id = :id")
    List<ChecklistChapter> findByChecklistHeadTempId(Long id);

    @Query("SELECT DISTINCT c.id FROM ChecklistChapter c WHERE c.checklistHeadTemp.id = :id")
    List<Long> findIdsByIdHeadTemp(Long id);
}