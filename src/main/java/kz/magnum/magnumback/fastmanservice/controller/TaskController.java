package kz.magnum.magnumback.fastmanservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.magnum.magnumback.fastmanservice.mapper.TaskMapper;
import kz.magnum.magnumback.fastmanservice.model.pdt.*;
import kz.magnum.magnumback.fastmanservice.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/fastman", name = "Методы для работы с задачами")
@Tag(name = "Fastman Controllers", description = "API для всех операций по Быстроделу")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @CrossOrigin
    @GetMapping("/incidents-statuses/by-site")
    @Operation(summary = "Метод возвращающий список инцидентов со статусами")
    public ResponseEntity<IncidentStatusesDto> getIncidentsWithStatuses(@RequestParam Short siteCode) {
        return ResponseEntity.ok(taskService.getIncidentsWithStatuses(siteCode));
    }

    @CrossOrigin
    @GetMapping("/actions-statuses/by-site")
    @Operation(summary = "Метод возвращающий список действий со статусами")
    public ResponseEntity<ActionStatusesDto> getActionsWithStatuses(@RequestParam Short siteCode) {
        return ResponseEntity.ok(taskService.getActionsWithStatuses(siteCode));
    }

    @CrossOrigin
    @GetMapping("/incidents/items")
    @Operation(summary = "Получение списка задач по инциденту")
    public ResponseEntity<List<FastmanItemDto>> getItems(@RequestParam Short siteCode,
                                                         @RequestParam(required = false) Long incidentCode,
                                                         @RequestParam(required = false) Short actionCode) {
        return ResponseEntity.ok(taskService.getItems(siteCode, incidentCode, actionCode)
            .stream()
            .map(taskMapper::toFastmanItemDto)
            .collect(Collectors.toList()));
    }

    @CrossOrigin
    @PostMapping("/tasks/post-new-task")
    @Operation(summary = "Создание ручной задачи с приложения")
    public ResponseEntity<List<FastmanTaskDto>> createTask(@RequestBody CreateTaskParamDto taskParam) {

        return ResponseEntity.ok(taskService.saveTasks(taskMapper.toCreateTaskModel(taskParam))
            .stream()
            .map(taskMapper::toFastmanTaskDto)
            .collect(Collectors.toList()));
    }

    @CrossOrigin
    @PutMapping("/tasks/post-completed-item/{id}")
    @Operation(summary = " отправка в БД о данных обработанной задачи")
    public ResponseEntity<CompletedItemDto> postCompletedItem(@PathVariable("id") Long id,
                                                              @RequestBody CompletedItemParam completedItemParam) {
        return ResponseEntity.ok(taskMapper.toCompletedItemDto(taskService.saveCompletedItem(id, completedItemParam)));
    }

    @CrossOrigin
    @PostMapping("/tasks/item-stock")
    @Operation(summary = "Метод для актуализации остатков по списку всех товаров в JAS-App")
    public ResponseEntity<GetItemsStockDto> getItemsStock(@RequestBody GetItemsStockParamDto getItemsStockParamDto) {
        return ResponseEntity.ok(
            taskMapper.toItemsStockDto(taskService.findItemsStock(
                taskMapper.toItemsStockParamModel(getItemsStockParamDto))));
    }

    @CrossOrigin
    @GetMapping("/tasks/task/{id}")
    @Operation(summary = "Получение задачи по его идентификатору")
    public ResponseEntity<GetFastmanTaskDto> getFastmanTasks(@PathVariable("id") Long fastmanTaskId) {
        return ResponseEntity.ok(taskMapper.toGetFastmanTaskDto(taskService.findById(fastmanTaskId)));
    }
}