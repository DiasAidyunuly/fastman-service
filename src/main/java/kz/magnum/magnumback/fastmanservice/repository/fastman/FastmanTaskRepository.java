package kz.magnum.magnumback.fastmanservice.repository.fastman;

import kz.magnum.magnumback.fastmanservice.entity.fastman.kafka.FastmanTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FastmanTaskRepository extends JpaRepository<FastmanTask, Long> {
    @Query("SELECT ft FROM FastmanTask ft " +
        "WHERE ft.siteCode = :siteCode " +
        "AND ( " +
        "   (ft.isManual = false AND ft.date <= CURRENT_DATE AND DATE(ft.dateEnd) > CURRENT_DATE) " +
        "   OR " +
        "   (ft.isManual = true AND ft.date <= CURRENT_DATE AND ft.dateEnd >= CURRENT_TIMESTAMP) " +
        ")")
    List<FastmanTask> findBySiteCode(Short siteCode);

    @Query("SELECT ft FROM FastmanTask ft " +
        "WHERE ft.siteCode = :siteCode AND ft.incident.id = :incidentCode " +
        "AND ( " +
        "   (ft.isManual = false AND ft.date <= CURRENT_DATE AND DATE(ft.dateEnd) > CURRENT_DATE) " +
        "   OR " +
        "   (ft.isManual = true AND ft.date <= CURRENT_DATE AND ft.dateEnd >= CURRENT_TIMESTAMP) " +
        ")")
    List<FastmanTask> findBySiteCodeAndIncident(Short siteCode, Long incidentCode);

    @Query("SELECT ft FROM FastmanTask ft " +
        "WHERE ft.siteCode = :siteCode AND ft.action.id = :actionCode " +
        "AND ( " +
        "   (ft.isManual = false AND ft.date <= CURRENT_DATE AND DATE(ft.dateEnd) > CURRENT_DATE) " +
        "   OR " +
        "   (ft.isManual = true AND ft.date <= CURRENT_DATE AND ft.dateEnd >= CURRENT_TIMESTAMP) " +
        ")")
    List<FastmanTask> findBySiteCodeAndAction(Short siteCode, Short actionCode);

    @Modifying
    @Query("UPDATE FastmanTask f SET f.action = NULL WHERE f.action.id = :actionId")
    void setActionToNull(Short actionId);

    @Modifying
    @Query("UPDATE FastmanTask f SET f.incident = NULL WHERE f.incident.id = :incidentId")
    void setIncidentToNull(Long incidentId);

    @Modifying
    @Query("UPDATE FastmanTask f SET f.status = NULL WHERE f.status.id = :statusId")
    void setStatusToNull(Short statusId);


    // list by site
    @Query(value = "SELECT count(*) FROM fastman.fastman_tasks ft " +
        "WHERE ((-1 = (:siteCode)) or (ft.site_code = (:siteCode))) " +
        "AND ((-1 in (:statusCodes)) or (ft.status_id in (:statusCodes))) " +
        "AND ((-1 in (:actionCodes)) or (ft.action_id in (:actionCodes))) " +
        "AND ((-1 in (:incidentCodes)) or (ft.incident_id in (:incidentCodes))) " +
        "AND ((DATE('1970-07-07') in (:dates)) or (ft.date in (:dates))) " +
        "AND ((" +
        "  CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) <= INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND ft.date BETWEEN CAST(:endDate AS timestamp) - INTERVAL '90 days' AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) > INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date >= CURRENT_DATE - INTERVAL '90 days'))",
        nativeQuery = true)
    Integer getTotalTasksCountBySiteAndOptionalFilters(Date startDate,
                                                       Date endDate,
                                                       Short siteCode,
                                                       List<Short> statusCodes,
                                                       List<Short> actionCodes,
                                                       List<Long> incidentCodes,
                                                       List<Date> dates);

    @Query(value = "SELECT count(*) FROM fastman.fastman_tasks ft " +
        "WHERE ((-1 = (:siteCode)) or (ft.site_code = (:siteCode))) " +
        "AND (ft.status_id in (:doneStatusCodes)) " +
        "AND ((-1 in (:actionCodes)) or (ft.action_id in (:actionCodes))) " +
        "AND ((-1 in (:incidentCodes)) or (ft.incident_id in (:incidentCodes))) " +
        "AND ((DATE('1970-07-07') in (:dates)) or (ft.date in (:dates))) " +
        "AND ((" +
        "  CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) <= INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND ft.date BETWEEN CAST(:endDate AS timestamp) - INTERVAL '90 days' AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) > INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date >= CURRENT_DATE - INTERVAL '90 days'))",
        nativeQuery = true)
    Integer getDoneTasksCountBySiteAndOptionalFilters(Date startDate,
                                                      Date endDate,
                                                      Short siteCode,
                                                      List<Short> doneStatusCodes,
                                                      List<Short> actionCodes,
                                                      List<Long> incidentCodes,
                                                      List<Date> dates);

    @Query(value = "SELECT DISTINCT ft.site_code FROM fastman.fastman_tasks ft " +
        "WHERE ((-1 IN (:statusCodes)) OR (ft.status_id IN (:statusCodes))) " +
        "AND ((-1 IN (:actionCodes)) OR (ft.action_id IN (:actionCodes))) " +
        "AND ((-1 IN (:incidentCodes)) OR (ft.incident_id IN (:incidentCodes))) " +
        "AND ((DATE('1970-07-07') IN (:dates)) OR (ft.date IN (:dates))) " +
        "AND ((" +
        "  CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) <= INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND ft.date BETWEEN CAST(:endDate AS timestamp) - INTERVAL '90 days' AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) > INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date >= CURRENT_DATE - INTERVAL '90 days'))",
        nativeQuery = true)
    List<Short> findSites(Date startDate,
                          Date endDate,
                          List<Short> statusCodes,
                          List<Short> actionCodes,
                          List<Long> incidentCodes,
                          List<Date> dates);

    @Query(value = "SELECT ft.site_code AS siteCode, " +
        "COUNT(*) AS totalCount, " +
        "SUM(CASE WHEN ft.status_id IN (3, 4) THEN 1 ELSE 0 END) AS doneCount " +
        "FROM fastman.fastman_tasks ft " +
        "WHERE ft.site_code IN :siteCodes " +
        "AND ((-1 IN (:statusCodes)) OR (ft.status_id IN (:statusCodes))) " +
        "AND ((-1 IN (:actionCodes)) OR (ft.action_id IN (:actionCodes))) " +
        "AND ((-1 IN (:incidentCodes)) OR (ft.incident_id IN (:incidentCodes))) " +
        "AND ((DATE('1970-07-07') IN (:dates)) OR (ft.date IN (:dates))) " +
        "AND ((" +
        "  CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) <= INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND ft.date BETWEEN CAST(:endDate AS timestamp) - INTERVAL '90 days' AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) > INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date >= CURRENT_DATE - INTERVAL '90 days')) " +
        "GROUP BY ft.site_code",
        nativeQuery = true)
    List<Object[]> findGroupedBySiteAndCount(Date startDate,
                                             Date endDate,
                                             List<Short> siteCodes,
                                             List<Short> statusCodes,
                                             List<Short> actionCodes,
                                             List<Long> incidentCodes,
                                             List<Date> dates);


    // list by status
    @Query(value = "SELECT count(*) FROM fastman.fastman_tasks ft " +
        "WHERE ((-1 in (:siteCodes)) or (ft.site_code in (:siteCodes))) " +
        "AND ((-1 = (:statusCode)) or (ft.status_id = (:statusCode))) " +
        "AND ((-1 in (:actionCodes)) or (ft.action_id in (:actionCodes))) " +
        "AND ((-1 in (:incidentCodes)) or (ft.incident_id in (:incidentCodes))) " +
        "AND ((DATE('1970-07-07') in (:dates)) or (ft.date in (:dates))) " +
        "AND ((" +
        "  CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) <= INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND ft.date BETWEEN CAST(:endDate AS timestamp) - INTERVAL '90 days' AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) > INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date >= CURRENT_DATE - INTERVAL '90 days'))",
        nativeQuery = true)
    Integer getTotalTasksCountByStatusAndOptionalFilters(Date startDate,
                                                         Date endDate,
                                                         List<Short> siteCodes,
                                                         Short statusCode,
                                                         List<Short> actionCodes,
                                                         List<Long> incidentCodes,
                                                         List<Date> dates);

    @Query(value = "SELECT count(*) FROM fastman.fastman_tasks ft " +
        "WHERE ((-1 in (:siteCodes)) or (ft.site_code in (:siteCodes))) " +
        "AND (ft.status_id in (:doneStatusCodes)) " +
        "AND ((-1 in (:actionCodes)) or (ft.action_id in (:actionCodes))) " +
        "AND ((-1 in (:incidentCodes)) or (ft.incident_id in (:incidentCodes))) " +
        "AND ((DATE('1970-07-07') in (:dates)) or (ft.date in (:dates))) " +
        "AND ((" +
        "  CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) <= INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND ft.date BETWEEN CAST(:endDate AS timestamp) - INTERVAL '90 days' AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) > INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date >= CURRENT_DATE - INTERVAL '90 days'))",
        nativeQuery = true)
    Integer getDoneTasksCountByStatusAndOptionalFilters(Date startDate,
                                                        Date endDate,
                                                        List<Short> siteCodes,
                                                        List<Short> doneStatusCodes,
                                                        List<Short> actionCodes,
                                                        List<Long> incidentCodes,
                                                        List<Date> dates);

    @Query(value = "SELECT DISTINCT ft.status_id FROM fastman.fastman_tasks ft " +
        "WHERE ((-1 IN (:siteCodes)) OR (ft.site_code IN (:siteCodes))) " +
        "AND ((-1 IN (:actionCodes)) OR (ft.action_id IN (:actionCodes))) " +
        "AND ((-1 IN (:incidentCodes)) OR (ft.incident_id IN (:incidentCodes))) " +
        "AND ((DATE('1970-07-07') IN (:dates)) OR (ft.date IN (:dates))) " +
        "AND ((" +
        "  CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) <= INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND ft.date BETWEEN CAST(:endDate AS timestamp) - INTERVAL '90 days' AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) > INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date >= CURRENT_DATE - INTERVAL '90 days'))",
        nativeQuery = true)
    List<Short> findStatuses(Date startDate,
                             Date endDate,
                             List<Short> siteCodes,
                             List<Short> actionCodes,
                             List<Long> incidentCodes,
                             List<Date> dates);

    @Query(value = "SELECT ft.status_id AS statusCode, " +
        "COUNT(*) AS totalCount, " +
        "SUM(CASE WHEN ft.status_id IN (3, 4) THEN 1 ELSE 0 END) AS doneCount " +
        "FROM fastman.fastman_tasks ft " +
        "WHERE ft.status_id IN :statusCodes " +
        "AND ((-1 IN (:siteCodes)) OR (ft.site_code IN (:siteCodes))) " +
        "AND ((-1 IN (:actionCodes)) OR (ft.action_id IN (:actionCodes))) " +
        "AND ((-1 IN (:incidentCodes)) OR (ft.incident_id IN (:incidentCodes))) " +
        "AND ((DATE('1970-07-07') IN (:dates)) OR (ft.date IN (:dates))) " +
        "AND ((" +
        "  CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) <= INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND ft.date BETWEEN CAST(:endDate AS timestamp) - INTERVAL '90 days' AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) > INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date >= CURRENT_DATE - INTERVAL '90 days')) " +
        "GROUP BY ft.status_id",
        nativeQuery = true)
    List<Object[]> findGroupedByStatusAndCount(Date startDate,
                                               Date endDate,
                                               List<Short> siteCodes,
                                               List<Short> statusCodes,
                                               List<Short> actionCodes,
                                               List<Long> incidentCodes,
                                               List<Date> dates);


    // list by action
    @Query(value = "SELECT count(*) FROM fastman.fastman_tasks ft " +
        "WHERE ((-1 in (:siteCodes)) or (ft.site_code in (:siteCodes))) " +
        "AND ((-1 in (:statusCodes)) or (ft.status_id in (:statusCodes))) " +
        "AND ((-1 = (:actionCode)) or (ft.action_id = (:actionCode))) " +
        "AND ((-1 in (:incidentCodes)) or (ft.incident_id in (:incidentCodes))) " +
        "AND ((DATE('1970-07-07') in (:dates)) or (ft.date in (:dates))) " +
        "AND ((" +
        "  CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) <= INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND ft.date BETWEEN CAST(:endDate AS timestamp) - INTERVAL '90 days' AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) > INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date >= CURRENT_DATE - INTERVAL '90 days'))",
        nativeQuery = true)
    Integer getTotalTasksCountByActionAndOptionalFilters(Date startDate,
                                                         Date endDate,
                                                         List<Short> siteCodes,
                                                         List<Short> statusCodes,
                                                         Short actionCode,
                                                         List<Long> incidentCodes,
                                                         List<Date> dates);

    @Query(value = "SELECT count(*) FROM fastman.fastman_tasks ft " +
        "WHERE ((-1 in (:siteCodes)) or (ft.site_code in (:siteCodes))) " +
        "AND (ft.status_id in (:doneStatusCodes)) " +
        "AND ((-1 = (:actionCode)) or (ft.action_id = (:actionCode))) " +
        "AND ((-1 in (:incidentCodes)) or (ft.incident_id in (:incidentCodes))) " +
        "AND ((DATE('1970-07-07') in (:dates)) or (ft.date in (:dates))) " +
        "AND ((" +
        "  CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) <= INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND ft.date BETWEEN CAST(:endDate AS timestamp) - INTERVAL '90 days' AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) > INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date >= CURRENT_DATE - INTERVAL '90 days'))",
        nativeQuery = true)
    Integer getDoneTasksCountByActionAndOptionalFilters(Date startDate,
                                                        Date endDate,
                                                        List<Short> siteCodes,
                                                        List<Short> doneStatusCodes,
                                                        Short actionCode,
                                                        List<Long> incidentCodes,
                                                        List<Date> dates);

    @Query(value = "SELECT DISTINCT ft.action_id FROM fastman.fastman_tasks ft " +
        "WHERE ((-1 IN (:siteCodes)) OR (ft.site_code IN (:siteCodes))) " +
        "AND ((-1 IN (:statusCodes)) OR (ft.status_id IN (:statusCodes))) " +
        "AND ((-1 IN (:incidentCodes)) OR (ft.incident_id IN (:incidentCodes))) " +
        "AND ((DATE('1970-07-07') IN (:dates)) OR (ft.date IN (:dates))) " +
        "AND ((" +
        "  CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) <= INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND ft.date BETWEEN CAST(:endDate AS timestamp) - INTERVAL '90 days' AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) > INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date >= CURRENT_DATE - INTERVAL '90 days'))",
        nativeQuery = true)
    List<Short> findActions(Date startDate,
                            Date endDate,
                            List<Short> siteCodes,
                            List<Short> statusCodes,
                            List<Long> incidentCodes,
                            List<Date> dates);

    @Query(value = "SELECT ft.action_id AS actionCode, " +
        "COUNT(*) AS totalCount, " +
        "SUM(CASE WHEN ft.status_id IN (3, 4) THEN 1 ELSE 0 END) AS doneCount " +
        "FROM fastman.fastman_tasks ft " +
        "WHERE ft.action_id IN :actionCodes " +
        "AND ((-1 IN (:statusCodes)) OR (ft.status_id IN (:statusCodes))) " +
        "AND ((-1 IN (:siteCodes)) OR (ft.site_code IN (:siteCodes))) " +
        "AND ((-1 IN (:incidentCodes)) OR (ft.incident_id IN (:incidentCodes))) " +
        "AND ((DATE('1970-07-07') IN (:dates)) OR (ft.date IN (:dates))) " +
        "AND ((" +
        "  CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) <= INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND ft.date BETWEEN CAST(:endDate AS timestamp) - INTERVAL '90 days' AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) > INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date >= CURRENT_DATE - INTERVAL '90 days')) " +
        "GROUP BY ft.action_id",
        nativeQuery = true)
    List<Object[]> findGroupedByActionAndCount(Date startDate,
                                               Date endDate,
                                               List<Short> siteCodes,
                                               List<Short> statusCodes,
                                               List<Short> actionCodes,
                                               List<Long> incidentCodes,
                                               List<Date> dates);

    // list by incident
    @Query(value = "SELECT count(*) FROM fastman.fastman_tasks ft " +
        "WHERE ((-1 in (:siteCodes)) or (ft.site_code in (:siteCodes))) " +
        "AND ((-1 in (:statusCodes)) or (ft.status_id in (:statusCodes))) " +
        "AND ((-1 in (:actionCodes)) or (ft.action_id in (:actionCodes))) " +
        "AND ((-1 = (:incidentCode)) or (ft.incident_id = (:incidentCode))) " +
        "AND ((DATE('1970-07-07') in (:dates)) or (ft.date in (:dates))) " +
        "AND ((" +
        "  CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) <= INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND ft.date BETWEEN CAST(:endDate AS timestamp) - INTERVAL '90 days' AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) > INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date >= CURRENT_DATE - INTERVAL '90 days'))",
        nativeQuery = true)
    Integer getTotalTasksCountByIncidentAndOptionalFilters(Date startDate,
                                                           Date endDate,
                                                           List<Short> siteCodes,
                                                           List<Short> statusCodes,
                                                           List<Short> actionCodes,
                                                           Long incidentCode,
                                                           List<Date> dates);

    @Query(value = "SELECT count(*) FROM fastman.fastman_tasks ft " +
        "WHERE ((-1 in (:siteCodes)) or (ft.site_code in (:siteCodes))) " +
        "AND (ft.status_id in (:doneStatusCodes)) " +
        "AND ((-1 in (:actionCodes)) or (ft.action_id in (:actionCodes))) " +
        "AND ((-1 = (:incidentCode)) or (ft.incident_id = (:incidentCode))) " +
        "AND ((DATE('1970-07-07') in (:dates)) or (ft.date in (:dates))) " +
        "AND ((" +
        "  CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) <= INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND ft.date BETWEEN CAST(:endDate AS timestamp) - INTERVAL '90 days' AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) > INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date >= CURRENT_DATE - INTERVAL '90 days'))",
        nativeQuery = true)
    Integer getDoneTasksCountByIncidentAndOptionalFilters(Date startDate,
                                                          Date endDate,
                                                          List<Short> siteCodes,
                                                          List<Short> doneStatusCodes,
                                                          List<Short> actionCodes,
                                                          Long incidentCode,
                                                          List<Date> dates);

    @Query(value = "SELECT DISTINCT ft.incident_id FROM fastman.fastman_tasks ft " +
        "WHERE ((-1 IN (:siteCodes)) OR (ft.site_code IN (:siteCodes))) " +
        "AND ((-1 IN (:statusCodes)) OR (ft.status_id IN (:statusCodes))) " +
        "AND ((-1 IN (:actionCodes)) OR (ft.action_id IN (:actionCodes))) " +
        "AND ((DATE('1970-07-07') IN (:dates)) OR (ft.date IN (:dates))) " +
        "AND ((" +
        "  CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) <= INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND ft.date BETWEEN CAST(:endDate AS timestamp) - INTERVAL '90 days' AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) > INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date >= CURRENT_DATE - INTERVAL '90 days'))",
        nativeQuery = true)
    List<Long> findIncidents(Date startDate,
                             Date endDate,
                             List<Short> siteCodes,
                             List<Short> statusCodes,
                             List<Short> actionCodes,
                             List<Date> dates);

    @Query(value = "SELECT ft.incident_id AS incidentCode, " +
        "COUNT(*) AS totalCount, " +
        "SUM(CASE WHEN ft.status_id IN (3, 4) THEN 1 ELSE 0 END) AS doneCount " +
        "FROM fastman.fastman_tasks ft " +
        "WHERE ft.incident_id IN :incidentCodes " +
        "AND ((-1 IN (:statusCodes)) OR (ft.status_id IN (:statusCodes))) " +
        "AND ((-1 IN (:siteCodes)) OR (ft.site_code IN (:siteCodes))) " +
        "AND ((-1 IN (:actionCodes)) OR (ft.action_id IN (:actionCodes))) " +
        "AND ((DATE('1970-07-07') IN (:dates)) OR (ft.date IN (:dates))) " +
        "AND ((" +
        "  CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) <= INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND ft.date BETWEEN CAST(:endDate AS timestamp) - INTERVAL '90 days' AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) > INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date >= CURRENT_DATE - INTERVAL '90 days')) " +
        "GROUP BY ft.incident_id",
        nativeQuery = true)
    List<Object[]> findGroupedByIncidentAndCount(Date startDate,
                                                 Date endDate,
                                                 List<Short> siteCodes,
                                                 List<Short> statusCodes,
                                                 List<Short> actionCodes,
                                                 List<Long> incidentCodes,
                                                 List<Date> dates);

    // list by date
    @Query(value = "SELECT count(*) FROM fastman.fastman_tasks ft " +
        "WHERE ((-1 in (:siteCodes)) or (ft.site_code in (:siteCodes))) " +
        "AND ((-1 in (:statusCodes)) or (ft.status_id in (:statusCodes))) " +
        "AND ((-1 in (:actionCodes)) or (ft.action_id in (:actionCodes))) " +
        "AND ((-1 in (:incidentCodes)) or (ft.incident_id in (:incidentCodes))) " +
        "AND ((DATE('1970-07-07') = (:date)) or (ft.date = (:date))) " +
        "AND ((" +
        "  CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) <= INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND ft.date BETWEEN CAST(:endDate AS timestamp) - INTERVAL '90 days' AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) > INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date >= CURRENT_DATE - INTERVAL '90 days'))",
        nativeQuery = true)
    Integer getTotalTasksCountByDateAndOptionalFilters(Date startDate,
                                                       Date endDate,
                                                       List<Short> siteCodes,
                                                       List<Short> statusCodes,
                                                       List<Short> actionCodes,
                                                       List<Long> incidentCodes,
                                                       Date date);

    @Query(value = "SELECT count(*) FROM fastman.fastman_tasks ft " +
        "WHERE ((-1 in (:siteCodes)) or (ft.site_code in (:siteCodes))) " +
        "AND (ft.status_id in (:doneStatusCodes)) " +
        "AND ((-1 in (:actionCodes)) or (ft.action_id in (:actionCodes))) " +
        "AND ((-1 in (:incidentCodes)) or (ft.incident_id in (:incidentCodes))) " +
        "AND ((DATE('1970-07-07') = (:date)) or (ft.date = (:date))) " +
        "AND ((" +
        "  CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) <= INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND ft.date BETWEEN CAST(:endDate AS timestamp) - INTERVAL '90 days' AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) > INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date >= CURRENT_DATE - INTERVAL '90 days'))",
        nativeQuery = true)
    Integer getDoneTasksCountByDateAndOptionalFilters(Date startDate,
                                                      Date endDate,
                                                      List<Short> siteCodes,
                                                      List<Short> doneStatusCodes,
                                                      List<Short> actionCodes,
                                                      List<Long> incidentCodes,
                                                      Date date);

    @Query(value = "SELECT DISTINCT ft.date FROM fastman.fastman_tasks ft " +
        "WHERE ((-1 IN (:statusCodes)) OR (ft.status_id IN (:statusCodes))) " +
        "AND ((-1 IN (:actionCodes)) OR (ft.action_id IN (:actionCodes))) " +
        "AND ((-1 IN (:incidentCodes)) OR (ft.incident_id IN (:incidentCodes))) " +
        "AND ((-1 in (:siteCodes)) or (ft.site_code in (:siteCodes))) " +
        "AND ((" +
        "  CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) <= INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND ft.date BETWEEN CAST(:endDate AS timestamp) - INTERVAL '90 days' AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) > INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date >= CURRENT_DATE - INTERVAL '90 days'))",
        nativeQuery = true)
    List<Date> findDates(Date startDate,
                          Date endDate,
                          List<Short> siteCodes,
                          List<Short> statusCodes,
                          List<Short> actionCodes,
                          List<Long> incidentCodes);

    @Query(value = "SELECT ft.date AS analyticDate, " +
        "COUNT(*) AS totalCount, " +
        "SUM(CASE WHEN ft.status_id IN (3, 4) THEN 1 ELSE 0 END) AS doneCount " +
        "FROM fastman.fastman_tasks ft " +
        "WHERE ft.date IN :dates " +
        "AND ((-1 IN (:statusCodes)) OR (ft.status_id IN (:statusCodes))) " +
        "AND ((-1 IN (:siteCodes)) OR (ft.site_code IN (:siteCodes))) " +
        "AND ((-1 IN (:actionCodes)) OR (ft.action_id IN (:actionCodes))) " +
        "AND ((-1 IN (:incidentCodes)) OR (ft.incident_id IN (:incidentCodes))) " +
        "AND ((" +
        "  CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) <= INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND ft.date BETWEEN CAST(:endDate AS timestamp) - INTERVAL '90 days' AND CAST(:endDate AS timestamp)) " +
        "  OR (CAST(:startDate AS timestamp) IS NOT NULL AND CAST(:endDate AS timestamp) IS NOT NULL " +
        "  AND (CAST(:endDate AS timestamp) - CAST(:startDate AS timestamp)) > INTERVAL '90 days' " +
        "  AND ft.date BETWEEN CAST(:startDate AS timestamp) AND CAST(:startDate AS timestamp) + INTERVAL '90 days') " +
        "  OR (CAST(:startDate AS timestamp) IS NULL AND CAST(:endDate AS timestamp) IS NULL " +
        "  AND ft.date >= CURRENT_DATE - INTERVAL '90 days')) " +
        "GROUP BY ft.date",
        nativeQuery = true)
    List<Object[]> findGroupedByDateAndCount(Date startDate,
                                             Date endDate,
                                             List<Short> siteCodes,
                                             List<Short> statusCodes,
                                             List<Short> actionCodes,
                                             List<Long> incidentCodes,
                                             List<Date> dates);
}
