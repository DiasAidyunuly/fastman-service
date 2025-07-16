package kz.magnum.magnumback.fastmanservice.repository.structure;

import kz.magnum.magnumback.fastmanservice.entity.structure.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("SELECT d.codeDepartment FROM Department d")
    Set<Long> findDepartmentCodes();

    void deleteByCodeDepartmentIn(Collection<Long> codes);

    @Modifying
    @Query("UPDATE Department d SET " +
        "d.valueReceptionDepartment = :valueReception, " +
        "d.valueCriticalDepartment = :valueCritical, " +
        "d.isActiveDepartment = :isActive " +
        "WHERE d.codeDepartment in :codes")
    void updateDepartments(List<Long> codes, Double valueReception, Double valueCritical, boolean isActive);

    @Modifying
    @Query("UPDATE Department d SET " +
        "d.valueReceptionDepartment = :valueReception, " +
        "d.valueCriticalDepartment = :valueCritical, " +
        "d.isActiveDepartment = :isActive " +
        "WHERE d.id = :id")
    void updateDepartmentById(Long id, Double valueReception, Double valueCritical, boolean isActive);
}