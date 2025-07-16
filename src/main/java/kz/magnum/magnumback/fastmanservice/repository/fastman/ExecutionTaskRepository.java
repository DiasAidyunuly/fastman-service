package kz.magnum.magnumback.fastmanservice.repository.fastman;

import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.ExecutionTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExecutionTaskRepository extends JpaRepository<ExecutionTask, Long> {
    @Query("SELECT e FROM ExecutionTask e WHERE e.fastmanTask.id = :id")
    List<ExecutionTask> findByFastmanTaskId(Long id);
}