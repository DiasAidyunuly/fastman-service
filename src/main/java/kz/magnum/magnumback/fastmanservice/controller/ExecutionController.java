package kz.magnum.magnumback.fastmanservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.magnum.magnumback.fastmanservice.mapper.ExecutionMapper;
import kz.magnum.magnumback.fastmanservice.mapper.TypeOfExecutionMapper;
import kz.magnum.magnumback.fastmanservice.model.pdt.ExecutionDto;
import kz.magnum.magnumback.fastmanservice.model.pdt.TypeOfExecutionDto;
import kz.magnum.magnumback.fastmanservice.service.ExecutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/fastman/execution", name = "Методы для СУБП (Исполнение)")
@Tag(name = "Fastman Controllers", description = "API для всех операций по Быстроделу")
public class ExecutionController {
    private final ExecutionService executionService;
    private final ExecutionMapper executionMapper;
    private final TypeOfExecutionMapper typeOfExecutionMapper;

    @CrossOrigin
    @GetMapping
    @Operation(summary = "Метод возвращающий список исполнений")
    public ResponseEntity<List<ExecutionDto>> getExecutionsDirect() {
        return ResponseEntity.ok(executionService.findAll()
            .stream()
            .map(executionMapper::toDto)
            .collect(Collectors.toList()));
    }

    @CrossOrigin
    @PostMapping
    @Operation(summary = "Метод добавляющий новое исполнение")
    public ResponseEntity<ExecutionDto> createExecutionDirect(@RequestBody ExecutionDto executionDto) {
        return ResponseEntity.ok(executionMapper.toDto(executionService.save(executionMapper.toEntity(executionDto))));
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    @Operation(summary = "Метод удаляющий исполнение")
    public ResponseEntity<Void> deleteExecutionDirect(@PathVariable("id") Long id) {
        executionService.delete(id);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PutMapping("/{id}")
    @Operation(summary = "Метод изменяющий исполнение")
    public ResponseEntity<ExecutionDto> updateExecutionDirect(@PathVariable("id") Long id,
                                                              @RequestBody ExecutionDto executionDto) {
        return ResponseEntity.ok(executionMapper.toDto(executionService.update(id, executionMapper.toEntity(executionDto))));
    }

    @CrossOrigin
    @GetMapping("/types")
    @Operation(summary = "Метод возвращающий список типов исполнений")
    public ResponseEntity<List<TypeOfExecutionDto>> getTypesOfExecutionDirect() {
        return ResponseEntity.ok(executionService.findAllTypes()
            .stream()
            .map(typeOfExecutionMapper::toDto)
            .collect(Collectors.toList()));
    }
}
