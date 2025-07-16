package kz.magnum.magnumback.fastmanservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.magnum.magnumback.fastmanservice.service.AnalyticService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/fastman/analytics", name = "Методы для аналитики")
@Tag(name = "Fastman Controllers", description = "API для всех операций по Быстроделу")
public class AnalyticController {
    private final AnalyticService analyticService;

    @CrossOrigin
    @GetMapping
    @Operation(summary = "Метод возвращающий список аналитических задач")
    public ResponseEntity<List<?>> getAnalyticTasks(@DateTimeFormat(pattern = "yyyy-MM-dd")
                                                    @RequestParam(required = false)
                                                    Date startDate,
                                                    @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                    @RequestParam(required = false)
                                                    Date endDate,
                                                    @RequestParam
                                                    String listBy,
                                                    @RequestParam(required = false)
                                                    List<Short> siteCodes,
                                                    @RequestParam(required = false)
                                                    List<Short> statusCodes,
                                                    @RequestParam(required = false)
                                                    List<Short> actionCodes,
                                                    @RequestParam(required = false)
                                                    List<Long> incidentCodes,
                                                    @RequestParam(required = false)
                                                    @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                    List<Date> dates) {
        return ResponseEntity.ok(analyticService.getTasks(startDate, endDate, listBy, siteCodes, statusCodes, actionCodes, incidentCodes, dates));
    }
}
