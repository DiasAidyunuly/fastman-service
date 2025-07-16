package kz.magnum.magnumback.fastmanservice.service.checklist.impl;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistQuestion;
import kz.magnum.magnumback.fastmanservice.model.file.File;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.UpdateChecklistPhotoCompletionModel;
import kz.magnum.magnumback.fastmanservice.repository.checklist.ChecklistQuestionRepository;
import kz.magnum.magnumback.fastmanservice.service.checklist.ChecklistPhotoCompletionService;
import kz.magnum.magnumback.fastmanservice.service.checklist.ChecklistQuestionService;
import kz.magnum.magnumback.fastmanservice.service.file.FileService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChecklistPhotoCompletionServiceImpl implements ChecklistPhotoCompletionService {
    private final ChecklistQuestionRepository checklistQuestionRepository;
    private final FileService fileService;
    private final ChecklistQuestionService checklistQuestionService;

    @Override
    public List<UpdateChecklistPhotoCompletionModel> update(Long id, List<UpdateChecklistPhotoCompletionModel> param) {

        ChecklistQuestion checklistQuestion = checklistQuestionService.findById(id);

        List<UpdateChecklistPhotoCompletionModel> resultList = new ArrayList<>();
        StringBuilder allPhotoCompletionBuilder = new StringBuilder();

        for (UpdateChecklistPhotoCompletionModel updateChecklistPhotoCompletion : param) {
            File photo = new File();
            photo.setFileContents(Base64.decodeBase64(updateChecklistPhotoCompletion.getPhotoCompletion()));
            photo.setFileExtension(updateChecklistPhotoCompletion.getPhotoExtension());
            String photoId = fileService.storeFile(photo);
            String photoUrl = fileService.getFileUrl(photoId, updateChecklistPhotoCompletion.getPhotoExtension());

            UpdateChecklistPhotoCompletionModel updateChecklistPhotoCompletionModel = UpdateChecklistPhotoCompletionModel.builder()
                .photoCompletion(photoUrl)
                .photoExtension(updateChecklistPhotoCompletion.getPhotoExtension())
                .build();
            resultList.add(updateChecklistPhotoCompletionModel);
            allPhotoCompletionBuilder.append(photoUrl).append(";");
        }
        checklistQuestion.setPhotoCompletion(allPhotoCompletionBuilder.toString());
        checklistQuestionRepository.save(checklistQuestion);
        return resultList;
    }
}