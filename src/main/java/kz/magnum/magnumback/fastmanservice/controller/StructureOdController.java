package kz.magnum.magnumback.fastmanservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.magnum.magnumback.fastmanservice.entity.general.Filial;
import kz.magnum.magnumback.fastmanservice.mapper.*;
import kz.magnum.magnumback.fastmanservice.model.pdt.*;
import kz.magnum.magnumback.fastmanservice.model.pdt.odstructure.StructureOdDto;
import kz.magnum.magnumback.fastmanservice.model.pdt.odstructure.StructureOdFilterParam;
import kz.magnum.magnumback.fastmanservice.service.StructureOdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(value = "/api/fastman", name = "Методы для работы со структурой ОД")
@Tag(name = "Fastman Controllers", description = "API для всех операций по Быстроделу")
public class StructureOdController {
    private final StructureOdService structureOdService;
    private final DivisionMapper divisionMapper;
    private final RegionalDirectorMapper regionalDirectorMapper;
    private final TerritorialDirectorMapper territorialDirectorMapper;
    private final FilialDirectorMapper filialDirectorMapper;
    private final FilialMapper filialMapper;

    @CrossOrigin
    @GetMapping("/structure-od")
    @Operation(summary = "Метод возвращающий данные структуры ОД")
    public ResponseEntity<StructureOdDto> getStructureOd(@RequestParam(required = false) List<Short> filials,
                                                         @RequestParam(required = false) List<String> formats,
                                                         @RequestParam(required = false) List<String> cities,
                                                         @RequestParam(required = false) List<String> regionalDirectors,
                                                         @RequestParam(required = false) List<String> territorialDirectors,
                                                         @RequestParam(required = false) List<String> filialDirectors,
                                                         @RequestParam(required = false) Byte isOpen) {
        StructureOdFilterParam filter = StructureOdFilterParam.builder()
            .filials(filials)
            .formats(formats)
            .cities(cities)
            .regionalDirectors(regionalDirectors)
            .territorialDirectors(territorialDirectors)
            .filialDirectors(filialDirectors)
            .isOpen(isOpen)
            .build();
        return ResponseEntity.ok(new StructureOdDto(structureOdService.getStructureOd(filter)));
    }

    @CrossOrigin
    @PostMapping("/division")
    @Operation(summary = "Метод добавляющий новый дивизион")
    public ResponseEntity<DivisionDto> createDivision(@RequestBody DivisionDto divisionDto) {
        return ResponseEntity.ok(divisionMapper.toDto(structureOdService.saveDivision(divisionMapper.toEntity(divisionDto))));
    }

    @CrossOrigin
    @DeleteMapping("/division/{id}")
    @Operation(summary = "Метод удаляющий дивизион")
    public ResponseEntity<Void> deleteDivision(@PathVariable("id") Short id) {
        structureOdService.deleteDivision(id);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PutMapping("/division/{id}")
    @Operation(summary = "Метод изменяющий дивизион")
    public ResponseEntity<DivisionDto> updateDivision(@PathVariable("id") Short id,
                                                      @RequestBody DivisionDto divisionDto) {
        return ResponseEntity.ok(divisionMapper.toDto(structureOdService.updateDivision(id, divisionDto)));
    }

    @CrossOrigin
    @PostMapping("/regional-director")
    @Operation(summary = "Метод добавляющий нового регионального директора")
    public ResponseEntity<RegionalDirectorDto> createRegionalDirector(@RequestBody RegionalDirectorDto regionalDirectorDto) {
        return ResponseEntity.ok(regionalDirectorMapper.toDto(structureOdService.saveRegionalDirector(regionalDirectorDto)));
    }

    @CrossOrigin
    @DeleteMapping("/regional-director/{id}")
    @Operation(summary = "Метод удаляющий регионального директора")
    public ResponseEntity<Void> deleteRegionalDirector(@PathVariable("id") Short id) {
        structureOdService.deleteRegionalDirector(id);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PutMapping("/regional-director/{id}")
    @Operation(summary = "Метод изменяющий регионального директора")
    public ResponseEntity<RegionalDirectorDto> updateRegionalDirector(@PathVariable("id") Short id,
                                                                      @RequestBody RegionalDirectorDto regionalDirectorDto) {
        return ResponseEntity.ok(regionalDirectorMapper.toDto(structureOdService.updateRegionalDirector(id, regionalDirectorDto)));
    }

    @CrossOrigin
    @PostMapping("/territorial-director")
    @Operation(summary = "Метод добавляющий нового территориального директора")
    public ResponseEntity<TerritorialDirectorDto> createTerritorialDirector(@RequestBody TerritorialDirectorDto territorialDirectorDto) {
        return ResponseEntity.ok(territorialDirectorMapper.toDto(structureOdService.saveTerritorialDirector(territorialDirectorDto)));
    }

    @CrossOrigin
    @DeleteMapping("/territorial-director/{id}")
    @Operation(summary = "Метод удаляющий территориального директора")
    public ResponseEntity<Void> deleteTerritorialDirector(@PathVariable("id") Short id) {
        structureOdService.deleteTerritorialDirector(id);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PutMapping("/territorial-director/{id}")
    @Operation(summary = "Метод изменяющий территориального директора")
    public ResponseEntity<TerritorialDirectorDto> updateTerritorialDirector(@PathVariable("id") Short id,
                                                                            @RequestBody TerritorialDirectorDto territorialDirectorDto) {
        return ResponseEntity.ok(territorialDirectorMapper.toDto(structureOdService.updateTerritorialDirector(id, territorialDirectorDto)));
    }

    @CrossOrigin
    @GetMapping("/filials")
    @Operation(summary = "Метод возвращающий список всех филиалов")
    public ResponseEntity<List<FilialDto>> getFilials() {
        return ResponseEntity.ok(structureOdService.getFilials()
            .stream()
            .map(filialMapper::toDto)
            .collect(Collectors.toList()));
    }

    @CrossOrigin
    @PostMapping("/filial-director")
    @Operation(summary = "Метод добавляющий нового директора филиала")
    public ResponseEntity<FilialDirectorDto> createFilialDirector(@RequestBody FilialDirectorDto filialDirectorDto) {
        return ResponseEntity.ok(filialDirectorMapper.toDto(structureOdService.saveFilialDirector(filialDirectorDto)));
    }

    @CrossOrigin
    @DeleteMapping("/filial-director/{id}")
    @Operation(summary = "Метод удаляющий директора филиала")
    public ResponseEntity<Void> deleteFilialDirector(@PathVariable("id") Short id) {
        structureOdService.deleteFilialDirector(id);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PutMapping("/filial-director/{id}")
    @Operation(summary = "Метод изменяющий директора филиала")
    public ResponseEntity<FilialDirectorDto> updateFilialDirector(@PathVariable("id") Short id,
                                                                  @RequestBody FilialDirectorDto filialDirectorDto) {
        return ResponseEntity.ok(filialDirectorMapper.toDto(structureOdService.updateFilialDirector(id, filialDirectorDto)));
    }

    @CrossOrigin
    @GetMapping("/filials/{tabNumber}")
    @Operation(summary = "Метод возвращающий филиалы доступные директору")
    public ResponseEntity<List<FilialDto>> getFilialsByTabNumber(@PathVariable("tabNumber") String tabNumber) {
        List<Filial> filialList = structureOdService.getFilialsByTabNumber(tabNumber);
        if (filialList != null) {
            return ResponseEntity.ok(filialList
                .stream()
                .map(filialMapper::toDto)
                .collect(Collectors.toList()));
        }
        return null;
    }
}
