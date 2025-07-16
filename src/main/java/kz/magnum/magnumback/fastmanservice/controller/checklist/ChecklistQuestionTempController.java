package kz.magnum.magnumback.fastmanservice.controller.checklist;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.magnum.magnumback.fastmanservice.mapper.checklist.ChecklistQuestionTempMapper;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.ChecklistQuestionTempDto;
import kz.magnum.magnumback.fastmanservice.service.checklist.ChecklistQuestionTempService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/fastman/checklist/checklist-question-temp", name = "Методы для чеклистов (Разделы шаблона ЧЛ, для управления вопросами шапок)")
@Tag(name = "Fastman Controllers", description = "API для всех операций по Быстроделу")
public class ChecklistQuestionTempController {
    private final ChecklistQuestionTempService checklistQuestionTempService;
    private final ChecklistQuestionTempMapper checklistQuestionTempMapper;

    @CrossOrigin
    @GetMapping("/{id}")
    @Operation(summary = "Метод для отображения разделов шаблона по управлению вопросами шапок")
    public ResponseEntity<List<ChecklistQuestionTempDto>> getChecklistQuestionTemps(@PathVariable("id") Long id) {
        return ResponseEntity.ok(checklistQuestionTempService.findAll(id)
            .stream()
            .map(checklistQuestionTempMapper::toDto)
            .collect(Collectors.toList()));
    }

    @CrossOrigin
    @PostMapping
    @Operation(summary = "Метод для добавления нового раздела шаблона по управлению вопросами шапок")
    public ResponseEntity<ChecklistQuestionTempDto> createChecklistQuestionTemp(@RequestBody ChecklistQuestionTempDto checklistQuestionTempDto) {
        return ResponseEntity.ok(checklistQuestionTempMapper.mapToDto(checklistQuestionTempService.save(checklistQuestionTempMapper.mapToDomain(checklistQuestionTempDto))));
    }

    @CrossOrigin
    @PutMapping("/{id}")
    @Operation(summary = "Метод изменяющий существующий раздел шаблона по управлению вопросами шапок")
    public ResponseEntity<ChecklistQuestionTempDto> updateChecklistQuestionTemp(@PathVariable("id") Long id,
                                                                                @RequestBody ChecklistQuestionTempDto checklistQuestionTempDto) {
        return ResponseEntity.ok(checklistQuestionTempMapper.mapToDto(checklistQuestionTempService.update(id, checklistQuestionTempMapper.mapToDomain(checklistQuestionTempDto))));
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    @Operation(summary = "Метод удаляющий раздел шаблона по управлению вопросами шапок")
    public ResponseEntity<Void> deleteChecklistQuestionTemp(@PathVariable("id") Long id) {
        checklistQuestionTempService.delete(id);
        return ResponseEntity.ok().build();
    }
}