package kz.magnum.magnumback.fastmanservice.service.checklist.impl;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistChapter;
import kz.magnum.magnumback.fastmanservice.exception.FastmanNotFoundException;
import kz.magnum.magnumback.fastmanservice.repository.checklist.ChecklistChapterRepository;
import kz.magnum.magnumback.fastmanservice.service.checklist.ChecklistChapterService;
import kz.magnum.magnumback.fastmanservice.service.checklist.ChecklistHeadTempService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChecklistChapterServiceImpl implements ChecklistChapterService {
    private final ChecklistChapterRepository checklistChapterRepository;
    private final ChecklistHeadTempService checklistHeadTempService;

    @Override
    public List<ChecklistChapter> findAll(Long id) {
        return checklistChapterRepository.findByChecklistHeadTempId(id);
    }

    @Override
    public ChecklistChapter save(ChecklistChapter checklistChapterParam) {
        checklistChapterParam.setChecklistHeadTemp(checklistHeadTempService.findById(checklistChapterParam.getChecklistHeadTemp().getId()));
        checklistChapterParam.setIsGrouped(true);
        return checklistChapterRepository.save(checklistChapterParam);
    }

    @Override
    public ChecklistChapter update(Long id, String chapterName) {
        ChecklistChapter checklistChapter = findById(id);
        checklistChapter.setChapterName(chapterName);
        checklistChapter.setIsGrouped(true);
        return checklistChapterRepository.save(checklistChapter);
    }

    @Override
    public void delete(Long id) {
        checklistChapterRepository.deleteById(id);
    }

    @Override
    public ChecklistChapter findById(Long id) {
        return checklistChapterRepository.findById(id).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Разделов шаблона по чеклистам с таким ID = %d не найден", id)));
    }
}