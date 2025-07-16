package kz.magnum.magnumback.fastmanservice.controller.checklist;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.magnum.magnumback.fastmanservice.mapper.checklist.ChecklistTypeOfAnswerMapper;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.ChecklistTypeOfAnswerDto;
import kz.magnum.magnumback.fastmanservice.service.checklist.ChecklistTypeOfAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/fastman/checklist/checklist-type-of-answer", name = "Методы для чеклистов (Тип ответа)")
@Tag(name = "Fastman Controllers", description = "API для всех операций по Быстроделу")
public class ChecklistTypeOfAnswerController {
    private final ChecklistTypeOfAnswerService checklistTypeOfAnswerService;
    private final ChecklistTypeOfAnswerMapper checklistTypeOfAnswerMapper;

    @CrossOrigin
    @GetMapping
    @Operation(summary = "Метод возвращающий список чеклисты типы ответов")
    public ResponseEntity<List<ChecklistTypeOfAnswerDto>> getChecklistTypesOfAnswers() {
        return ResponseEntity.ok(checklistTypeOfAnswerService.findAll()
            .stream()
            .map(checklistTypeOfAnswerMapper::toDto)
            .collect(Collectors.toList()));
    }

    @CrossOrigin
    @PostMapping
    @Operation(summary = "Метод добавляющий новый чеклист тип ответа")
    public ResponseEntity<ChecklistTypeOfAnswerDto> createChecklistTypeOfAnswer(@RequestBody ChecklistTypeOfAnswerDto checklistTypeOfAnswerDto) {
        return ResponseEntity.ok(checklistTypeOfAnswerMapper.toDto(checklistTypeOfAnswerService.save(checklistTypeOfAnswerMapper.toEntity(checklistTypeOfAnswerDto))));
    }

    @CrossOrigin
    @PutMapping("/{id}")
    @Operation(summary = "Метод изменяющий существующий чеклист тип ответа")
    public ResponseEntity<ChecklistTypeOfAnswerDto> updateChecklistTypeOfAnswer(@PathVariable("id") Long id,
                                                                                @RequestBody ChecklistTypeOfAnswerDto checklistTypeOfAnswerDto) {
        return ResponseEntity.ok(checklistTypeOfAnswerMapper.toDto(checklistTypeOfAnswerService.update(id, checklistTypeOfAnswerMapper.toEntity(checklistTypeOfAnswerDto))));
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    @Operation(summary = "Метод удаляющий чеклист тип ответа")
    public ResponseEntity<Void> deleteChecklistTypeOfAnswer(@PathVariable("id") Long id) {
        checklistTypeOfAnswerService.delete(id);
        return ResponseEntity.ok().build();
    }
}