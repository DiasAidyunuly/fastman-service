package kz.magnum.magnumback.fastmanservice.repository.fastman;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.IncAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IncActionRepository extends JpaRepository<IncAction, Long> {
    @Query("SELECT i FROM IncAction i WHERE i.incident.id = :incidentId")
    List<IncAction> findAllByIncidentId(Long incidentId);

    @Modifying
    @Query("DELETE IncAction i WHERE i.action.id = :actionId")
    void deleteByActionId(Short actionId);

    @Modifying
    @Query("DELETE IncAction i WHERE i.incident.id = :incidentId")
    void deleteByIncidentId(Long incidentId);
}
