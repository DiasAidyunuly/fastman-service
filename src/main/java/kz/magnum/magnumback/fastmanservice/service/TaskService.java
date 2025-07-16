package kz.magnum.magnumback.fastmanservice.service;

import kz.magnum.magnumback.fastmanservice.entity.fastman.kafka.FastmanTask;
import kz.magnum.magnumback.fastmanservice.model.pdt.*;

import java.util.List;

public interface TaskService {
    IncidentStatusesDto getIncidentsWithStatuses(Short siteCode);

    ActionStatusesDto getActionsWithStatuses(Short siteCode);

    List<FastmanItemModel> getItems(Short siteCode, Long incidentCode, Short actionCode);

    List<FastmanTask> saveTasks(CreateTaskParamModel taskParam);

    CompletedItemModel saveCompletedItem(Long id, CompletedItemParam completedItemDto);

    String uploadFile(String base64, String fileName, String extension);

    void saveTaskFromKafka(String param);

    FastmanTask findById(Long id);

    GetItemsStockModel findItemsStock(GetItemsStockParamModel param);
}