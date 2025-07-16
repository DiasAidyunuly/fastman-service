package kz.magnum.magnumback.fastmanservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.magnum.magnumback.fastmanservice.mapper.TemplateMapper;
import kz.magnum.magnumback.fastmanservice.model.pdt.TemplateDto;
import kz.magnum.magnumback.fastmanservice.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/fastman/template", name = "Методы для СУБП (Шаблон)")
@Tag(name = "Fastman Controllers", description = "API для всех операций по Быстроделу")
public class TemplateController {
    private final TemplateService templateService;
    private final TemplateMapper templateMapper;

    @CrossOrigin
    @GetMapping
    @Operation(summary = "Метод возвращающий список шаблонов")
    public ResponseEntity<List<TemplateDto>> getTemplatesDirect() {
        return ResponseEntity.ok(templateService.findAll()
            .stream()
            .map(templateMapper::toDto)
            .collect(Collectors.toList()));
    }

    @CrossOrigin
    @PostMapping
    @Operation(summary = "Метод добавляющий новый шаблон")
    public ResponseEntity<TemplateDto> createTemplateDirect(@RequestBody TemplateDto templateDto) {
        return ResponseEntity.ok(templateMapper.toDto(templateService.save(templateMapper.toEntity(templateDto))));
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    @Operation(summary = "Метод удаляющий шаблон")
    public ResponseEntity<Void> deleteTemplateDirect(@PathVariable("id") Short id) {
        templateService.delete(id);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PutMapping("/{id}")
    @Operation(summary = "Метод изменяющий шаблон")
    public ResponseEntity<TemplateDto> updateTemplateDirect(@PathVariable("id") Short id,
                                                            @RequestBody TemplateDto templateDto) {
        return ResponseEntity.ok(templateMapper.toDto(templateService.update(id, templateMapper.toEntity(templateDto))));
    }
}

