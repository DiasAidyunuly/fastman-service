package kz.magnum.magnumback.fastmanservice.service.checklist;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistChapter;

import java.util.List;

public interface ChecklistChapterService {
    List<ChecklistChapter> findAll(Long id);

    ChecklistChapter save(ChecklistChapter checklistChapter);

    ChecklistChapter update(Long id, String chapterName);

    void delete(Long id);

    ChecklistChapter findById(Long id);
}