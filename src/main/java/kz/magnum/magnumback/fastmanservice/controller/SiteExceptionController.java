package kz.magnum.magnumback.fastmanservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.magnum.magnumback.fastmanservice.mapper.NewSiteExceptionMapper;
import kz.magnum.magnumback.fastmanservice.mapper.SiteExceptionMapper;
import kz.magnum.magnumback.fastmanservice.mapper.SiteExceptionWithIncidentNameMapper;
import kz.magnum.magnumback.fastmanservice.model.pdt.NewSiteExceptionDto;
import kz.magnum.magnumback.fastmanservice.model.pdt.SiteExceptionDto;
import kz.magnum.magnumback.fastmanservice.model.pdt.SiteExceptionWithIncidentNameDto;
import kz.magnum.magnumback.fastmanservice.service.SiteExceptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/fastman", name = "Методы для СУБП (Исключение по сайтам)")
@Tag(name = "Fastman Controllers", description = "API для всех операций по Быстроделу")
public class SiteExceptionController {
    private final SiteExceptionService siteExceptionService;
    private final SiteExceptionMapper siteExceptionMapper;
    private final SiteExceptionWithIncidentNameMapper siteExceptionWithIncidentNameMapper;
    private final NewSiteExceptionMapper newSiteExceptionMapper;

    @CrossOrigin
    @GetMapping("/new-site-exception")
    @Operation(summary = "Метод для получение данных по исключениям для задач по сайтам")
    public ResponseEntity<List<NewSiteExceptionDto>> getNewSiteExceptions() {
        return ResponseEntity.ok(siteExceptionService.findAllNewSites()
            .stream()
            .map(newSiteExceptionMapper::toDto)
            .collect(Collectors.toList()));
    }

    @CrossOrigin
    @GetMapping("/site-exception")
    @Operation(summary = "Метод для получение данных по исключениям для задач по сайтам")
    public ResponseEntity<List<SiteExceptionWithIncidentNameDto>> getSiteExceptionsDirect() {
        return ResponseEntity.ok(siteExceptionService.findAll()
            .stream()
            .map(siteExceptionWithIncidentNameMapper::toDto)
            .collect(Collectors.toList()));
    }

    @CrossOrigin
    @PostMapping("/site-exception")
    @Operation(summary = "Метод добавляющий новое исключение")
    public ResponseEntity<SiteExceptionDto> createSiteExceptionDirect(@RequestBody SiteExceptionDto siteExceptionDto) {
        return ResponseEntity.ok(siteExceptionMapper.toDto(siteExceptionService.save(siteExceptionMapper.toEntity(siteExceptionDto))));
    }

    @CrossOrigin
    @DeleteMapping("/site-exception/{id}")
    @Operation(summary = "Метод удаляющий исключение по сайтам")
    public ResponseEntity<Void> deleteSiteExceptionDirect(@PathVariable("id") Long id) {
        siteExceptionService.delete(id);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PutMapping("/site-exception/{id}")
    @Operation(summary = "Метод изменяющий исключение по сайтам")
    public ResponseEntity<SiteExceptionDto> updateSiteExceptionDirect(@PathVariable("id") Long id,
                                                                      @RequestBody Integer excQtyTask) {
        return ResponseEntity.ok(siteExceptionMapper.toDto(siteExceptionService.update(id, excQtyTask)));
    }
}
