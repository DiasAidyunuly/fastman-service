package kz.magnum.magnumback.fastmanservice.controller.checklist;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.magnum.magnumback.fastmanservice.mapper.checklist.ChecklistHeadTempMapper;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.ChecklistHeadTempDto;
import kz.magnum.magnumback.fastmanservice.service.checklist.ChecklistHeadTempService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/fastman/checklist/checklist-head-temp", name = "Методы для чеклистов (Шапки шаблонов)")
@Tag(name = "Fastman Controllers", description = "API для всех операций по Быстроделу")
public class ChecklistHeadTempController {
    private final ChecklistHeadTempService checklistHeadTempService;
    private final ChecklistHeadTempMapper checklistHeadTempMapper;

    @CrossOrigin
    @GetMapping
    @Operation(summary = "Метод для отображения шаблонов шапок по чеклистам")
    public ResponseEntity<List<ChecklistHeadTempDto>> getChecklistHeadTemps() {
        return ResponseEntity.ok(checklistHeadTempService.findAll()
            .stream()
            .map(checklistHeadTempMapper::toDto)
            .collect(Collectors.toList()));
    }

    @CrossOrigin
    @PostMapping
    @Operation(summary = "Метод для добавления нового шаблона шапок по чеклистам")
    public ResponseEntity<ChecklistHeadTempDto> createChecklistHeadTemp(@RequestBody ChecklistHeadTempDto checklistHeadTempDto) {
        return ResponseEntity.ok(checklistHeadTempMapper.toDto(checklistHeadTempService.save(checklistHeadTempMapper.toEntity(checklistHeadTempDto))));
    }

    @CrossOrigin
    @PutMapping("/{id}")
    @Operation(summary = "Метод изменяющий существующий шаблона шапок по чеклистам")
    public ResponseEntity<ChecklistHeadTempDto> updateChecklistHeadTemp(@PathVariable("id") Long id,
                                                                        @RequestBody ChecklistHeadTempDto checklistHeadTempDto) {
        return ResponseEntity.ok(checklistHeadTempMapper.toDto(checklistHeadTempService.update(id, checklistHeadTempMapper.toEntity(checklistHeadTempDto))));
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    @Operation(summary = "Метод удаляющий шаблон шапок по чеклистам")
    public ResponseEntity<Void> deleteChecklistHeadTemp(@PathVariable("id") Long id) {
        checklistHeadTempService.delete(id);
        return ResponseEntity.ok().build();
    }
}