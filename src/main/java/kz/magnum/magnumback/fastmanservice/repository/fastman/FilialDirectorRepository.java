package kz.magnum.magnumback.fastmanservice.repository.fastman;

import kz.magnum.magnumback.fastmanservice.entity.general.FilialDirector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FilialDirectorRepository extends JpaRepository<FilialDirector, Short> {
    @Modifying
    @Query("UPDATE FilialDirector d SET d.territorialDirector = NULL WHERE d.territorialDirector.id = :territorialDirectorId")
    void setTerritorialDirectorToNull(Short territorialDirectorId);
}
