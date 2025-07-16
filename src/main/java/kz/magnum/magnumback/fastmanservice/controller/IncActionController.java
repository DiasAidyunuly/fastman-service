package kz.magnum.magnumback.fastmanservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.magnum.magnumback.fastmanservice.mapper.IncActionLocalMapper;
import kz.magnum.magnumback.fastmanservice.mapper.IncActionMapper;
import kz.magnum.magnumback.fastmanservice.mapper.IncActionWithIncidentIdMapper;
import kz.magnum.magnumback.fastmanservice.model.pdt.IncActionDto;
import kz.magnum.magnumback.fastmanservice.model.pdt.IncActionLocalDto;
import kz.magnum.magnumback.fastmanservice.model.pdt.IncActionWithIncidentIdDto;
import kz.magnum.magnumback.fastmanservice.service.IncActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/fastman", name = "Методы для СУБП (Инцидент с действием)")
@Tag(name = "Fastman Controllers", description = "API для всех операций по Быстроделу")
public class IncActionController {
    private final IncActionService incActionService;
    private final IncActionMapper incActionMapper;
    private final IncActionWithIncidentIdMapper incActionWithIncidentIdMapper;
    private final IncActionLocalMapper incActionLocalMapper;

    @CrossOrigin
    @GetMapping("/inc-action/{id}")
    @Operation(summary = "Метод возвращающий список инцидентов с действием")
    public ResponseEntity<List<IncActionWithIncidentIdDto>> getIncActionsDirect(@PathVariable("id") Long incidentId) {
        return ResponseEntity.ok(incActionService.findByIncidentId(incidentId)
            .stream()
            .map(incActionWithIncidentIdMapper::toDto)
            .collect(Collectors.toList()));
    }

    @CrossOrigin
    @GetMapping("/inc-action-local")
    @Operation(summary = "Метод возвращающий список инцидентов с действием для ТСД")
    public ResponseEntity<List<IncActionLocalDto>> getIncActionsLocal() {
        return ResponseEntity.ok(incActionService.findIncActionsLocal()
            .stream()
            .map(incActionLocalMapper::toDto)
            .collect(Collectors.toList()));
    }

    @CrossOrigin
    @PostMapping("/inc-action")
    @Operation(summary = "Метод добавляющий новый инцидент с действием")
    public ResponseEntity<IncActionDto> createIncActionDirect(@RequestBody IncActionDto incActionDto) {
        return ResponseEntity.ok(incActionMapper.toDto(incActionService.save(incActionMapper.toEntity(incActionDto))));
    }

    @CrossOrigin
    @DeleteMapping("/inc-action/{id}")
    @Operation(summary = "Метод удаляющий инцидент с действием")
    public ResponseEntity<Void> deleteIncActionDirect(@PathVariable("id") Long id) {
        incActionService.delete(id);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PutMapping("/inc-action/{id}")
    @Operation(summary = "Метод изменяющий инцидент с действием")
    public ResponseEntity<IncActionDto> updateIncActionDirect(@PathVariable("id") Long id,
                                                              @RequestBody IncActionDto incActionDto) {
        return ResponseEntity.ok(incActionMapper.toDto(incActionService.update(id, incActionMapper.toEntity(incActionDto))));
    }
}
