package kz.magnum.magnumback.fastmanservice.controller.checklist;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.magnum.magnumback.fastmanservice.mapper.checklist.ChecklistChapterMapper;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.ChecklistChapterDto;
import kz.magnum.magnumback.fastmanservice.service.checklist.ChecklistChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/fastman/checklist/checklist-chapter", name = "Методы для чеклистов (Разделы шаблона ЧЛ, для применения в вопросах)")
@Tag(name = "Fastman Controllers", description = "API для всех операций по Быстроделу")
public class ChecklistChapterController {
    private final ChecklistChapterService checklistChapterService;
    private final ChecklistChapterMapper checklistChapterMapper;

    @CrossOrigin
    @GetMapping("/{id}")
    @Operation(summary = "Метод для отображения разделов шаблона по чеклистам")
    public ResponseEntity<List<ChecklistChapterDto>> getChecklistChapters(@PathVariable("id") Long id) {
        return ResponseEntity.ok(checklistChapterService.findAll(id)
            .stream()
            .map(checklistChapterMapper::toDto)
            .collect(Collectors.toList()));
    }

    @CrossOrigin
    @PostMapping
    @Operation(summary = "Метод для добавления нового раздела шаблона по чеклистам")
    public ResponseEntity<ChecklistChapterDto> createChecklistChapter(@RequestBody ChecklistChapterDto checklistChapterDto) {
        return ResponseEntity.ok(checklistChapterMapper.toDto(checklistChapterService.save(checklistChapterMapper.toEntity(checklistChapterDto))));
    }

    @CrossOrigin
    @PutMapping("/{id}")
    @Operation(summary = "Метод изменяющий существующий раздел шаблона по чеклистам")
    public ResponseEntity<ChecklistChapterDto> updateChecklistChapter(@PathVariable("id") Long id,
                                                                      @RequestBody String chapterName) {
        return ResponseEntity.ok(checklistChapterMapper.toDto(checklistChapterService.update(id, chapterName)));
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    @Operation(summary = "Метод удаляющий раздел шаблона по чеклистам")
    public ResponseEntity<Void> deleteChecklistChapter(@PathVariable("id") Long id) {
        checklistChapterService.delete(id);
        return ResponseEntity.ok().build();
    }
}