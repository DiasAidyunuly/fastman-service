package kz.magnum.magnumback.fastmanservice.controller.checklist;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.magnum.magnumback.fastmanservice.mapper.checklist.ChecklistPhotoCompletionMapper;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.UpdateChecklistPhotoCompletionDto;
import kz.magnum.magnumback.fastmanservice.service.checklist.ChecklistPhotoCompletionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/fastman/checklist-photo-completion", name = "Методы для передачи фото, в рамках чеклистов по Быстроделу")
@Tag(name = "Fastman Controllers", description = "API для всех операций по Быстроделу")
public class ChecklistPhotoCompletionController {
    private final ChecklistPhotoCompletionService checklistPhotoCompletionService;
    private final ChecklistPhotoCompletionMapper checklistPhotoCompletionMapper;

    @CrossOrigin
    @PutMapping("/{id}")
    @Operation(summary = "Метод изменяющий фото в рамках чеклистов по Быстроделу")
    public ResponseEntity<List<UpdateChecklistPhotoCompletionDto>> updateChecklistPhotoCompletion(@PathVariable("id") Long id,
                                                                                                  @RequestBody List<UpdateChecklistPhotoCompletionDto> param) {

        return ResponseEntity.ok(checklistPhotoCompletionService.update(id, param.stream()
                .map(checklistPhotoCompletionMapper::toDomain)
                .collect(Collectors.toList())).stream()
            .map(checklistPhotoCompletionMapper::toDto)
            .collect(Collectors.toList()));

    }
}