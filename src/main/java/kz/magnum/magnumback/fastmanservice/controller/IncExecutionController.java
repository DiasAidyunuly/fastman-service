package kz.magnum.magnumback.fastmanservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.magnum.magnumback.fastmanservice.mapper.IncExecutionLocalMapper;
import kz.magnum.magnumback.fastmanservice.mapper.IncExecutionMapper;
import kz.magnum.magnumback.fastmanservice.mapper.IncExecutionWithIncidentIdMapper;
import kz.magnum.magnumback.fastmanservice.model.pdt.IncExecutionDto;
import kz.magnum.magnumback.fastmanservice.model.pdt.IncExecutionLocalDto;
import kz.magnum.magnumback.fastmanservice.model.pdt.IncExecutionWithIncidentIdDto;
import kz.magnum.magnumback.fastmanservice.service.IncExecutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/fastman", name = "Методы для СУБП (Инцидент с выполнением)")
@Tag(name = "Fastman Controllers", description = "API для всех операций по Быстроделу")
public class IncExecutionController {
    private final IncExecutionService incExecutionService;
    private final IncExecutionMapper incExecutionMapper;
    private final IncExecutionWithIncidentIdMapper incExecutionWithIncidentIdMapper;
    private final IncExecutionLocalMapper incExecutionLocalMapper;

    @CrossOrigin
    @GetMapping("/inc-execution/{id}")
    @Operation(summary = "Метод возвращающий список инцидентов с отработкой")
    public ResponseEntity<List<IncExecutionWithIncidentIdDto>> getIncExecutionsDirect(@PathVariable("id") Long incidentId) {
        return ResponseEntity.ok(incExecutionService.findByIncidentId(incidentId)
            .stream()
            .map(incExecutionWithIncidentIdMapper::toDto)
            .collect(Collectors.toList()));
    }

    @CrossOrigin
    @GetMapping("/inc-execution-local")
    @Operation(summary = "Метод возвращающий список инцидентов с отработкой для ТСД")
    public ResponseEntity<List<IncExecutionLocalDto>> getIncExecutionsLocal() {
        return ResponseEntity.ok(incExecutionService.findIncExecutionsLocal()
            .stream()
            .map(incExecutionLocalMapper::toDto)
            .collect(Collectors.toList()));
    }

    @CrossOrigin
    @PostMapping("/inc-execution")
    @Operation(summary = "Метод добавляющий новый инцидент с отработкой")
    public ResponseEntity<IncExecutionDto> createIncExecutionDirect(@RequestBody IncExecutionDto incExecutionDto) {
        return ResponseEntity.ok(incExecutionMapper.toDto(incExecutionService.save(incExecutionMapper.toEntity(incExecutionDto))));
    }

    @CrossOrigin
    @DeleteMapping("/inc-execution/{id}")
    @Operation(summary = "Метод удаляющий инцидент с отработкой")
    public ResponseEntity<Void> deleteIncExecutionDirect(@PathVariable("id") Long id) {
        incExecutionService.delete(id);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PutMapping("/inc-execution/{id}")
    @Operation(summary = "Метод изменяющий инцидент с отработкой")
    public ResponseEntity<IncExecutionDto> updateIncExecutionDirect(@PathVariable("id") Long id,
                                                                    @RequestBody IncExecutionDto incExecutionDto) {
        return ResponseEntity.ok(incExecutionMapper.toDto(incExecutionService.update(id, incExecutionMapper.toEntity(incExecutionDto))));
    }
}
