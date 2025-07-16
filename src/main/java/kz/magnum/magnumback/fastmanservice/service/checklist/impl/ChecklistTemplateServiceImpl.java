package kz.magnum.magnumback.fastmanservice.service.checklist.impl;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistTemplate;
import kz.magnum.magnumback.fastmanservice.exception.FastmanNotFoundException;
import kz.magnum.magnumback.fastmanservice.repository.checklist.ChecklistTemplateRepository;
import kz.magnum.magnumback.fastmanservice.service.checklist.ChecklistTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChecklistTemplateServiceImpl implements ChecklistTemplateService {
    private final ChecklistTemplateRepository checklistTemplateRepository;

    @Override
    public List<ChecklistTemplate> findAll() {
        return checklistTemplateRepository.findAll();
    }

    @Override
    public ChecklistTemplate save(ChecklistTemplate checklistTemplateParam) {
        return checklistTemplateRepository.save(checklistTemplateParam);
    }

    @Override
    public ChecklistTemplate update(Long id, ChecklistTemplate checklistTemplateParam) {
        checklistTemplateRepository.findById(id).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Чеклист шаблон с таким ID = %d не найден", id)));
        checklistTemplateParam.setId(id);
        return checklistTemplateRepository.save(checklistTemplateParam);
    }

    @Override
    public void delete(Long id) {
        checklistTemplateRepository.deleteById(id);
    }

    @Override
    public ChecklistTemplate findById(Long id) {
        return checklistTemplateRepository.findById(id).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Чеклист шаблон с таким ID = %d не найден", id)));
    }
}