package kz.magnum.magnumback.fastmanservice.service.impl;

import kz.magnum.magnumback.fastmanservice.model.pdt.*;
import kz.magnum.magnumback.fastmanservice.repository.fastman.FastmanTaskRepository;
import kz.magnum.magnumback.fastmanservice.service.AnalyticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnalyticServiceImpl implements AnalyticService {
    private static final Short defaultValueShort = -1;
    private static final Long defaultValueLong = -1L;
    private static final String dateDefaultValueString = "1970-07-07T00:00:00Z";
    private static final Date dateDefaultValue = Date.from(Instant.parse(dateDefaultValueString));

    private final FastmanTaskRepository fastmanTaskRepository;

    @Override
    public List<?> getTasks(Date startDate,
                            Date endDate,
                            String listBy,
                            List<Short> siteCodes,
                            List<Short> statusCodes,
                            List<Short> actionCodes,
                            List<Long> incidentCodes,
                            List<Date> dates) {

        siteCodes = siteCodes != null ? siteCodes : List.of(defaultValueShort);
        statusCodes = statusCodes != null ? statusCodes : List.of(defaultValueShort);
        actionCodes = actionCodes != null ? actionCodes : List.of(defaultValueShort);
        incidentCodes = incidentCodes != null ? incidentCodes : List.of(defaultValueLong);
        dates = dates != null ? dates : List.of(dateDefaultValue);

        switch (listBy) {
            case "site":
                return getTasksBySite(startDate, endDate, siteCodes, statusCodes, actionCodes, incidentCodes, dates);
            case "status":
                return getTasksByStatus(startDate, endDate, siteCodes, statusCodes, actionCodes, incidentCodes, dates);
            case "action":
                return getTasksByAction(startDate, endDate, siteCodes, statusCodes, actionCodes, incidentCodes, dates);
            case "incident":
                return getTasksByIncident(startDate, endDate, siteCodes, statusCodes, actionCodes, incidentCodes, dates);
            case "date":
                return getTasksByDate(startDate, endDate, siteCodes, statusCodes, actionCodes, incidentCodes, dates);
            default:
                return null;
        }
    }

    private List<ListBySiteAnalyticDto> getTasksBySite(Date startDate,
                                                       Date endDate,
                                                       List<Short> siteCodes,
                                                       List<Short> statusCodes,
                                                       List<Short> actionCodes,
                                                       List<Long> incidentCodes,
                                                       List<Date> dates) {
        List<ListBySiteAnalyticDto> resultList = new ArrayList<>();
        if (siteCodes.contains((short) -1)) {
            siteCodes = fastmanTaskRepository
                .findSites(startDate, endDate, statusCodes, actionCodes, incidentCodes, dates);

            List<Object[]> listGroupedBySiteAndCount = fastmanTaskRepository
                .findGroupedBySiteAndCount(startDate, endDate, siteCodes, statusCodes, actionCodes, incidentCodes, dates);

            listGroupedBySiteAndCount.forEach(e -> {
                Short siteCode = ((Number) e[0]).shortValue();
                Integer totalCount = ((Number) e[1]).intValue();
                Integer doneCount = ((Number) e[2]).intValue();
                resultList.add(createListBySiteAnalyticDto(siteCode, totalCount, doneCount));
            });
            return resultList;
        }

        for (Short siteCode : siteCodes) {
            Integer totalCount = fastmanTaskRepository
                .getTotalTasksCountBySiteAndOptionalFilters(startDate, endDate, siteCode, statusCodes, actionCodes, incidentCodes, dates);

            List<Short> doneStatusCodes = getDoneStatusCodes(statusCodes);
            Integer doneCount = fastmanTaskRepository
                .getDoneTasksCountBySiteAndOptionalFilters(startDate, endDate, siteCode, doneStatusCodes, actionCodes, incidentCodes, dates);
            resultList.add(createListBySiteAnalyticDto(siteCode, totalCount, doneCount));
        }
        return resultList;
    }

    private List<ListByStatusAnalyticDto> getTasksByStatus(Date startDate,
                                                           Date endDate,
                                                           List<Short> siteCodes,
                                                           List<Short> statusCodes,
                                                           List<Short> actionCodes,
                                                           List<Long> incidentCodes,
                                                           List<Date> dates) {
        List<ListByStatusAnalyticDto> resultList = new ArrayList<>();
        if (statusCodes.contains((short) -1)) {
            statusCodes = fastmanTaskRepository
                .findStatuses(startDate, endDate, siteCodes, actionCodes, incidentCodes, dates);

            List<Object[]> listGroupedByStatusAndCount = fastmanTaskRepository
                .findGroupedByStatusAndCount(startDate, endDate, siteCodes, statusCodes, actionCodes, incidentCodes, dates);

            listGroupedByStatusAndCount.forEach(e -> {
                Short statusCode = ((Number) e[0]).shortValue();
                Integer totalCount = ((Number) e[1]).intValue();
                Integer doneCount = ((Number) e[2]).intValue();
                resultList.add(createListByStatusAnalyticDto(statusCode, totalCount, doneCount));
            });
            return resultList;
        }

        for (Short statusCode : statusCodes) {
            Integer totalCount = fastmanTaskRepository
                .getTotalTasksCountByStatusAndOptionalFilters(startDate, endDate, siteCodes, statusCode, actionCodes, incidentCodes, dates);

            if (statusCode == 1 || statusCode == 2) {
                resultList.add(createListByStatusAnalyticDto(statusCode, totalCount, 0));
                continue;
            }

            List<Short> doneStatusCodes = new ArrayList<>();
            if (statusCodes.contains(defaultValueShort)) {
                doneStatusCodes.add((short) 3);
                doneStatusCodes.add((short) 4);
            }

            if (statusCode == 3 || statusCode == 4) {
                doneStatusCodes.add(statusCode);
            }

            Integer doneCount = fastmanTaskRepository
                .getDoneTasksCountByStatusAndOptionalFilters(startDate, endDate, siteCodes, doneStatusCodes, actionCodes, incidentCodes, dates);
            resultList.add(createListByStatusAnalyticDto(statusCode, totalCount, doneCount));
        }
        return resultList;
    }

    private List<ListByActionAnalyticDto> getTasksByAction(Date startDate,
                                                           Date endDate,
                                                           List<Short> siteCodes,
                                                           List<Short> statusCodes,
                                                           List<Short> actionCodes,
                                                           List<Long> incidentCodes,
                                                           List<Date> dates) {
        List<ListByActionAnalyticDto> resultList = new ArrayList<>();
        if (actionCodes.contains((short) -1)) {
            actionCodes = fastmanTaskRepository
                .findActions(startDate, endDate, siteCodes, statusCodes, incidentCodes, dates);

            List<Object[]> listGroupedByActionAndCount = fastmanTaskRepository
                .findGroupedByActionAndCount(startDate, endDate, siteCodes, statusCodes, actionCodes, incidentCodes, dates);

            listGroupedByActionAndCount.forEach(e -> {
                Short actionCode = ((Number) e[0]).shortValue();
                Integer totalCount = ((Number) e[1]).intValue();
                Integer doneCount = ((Number) e[2]).intValue();
                resultList.add(createListByActionAnalyticDto(actionCode, totalCount, doneCount));
            });
            return resultList;
        }

        for (Short actionCode : actionCodes) {
            Integer totalCount = fastmanTaskRepository
                .getTotalTasksCountByActionAndOptionalFilters(startDate, endDate, siteCodes, statusCodes, actionCode, incidentCodes, dates);

            List<Short> doneStatusCodes = getDoneStatusCodes(statusCodes);
            Integer doneCount = fastmanTaskRepository
                .getDoneTasksCountByActionAndOptionalFilters(startDate, endDate, siteCodes, doneStatusCodes, actionCode, incidentCodes, dates);
            resultList.add(createListByActionAnalyticDto(actionCode, totalCount, doneCount));
        }
        return resultList;
    }

    private List<ListByIncidentAnalyticDto> getTasksByIncident(Date startDate,
                                                               Date endDate,
                                                               List<Short> siteCodes,
                                                               List<Short> statusCodes,
                                                               List<Short> actionCodes,
                                                               List<Long> incidentCodes,
                                                               List<Date> dates) {
        List<ListByIncidentAnalyticDto> resultList = new ArrayList<>();
        if (incidentCodes.contains((long) -1)) {
            incidentCodes = fastmanTaskRepository
                .findIncidents(startDate, endDate, siteCodes, statusCodes, actionCodes, dates);

            List<Object[]> listGroupedByIncidentAndCount = fastmanTaskRepository
                .findGroupedByIncidentAndCount(startDate, endDate, siteCodes, statusCodes, actionCodes, incidentCodes, dates);

            listGroupedByIncidentAndCount.forEach(e -> {
                Long incidentCode = ((Number) e[0]).longValue();
                Integer totalCount = ((Number) e[1]).intValue();
                Integer doneCount = ((Number) e[2]).intValue();
                resultList.add(createListByIncidentAnalyticDto(incidentCode, totalCount, doneCount));
            });
            return resultList;
        }

        for (Long incidentCode : incidentCodes) {
            Integer totalCount = fastmanTaskRepository
                .getTotalTasksCountByIncidentAndOptionalFilters(startDate, endDate, siteCodes, statusCodes, actionCodes, incidentCode, dates);

            List<Short> doneStatusCodes = getDoneStatusCodes(statusCodes);
            Integer doneCount = fastmanTaskRepository
                .getDoneTasksCountByIncidentAndOptionalFilters(startDate, endDate, siteCodes, doneStatusCodes, actionCodes, incidentCode, dates);
            resultList.add(createListByIncidentAnalyticDto(incidentCode, totalCount, doneCount));
        }
        return resultList;
    }

    private List<ListByDateAnalyticDto> getTasksByDate(Date startDate,
                                                       Date endDate,
                                                       List<Short> siteCodes,
                                                       List<Short> statusCodes,
                                                       List<Short> actionCodes,
                                                       List<Long> incidentCodes,
                                                       List<Date> dates) {
        List<ListByDateAnalyticDto> resultList = new ArrayList<>();
        if (dates.contains(dateDefaultValue)) {
            dates = fastmanTaskRepository
                .findDates(startDate, endDate, siteCodes, statusCodes, actionCodes, incidentCodes);

            List<Object[]> listGroupedByDateAndCount = fastmanTaskRepository
                .findGroupedByDateAndCount(startDate, endDate, siteCodes, statusCodes, actionCodes, incidentCodes, dates);

            listGroupedByDateAndCount.forEach(e -> {
                Date date = ((Date) e[0]);
                Integer totalCount = ((Number) e[1]).intValue();
                Integer doneCount = ((Number) e[2]).intValue();
                resultList.add(createListByDateAnalyticDto(date, totalCount, doneCount));
            });
            return resultList;
        }

        for (Date date : dates) {
            Integer totalCount = fastmanTaskRepository
                .getTotalTasksCountByDateAndOptionalFilters(startDate, endDate, siteCodes, statusCodes, actionCodes, incidentCodes, date);

            List<Short> doneStatusCodes = getDoneStatusCodes(statusCodes);
            Integer doneCount = fastmanTaskRepository
                .getDoneTasksCountByDateAndOptionalFilters(startDate, endDate, siteCodes, doneStatusCodes, actionCodes, incidentCodes, date);
            resultList.add(createListByDateAnalyticDto(date, totalCount, doneCount));
        }
        return resultList;

    }

    private List<Short> getDoneStatusCodes(List<Short> statusCodes) {
        return statusCodes.contains(defaultValueShort)
            ? List.of((short) 3, (short) 4)
            : statusCodes.stream().filter(status -> status == 3 || status == 4).collect(Collectors.toList());
    }

    private ListBySiteAnalyticDto createListBySiteAnalyticDto(Short siteCode, Integer totalCount, Integer doneCount) {
        return ListBySiteAnalyticDto.builder()
            .siteCode(siteCode)
            .itemsCountTotal(totalCount)
            .itemsCountDone(doneCount)
            .build();
    }

    private ListByStatusAnalyticDto createListByStatusAnalyticDto(Short statusCode, Integer totalCount, Integer doneCount) {
        return ListByStatusAnalyticDto.builder()
            .statusCode(statusCode)
            .itemsCountTotal(totalCount)
            .itemsCountDone(doneCount)
            .build();
    }

    private ListByActionAnalyticDto createListByActionAnalyticDto(Short actionCode, Integer totalCount, Integer doneCount) {
        return ListByActionAnalyticDto.builder()
            .actionCode(actionCode)
            .itemsCountTotal(totalCount)
            .itemsCountDone(doneCount)
            .build();
    }

    private ListByIncidentAnalyticDto createListByIncidentAnalyticDto(Long incidentCode, Integer totalCount, Integer doneCount) {
        return ListByIncidentAnalyticDto.builder()
            .incidentCode(incidentCode)
            .itemsCountTotal(totalCount)
            .itemsCountDone(doneCount)
            .build();
    }

    private ListByDateAnalyticDto createListByDateAnalyticDto(Date date, Integer totalCount, Integer doneCount) {
        return ListByDateAnalyticDto.builder()
            .date(date)
            .itemsCountTotal(totalCount)
            .itemsCountDone(doneCount)
            .build();
    }
}
