package kz.magnum.magnumback.fastmanservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.magnum.magnumback.fastmanservice.mapper.structure.DirectionMapper;
import kz.magnum.magnumback.fastmanservice.mapper.structure.StructureLevelMapper;
import kz.magnum.magnumback.fastmanservice.model.pdt.structure.DirectionDto;
import kz.magnum.magnumback.fastmanservice.model.pdt.structure.UpdateStructureLevelsParamDto;
import kz.magnum.magnumback.fastmanservice.service.structure.StructureLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/fastman/structure-level", name = "Методы для СУБП (Управления коэффициентами расчета критичных сроков годности)")
@Tag(name = "Fastman Controllers", description = "API для всех операций по Быстроделу")
public class StructureLevelController {
    private final StructureLevelService structureLevelService;
    private final DirectionMapper directionMapper;
    private final StructureLevelMapper structureLevelMapper;

    @CrossOrigin
    @GetMapping
    @Operation(summary = "Метод возвращающий список данных в виде структуры входящих массивов из уровня структур от 2 до 5")
    public ResponseEntity<List<DirectionDto>> getStructureLevels() {
        return ResponseEntity.ok(structureLevelService.findDirections()
            .stream()
            .map(directionMapper::toDto)
            .collect(Collectors.toList()));
    }

    @CrossOrigin
    @PutMapping
    @Operation(summary = "Метод изменяющий значения в структурных уровнях и всех соединенных уровнях выше")
    public ResponseEntity<Void> updateStructureLevels(@RequestBody UpdateStructureLevelsParamDto param) {
        structureLevelService.updateStructureLevels(structureLevelMapper.toModel(param));
        return ResponseEntity.ok().build();
    }
}