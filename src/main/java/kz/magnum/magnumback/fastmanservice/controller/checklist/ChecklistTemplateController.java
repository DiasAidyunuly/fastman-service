package kz.magnum.magnumback.fastmanservice.controller.checklist;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.magnum.magnumback.fastmanservice.mapper.checklist.ChecklistTemplateMapper;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.ChecklistTemplateDto;
import kz.magnum.magnumback.fastmanservice.service.checklist.ChecklistTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/fastman/checklist/checklist-template", name = "Методы для чеклистов (Шаблон)")
@Tag(name = "Fastman Controllers", description = "API для всех операций по Быстроделу")
public class ChecklistTemplateController {
    private final ChecklistTemplateService checklistTemplateService;
    private final ChecklistTemplateMapper checklistTemplateMapper;

    @CrossOrigin
    @GetMapping
    @Operation(summary = "Метод возвращающий список чеклисты шаблонов")
    public ResponseEntity<List<ChecklistTemplateDto>> getChecklistTemplates() {
        return ResponseEntity.ok(checklistTemplateService.findAll()
            .stream()
            .map(checklistTemplateMapper::toDto)
            .collect(Collectors.toList()));
    }

    @CrossOrigin
    @PostMapping
    @Operation(summary = "Метод добавляющий новый чеклист шаблон")
    public ResponseEntity<ChecklistTemplateDto> createChecklistTemplate(@RequestBody ChecklistTemplateDto checklistTemplateDto) {
        return ResponseEntity.ok(checklistTemplateMapper.toDto(checklistTemplateService.save(checklistTemplateMapper.toEntity(checklistTemplateDto))));
    }

    @CrossOrigin
    @PutMapping("/{id}")
    @Operation(summary = "Метод изменяющий существующий чеклист шаблон")
    public ResponseEntity<ChecklistTemplateDto> updateChecklistTemplate(@PathVariable("id") Long id,
                                                                        @RequestBody ChecklistTemplateDto checklistTemplateDto) {
        return ResponseEntity.ok(checklistTemplateMapper.toDto(checklistTemplateService.update(id, checklistTemplateMapper.toEntity(checklistTemplateDto))));
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    @Operation(summary = "Метод удаляющий чеклист шаблон")
    public ResponseEntity<Void> deleteChecklistTemplate(@PathVariable("id") Long id) {
        checklistTemplateService.delete(id);
        return ResponseEntity.ok().build();
    }
}
