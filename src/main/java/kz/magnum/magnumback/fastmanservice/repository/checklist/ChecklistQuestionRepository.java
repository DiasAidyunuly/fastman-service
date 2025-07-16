package kz.magnum.magnumback.fastmanservice.repository.checklist;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChecklistQuestionRepository extends JpaRepository<ChecklistQuestion, Long> {
    @Query("SELECT c FROM ChecklistQuestion c " +
        "WHERE c.checklistHead.id = :headId " +
        "AND ((-1L = :showQuestionStatuses) OR (c.checklistQuestionStatus.id = :showQuestionStatuses))")
    List<ChecklistQuestion> findByChecklistHeadAndChecklistQuestionStatus(Long headId, Long showQuestionStatuses);
}