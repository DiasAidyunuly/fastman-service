package kz.magnum.magnumback.fastmanservice.repository.fastman;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.IncExecution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IncExecutionRepository extends JpaRepository<IncExecution, Long> {
    @Query("SELECT i FROM IncExecution i WHERE i.incident.id = :incidentId")
    List<IncExecution> findAllByIncidentId(Long incidentId);

    @Modifying
    @Query("DELETE IncExecution i WHERE i.execution.id = :executionId")
    void deleteByExecutionId(Long executionId);

    @Modifying
    @Query("DELETE IncExecution i WHERE i.incident.id = :incidentId")
    void deleteByIncidentId(Long incidentId);
}
