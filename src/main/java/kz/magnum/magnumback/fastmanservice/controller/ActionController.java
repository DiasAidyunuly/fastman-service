package kz.magnum.magnumback.fastmanservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.magnum.magnumback.fastmanservice.mapper.ActionMapper;
import kz.magnum.magnumback.fastmanservice.model.pdt.ActionDto;
import kz.magnum.magnumback.fastmanservice.service.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/fastman/action", name = "Методы для СУБП (Действие)")
@Tag(name = "Fastman Controllers", description = "API для всех операций по Быстроделу")
public class ActionController {
    private final ActionService actionService;
    private final ActionMapper actionMapper;

    @CrossOrigin
    @GetMapping
    @Operation(summary = "Метод возвращающий список действий")
    public ResponseEntity<List<ActionDto>> getActionsDirect() {
        return ResponseEntity.ok(actionService.findAll()
            .stream()
            .map(actionMapper::toDto)
            .collect(Collectors.toList()));
    }

    @CrossOrigin
    @PostMapping
    @Operation(summary = "Метод добавляющий новое действие")
    public ResponseEntity<ActionDto> createActionDirect(@RequestBody ActionDto actionDto) {
        return ResponseEntity.ok(actionMapper.toDto(actionService.save(actionMapper.toEntity(actionDto))));
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    @Operation(summary = "Метод удаляющий действие")
    public ResponseEntity<Void> deleteActionDirect(@PathVariable("id") Short id) {
        actionService.delete(id);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PutMapping("/{id}")
    @Operation(summary = "Метод изменяющий действие")
    public ResponseEntity<ActionDto> updateActionDirect(@PathVariable("id") Short id,
                                                        @RequestBody ActionDto actionDto) {
        return ResponseEntity.ok(actionMapper.toDto(actionService.update(id, actionMapper.toEntity(actionDto))));
    }
}