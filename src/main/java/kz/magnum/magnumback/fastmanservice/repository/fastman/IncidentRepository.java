package kz.magnum.magnumback.fastmanservice.repository.fastman;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
    @Modifying
    @Query("UPDATE Incident i SET i.template = NULL WHERE i.template.id = :templateId")
    void setTemplateToNull(Short templateId);

    @Modifying
    @Query("UPDATE Incident i SET i.action = NULL WHERE i.action.id = :actionId")
    void setActionToNull(Short actionId);

    @Query("SELECT MAX(i.priority) FROM Incident i")
    Short findMaxPriority();

    List<Incident> findByPriorityGreaterThan(Short priority);
}
