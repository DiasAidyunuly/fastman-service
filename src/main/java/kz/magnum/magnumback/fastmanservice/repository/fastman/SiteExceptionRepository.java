package kz.magnum.magnumback.fastmanservice.repository.fastman;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.SiteException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SiteExceptionRepository extends JpaRepository<SiteException, Long> {
    @Modifying
    @Query("DELETE SiteException s WHERE s.incident.id = :incidentId")
    void deleteByIncidentId(Long incidentId);
}
