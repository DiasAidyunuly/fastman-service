package kz.magnum.magnumback.fastmanservice.repository.structure;

import kz.magnum.magnumback.fastmanservice.entity.structure.Subgroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface SubgroupRepository extends JpaRepository<Subgroup, Long> {
    @Query("SELECT s.codeSubgroup FROM Subgroup s")
    Set<Long> findSubgroupCodes();

    void deleteByCodeSubgroupIn(Collection<Long> codes);

    @Modifying
    @Query("UPDATE Subgroup s SET " +
        "s.valueReceptionSubgroup = :valueReception, " +
        "s.valueCriticalSubgroup = :valueCritical, " +
        "s.isActiveSubgroup = :isActive " +
        "WHERE s.codeSubgroup in :codes")
    void updateSubgroups(List<Long> codes, Double valueReception, Double valueCritical, boolean isActive);

    @Modifying
    @Query("UPDATE Subgroup s SET " +
        "s.valueReceptionSubgroup = :valueReception, " +
        "s.valueCriticalSubgroup = :valueCritical, " +
        "s.isActiveSubgroup = :isActive " +
        "WHERE s.id = :id")
    void updateSubgroupById(Long id, Double valueReception, Double valueCritical, boolean isActive);
}