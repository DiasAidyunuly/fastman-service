package kz.magnum.magnumback.fastmanservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.magnum.magnumback.fastmanservice.model.pdt.UpdateTaskPhotoCompletionResponse;
import kz.magnum.magnumback.fastmanservice.model.pdt.UpdateTaskPhotoCompletionParam;
import kz.magnum.magnumback.fastmanservice.service.TaskPhotoCompletionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/fastman/task-photo-completion", name = "Методы для передачи фото, в рамках отработки задач по Быстроделу")
@Tag(name = "Fastman Controllers", description = "API для всех операций по Быстроделу")
public class TaskPhotoCompletionController {
    private final TaskPhotoCompletionService taskPhotoCompletionService;

    @CrossOrigin
    @PutMapping("/{id}")
    @Operation(summary = "Метод изменяющий фото в рамках отработки задач по Быстроделу")
    public ResponseEntity<List<UpdateTaskPhotoCompletionResponse>> updateTaskPhotoCompletion(@PathVariable("id") Long id,
                                                                                             @RequestBody List<UpdateTaskPhotoCompletionParam> param) {
        return ResponseEntity.ok(taskPhotoCompletionService.update(id, param));
    }
}