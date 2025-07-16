package kz.magnum.magnumback.fastmanservice.service;

import kz.magnum.magnumback.fastmanservice.model.pdt.UpdateTaskPhotoCompletionResponse;
import kz.magnum.magnumback.fastmanservice.model.pdt.UpdateTaskPhotoCompletionParam;

import java.util.List;

public interface TaskPhotoCompletionService {
    List<UpdateTaskPhotoCompletionResponse> update(Long id, List<UpdateTaskPhotoCompletionParam> param);
}