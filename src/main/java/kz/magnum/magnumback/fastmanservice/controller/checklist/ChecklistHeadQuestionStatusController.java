package kz.magnum.magnumback.fastmanservice.controller.checklist;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.magnum.magnumback.fastmanservice.mapper.checklist.ChecklistHeadQuestionStatusMapper;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.ChecklistHeadQuestionStatusDto;
import kz.magnum.magnumback.fastmanservice.service.checklist.ChecklistHeadQuestionStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/fastman/checklist/checklist-head-question-status", name = "Методы для чеклистов (Справочника статусов по шапкам и вопросам)")
@Tag(name = "Fastman Controllers", description = "API для всех операций по Быстроделу")
public class ChecklistHeadQuestionStatusController {
    private final ChecklistHeadQuestionStatusService checklistHeadQuestionStatusService;
    private final ChecklistHeadQuestionStatusMapper checklistHeadQuestionStatusMapper;

    @CrossOrigin
    @GetMapping
    @Operation(summary = "Метод для передачи справочника статусов по шапкам и вопросам Чек-листов")
    public ResponseEntity<ChecklistHeadQuestionStatusDto> getChecklistHeadQuestionStatuses() {
        return ResponseEntity.ok(checklistHeadQuestionStatusMapper.toDto(checklistHeadQuestionStatusService.findAll()));
    }
}