package kz.magnum.magnumback.fastmanservice.repository.fastman;

import kz.magnum.magnumback.fastmanservice.entity.general.RegionalDirector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface RegionalDirectorRepository extends JpaRepository<RegionalDirector, Short> {
    @Modifying
    @Query("UPDATE RegionalDirector r SET r.division = NULL WHERE r.division.id = :divisionId")
    void setDivisionToNull(Short divisionId);

    @Query("SELECT rd from RegionalDirector rd where rd.regionalDirectorTab = :tabNumber")
    List<RegionalDirector> findByTabNumber(@PathVariable("tabNumber") String tabNumber);
}
