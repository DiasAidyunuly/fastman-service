package kz.magnum.magnumback.fastmanservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.magnum.magnumback.fastmanservice.mapper.IncidentMapper;
import kz.magnum.magnumback.fastmanservice.model.pdt.IncidentDto;
import kz.magnum.magnumback.fastmanservice.service.IncidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/fastman/incident", name = "Методы для СУБП (Инцидент)")
@Tag(name = "Fastman Controllers", description = "API для всех операций по Быстроделу")
public class IncidentController {
    private final IncidentService incidentService;
    private final IncidentMapper incidentMapper;

    @CrossOrigin
    @GetMapping
    @Operation(summary = "Метод возвращающий список инцидентов")
    public ResponseEntity<List<IncidentDto>> getIncidentsDirect() {
        return ResponseEntity.ok(incidentService.findAll()
            .stream()
            .map(incidentMapper::toDto)
            .collect(Collectors.toList()));
    }

    @CrossOrigin
    @PostMapping
    @Operation(summary = "Метод добавляющий новый инцидент")
    public ResponseEntity<IncidentDto> createIncidentDirect(@RequestBody IncidentDto incidentDto) {
        return ResponseEntity.ok(incidentMapper.toDto(incidentService.save(incidentMapper.toEntity(incidentDto))));
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    @Operation(summary = "Метод удаляющий инцидент")
    public ResponseEntity<Void> deleteIncidentDirect(@PathVariable("id") Long id) {
        incidentService.delete(id);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PutMapping("/{id}")
    @Operation(summary = "Метод изменяющий инцидент")
    public ResponseEntity<IncidentDto> updateIncidentDirect(@PathVariable("id") Long id,
                                                            @RequestBody IncidentDto incidentDto) {
        return ResponseEntity.ok(incidentMapper.toDto(incidentService.update(id, incidentMapper.toEntity(incidentDto))));
    }
}
