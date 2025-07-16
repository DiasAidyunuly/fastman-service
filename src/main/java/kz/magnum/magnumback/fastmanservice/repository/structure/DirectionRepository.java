package kz.magnum.magnumback.fastmanservice.repository.structure;

import kz.magnum.magnumback.fastmanservice.entity.structure.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Set;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
    @Query("SELECT d.codeDirection FROM Direction d")
    Set<Long> findDirectionCodes();

    void deleteByCodeDirectionIn(Collection<Long> codes);

    @Modifying
    @Query("UPDATE Direction d SET " +
        "d.valueReceptionDirection = :valueReception, " +
        "d.valueCriticalDirection = :valueCritical, " +
        "d.isActiveDirection = :isActive " +
        "WHERE d.id = :id")
    void updateDirectionById(Long id, Double valueReception, Double valueCritical, boolean isActive);
}