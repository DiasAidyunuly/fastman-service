package kz.magnum.magnumback.fastmanservice.repository.structure;

import kz.magnum.magnumback.fastmanservice.entity.structure.HeadStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Set;

public interface HeadStructureRepository extends JpaRepository<HeadStructure, Long> {
    @Query("SELECT hs.codeHeadStructure FROM HeadStructure hs")
    Set<Long> findHeadStructureCodes();

    void deleteByCodeHeadStructureIn(Collection<Long> codes);
}