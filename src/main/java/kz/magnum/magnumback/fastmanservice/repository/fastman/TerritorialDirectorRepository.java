package kz.magnum.magnumback.fastmanservice.repository.fastman;

import kz.magnum.magnumback.fastmanservice.entity.general.TerritorialDirector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TerritorialDirectorRepository extends JpaRepository<TerritorialDirector, Short> {
    @Modifying
    @Query("UPDATE TerritorialDirector t SET t.regionalDirector = NULL WHERE t.regionalDirector.id = :regionalDirectorId")
    void setRegionalDirectorToNull(Short regionalDirectorId);

    @Query("SELECT td from TerritorialDirector td where td.territorialDirectorTab = :tabNumber")
    List<TerritorialDirector> findByTabNumber(@PathVariable("tabNumber") String tabNumber);
}
