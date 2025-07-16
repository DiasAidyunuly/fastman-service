package kz.magnum.magnumback.fastmanservice.service.checklist;

import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.UpdateChecklistPhotoCompletionModel;

import java.util.List;

public interface ChecklistPhotoCompletionService {
    List<UpdateChecklistPhotoCompletionModel> update(Long id, List<UpdateChecklistPhotoCompletionModel> param);
}