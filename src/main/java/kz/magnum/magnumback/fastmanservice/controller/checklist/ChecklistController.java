package kz.magnum.magnumback.fastmanservice.controller.checklist;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.magnum.magnumback.fastmanservice.mapper.checklist.ChecklistBySiteAndDataMapper;
import kz.magnum.magnumback.fastmanservice.mapper.checklist.ChecklistQuestionMapper;
import kz.magnum.magnumback.fastmanservice.mapper.checklist.GetChecklistsByQuestionStatusResponseMapper;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.*;
import kz.magnum.magnumback.fastmanservice.service.checklist.ChecklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/fastman/checklist", name = "Методы для чеклистов (Главный раздел, список чек-листов, окно фильтрации списка ЧЛ)")
@Tag(name = "Fastman Controllers", description = "API для всех операций по Быстроделу")
public class ChecklistController {
    private final ChecklistService checklistService;
    private final ChecklistBySiteAndDataMapper checklistBySiteAndDataMapper;
    private final GetChecklistsByQuestionStatusResponseMapper getChecklistsByQuestionStatusResponseMapper;
    private final ChecklistQuestionMapper checklistQuestionMapper;

    @CrossOrigin
    @GetMapping
    @Operation(summary = "Метод для отображения главного раздела, список чек-листов, окно фильтрации списка ЧЛ")
    public ResponseEntity<ChecklistBySiteAndDataDto> getChecklists(@RequestParam Integer site,
                                                                   @RequestParam(required = false) List<Long> status,
                                                                   @RequestParam(required = false) List<Long> type,
                                                                   @RequestParam(required = false) List<Long> temp,
                                                                   @RequestParam(required = false) List<String> performer,
                                                                   @RequestParam(required = false) List<String> role,
                                                                   @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                                   @RequestParam(required = false)
                                                                   Date startDate,
                                                                   @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                                   @RequestParam(required = false)
                                                                   Date endDate) {
        return ResponseEntity.ok(checklistBySiteAndDataMapper.toDto(
            checklistService.findChecklists(site, status, type, temp, performer, role, startDate, endDate))
        );
    }

    @CrossOrigin
    @PostMapping
    @Operation(summary = "Метод для создания чек-листа")
    public ResponseEntity<ChecklistCreateResponse> createChecklist(@RequestBody ChecklistHeadDtoParam checklistHeadDtoParam) {
        return ResponseEntity.ok(checklistService.saveChecklistHead(checklistHeadDtoParam));
    }

    @CrossOrigin
    @GetMapping("/{headId}")
    @Operation(summary = "Метод для получение данных чек-листа и списка вопросов")
    public ResponseEntity<GetChecklistsByQuestionStatusResponseDto> getChecklistsByQuestionStatus(@PathVariable("headId") Long headId,
                                                                                                  @RequestParam Long showQuestionStatus) {
        return ResponseEntity.ok(getChecklistsByQuestionStatusResponseMapper.toDto(checklistService.findChecklistsByQuestionStatus(headId, showQuestionStatus)));
    }

    @CrossOrigin
    @PutMapping("/{id}")
    @Operation(summary = "Метод для обновление вопроса в чек-листе")
    public ResponseEntity<ChecklistQuestionDto> updateChecklistQuestion(@PathVariable("id") Long id,
                                                                        @RequestBody UpdateChecklistQuestionParamDto updateChecklistQuestionParamDto) {
        return ResponseEntity.ok(
            checklistQuestionMapper.toDto(
                checklistService.updateChecklistQuestion(
                    id, checklistQuestionMapper.toUpdateDomain(updateChecklistQuestionParamDto))));
    }
}