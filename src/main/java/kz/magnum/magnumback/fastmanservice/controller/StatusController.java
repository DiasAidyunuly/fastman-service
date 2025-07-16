package kz.magnum.magnumback.fastmanservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.magnum.magnumback.fastmanservice.mapper.StatusMapper;
import kz.magnum.magnumback.fastmanservice.model.pdt.StatusDto;
import kz.magnum.magnumback.fastmanservice.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/fastman/status", name = "Методы для СУБП (Статус)")
@Tag(name = "Fastman Controllers", description = "API для всех операций по Быстроделу")
public class StatusController {
    private final StatusService statusService;
    private final StatusMapper statusMapper;

    @CrossOrigin
    @GetMapping
    @Operation(summary = "Метод возвращающий список статусов")
    public ResponseEntity<List<StatusDto>> getStatuses() {
        return ResponseEntity.ok(statusService.findAll()
            .stream()
            .map(statusMapper::toDto)
            .collect(Collectors.toList()));
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    @Operation(summary = "Метод удаляющий статус")
    public ResponseEntity<Void> deleteActionDirect(@PathVariable("id") Short id) {
        statusService.delete(id);
        return ResponseEntity.ok().build();
    }
}
