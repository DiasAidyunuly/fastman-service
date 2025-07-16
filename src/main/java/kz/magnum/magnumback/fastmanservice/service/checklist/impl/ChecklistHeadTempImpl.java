package kz.magnum.magnumback.fastmanservice.service.checklist.impl;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistChapter;
import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistHeadTemp;
import kz.magnum.magnumback.fastmanservice.exception.FastmanNotFoundException;
import kz.magnum.magnumback.fastmanservice.repository.checklist.ChecklistChapterRepository;
import kz.magnum.magnumback.fastmanservice.repository.checklist.ChecklistHeadTempRepository;
import kz.magnum.magnumback.fastmanservice.service.checklist.ChecklistHeadTempService;
import kz.magnum.magnumback.fastmanservice.service.checklist.ChecklistTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChecklistHeadTempImpl implements ChecklistHeadTempService {
    private final ChecklistHeadTempRepository checklistHeadTempRepository;
    private final ChecklistTemplateService checklistTemplateService;
    private final ChecklistChapterRepository checklistChapterRepository;
    private static final String CHAPTER_NAME = "Общий раздел";

    @Override
    public List<ChecklistHeadTemp> findAll() {
        return checklistHeadTempRepository.findAll();
    }

    @Override
    public ChecklistHeadTemp save(ChecklistHeadTemp checklistHeadTempParam) {
        checklistHeadTempParam.setChecklistTemplate(checklistTemplateService.findById(checklistHeadTempParam.getChecklistTemplate().getId()));
        ChecklistHeadTemp checklistHeadTemp = checklistHeadTempRepository.save(checklistHeadTempParam);

        ChecklistChapter checklistChapter = ChecklistChapter.builder()
            .chapterName(CHAPTER_NAME)
            .checklistHeadTemp(checklistHeadTemp)
            .isGrouped(false)
            .build();
        checklistChapterRepository.save(checklistChapter);
        return checklistHeadTemp;
    }

    @Override
    public ChecklistHeadTemp update(Long id, ChecklistHeadTemp checklistHeadTempParam) {
        findById(id);
        checklistHeadTempParam.setId(id);
        checklistHeadTempParam.setChecklistTemplate(
            checklistTemplateService.findById(checklistHeadTempParam.getChecklistTemplate().getId()));
        return checklistHeadTempRepository.save(checklistHeadTempParam);
    }

    @Override
    public void delete(Long id) {
        checklistHeadTempRepository.deleteById(id);
    }

    @Override
    public ChecklistHeadTemp findById(Long id) {
        return checklistHeadTempRepository.findById(id).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Чеклист шапка шаблон с таким ID = %d не найден", id)));
    }
}