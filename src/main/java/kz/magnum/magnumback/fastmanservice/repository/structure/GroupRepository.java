package kz.magnum.magnumback.fastmanservice.repository.structure;

import kz.magnum.magnumback.fastmanservice.entity.structure.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query("SELECT g.codeGroup FROM Group g")
    Set<Long> findGroupCodes();

    void deleteByCodeGroupIn(Collection<Long> codes);

    @Modifying
    @Query("UPDATE Group g SET " +
        "g.valueReceptionGroup = :valueReception, " +
        "g.valueCriticalGroup = :valueCritical, " +
        "g.isActiveGroup = :isActive " +
        "WHERE g.codeGroup in :codes")
    void updateGroups(List<Long> codes, Double valueReception, Double valueCritical, boolean isActive);

    @Modifying
    @Query("UPDATE Group g SET " +
        "g.valueReceptionGroup = :valueReception, " +
        "g.valueCriticalGroup = :valueCritical, " +
        "g.isActiveGroup = :isActive " +
        "WHERE g.id = :id")
    void updateGroupById(Long id, Double valueReception, Double valueCritical, boolean isActive);
}