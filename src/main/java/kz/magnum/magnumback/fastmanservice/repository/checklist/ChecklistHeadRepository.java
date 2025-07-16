package kz.magnum.magnumback.fastmanservice.repository.checklist;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistHead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ChecklistHeadRepository extends JpaRepository<ChecklistHead, Long> {
    @Query("SELECT c FROM ChecklistHead c WHERE " +
        "c.site = :site " +
        "AND (c.startDate BETWEEN CAST(:startDate AS timestamp) AND CAST(:endDate AS timestamp)) " +
        "AND (('-1' IN (:rolePerformer)) OR (c.checklistHeadTemp.rolePerformer IN (:rolePerformer))) " +
        "AND (('-1' IN (:roleCreator)) OR (c.roleCreator IN (:roleCreator))) " +
        "AND ((-1L IN (:status)) OR (c.checklistHeadStatus.id IN (:status))) " +
        "AND ((-1L IN (:type)) OR (c.checklistHeadTemp.checklistTemplate.id IN (:type))) " +
        "AND ((-1L IN (:temp)) OR (c.checklistHeadTemp.id IN (:temp)))")
    List<ChecklistHead> findAllByFilters(Integer site,
                                         Date startDate,
                                         Date endDate,
                                         List<Long> status,
                                         List<Long> type,
                                         List<Long> temp,
                                         List<String> rolePerformer,
                                         List<String> roleCreator);

    @Query("SELECT DISTINCT c.startDate FROM ChecklistHead c WHERE " +
        "c.site = :site " +
        "AND (c.startDate BETWEEN CAST(:startDate AS timestamp) AND CAST(:endDate AS timestamp)) " +
        "AND (('-1' IN (:rolePerformer)) OR (c.checklistHeadTemp.rolePerformer IN (:rolePerformer))) " +
        "AND (('-1' IN (:roleCreator)) OR (c.roleCreator IN (:roleCreator))) " +
        "AND ((-1L IN (:status)) OR (c.checklistHeadStatus.id IN (:status))) " +
        "AND ((-1L IN (:type)) OR (c.checklistHeadTemp.checklistTemplate.id IN (:type))) " +
        "AND ((-1L IN (:temp)) OR (c.checklistHeadTemp.id IN (:temp)))")
    List<Date> findDateTime(Integer site,
                            Date startDate,
                            Date endDate,
                            List<Long> status,
                            List<Long> type,
                            List<Long> temp,
                            List<String> rolePerformer,
                            List<String> roleCreator);
}