package kz.magnum.magnumback.fastmanservice.repository.checklist;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistQuestionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChecklistQuestionStatusRepository extends JpaRepository<ChecklistQuestionStatus, Long> {
}