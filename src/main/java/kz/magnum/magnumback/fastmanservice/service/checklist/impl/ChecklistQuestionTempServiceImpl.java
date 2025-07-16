package kz.magnum.magnumback.fastmanservice.service.checklist.impl;

import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistChapter;
import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistQuestionTemp;
import kz.magnum.magnumback.fastmanservice.entity.checklist.ChecklistTypeOfAnswer;
import kz.magnum.magnumback.fastmanservice.exception.FastmanNotFoundException;
import kz.magnum.magnumback.fastmanservice.model.file.File;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.ChecklistQuestionTempModel;
import kz.magnum.magnumback.fastmanservice.repository.checklist.ChecklistQuestionTempRepository;
import kz.magnum.magnumback.fastmanservice.service.checklist.ChecklistChapterService;
import kz.magnum.magnumback.fastmanservice.service.checklist.ChecklistQuestionTempService;
import kz.magnum.magnumback.fastmanservice.service.checklist.ChecklistTypeOfAnswerService;
import kz.magnum.magnumback.fastmanservice.service.file.FileService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChecklistQuestionTempServiceImpl implements ChecklistQuestionTempService {
    private final ChecklistQuestionTempRepository checklistQuestionTempRepository;
    private final ChecklistChapterService checklistChapterService;
    private final ChecklistTypeOfAnswerService checklistTypeOfAnswerService;
    private final FileService fileService;

    @Override
    public List<ChecklistQuestionTemp> findAll(Long id) {
        return checklistQuestionTempRepository.findByChecklistHeadTempId(id);
    }

    @Override
    public ChecklistQuestionTempModel save(ChecklistQuestionTempModel checklistQuestionTempModel) {
        ChecklistChapter checklistChapter = checklistChapterService.findById(checklistQuestionTempModel.getChecklistChapter().getId());
        ChecklistTypeOfAnswer checklistTypeOfAnswer = checklistTypeOfAnswerService.findById(checklistQuestionTempModel.getChecklistTypeOfAnswer().getId());

        String photoUrl = null;
        String fileUrl = null;

        Map<String, String> fileUrls = saveFilesToS3(checklistQuestionTempModel);
        for (Map.Entry<String, String> entry : fileUrls.entrySet()) {
            if (entry.getKey() != null) {
                photoUrl = entry.getKey();
            }
            if (entry.getValue() != null) {
                fileUrl = entry.getValue();
            }
        }

        ChecklistQuestionTemp checklistQuestionTemp = ChecklistQuestionTemp.builder()
            .checklistChapter(checklistChapter)
            .checklistTypeOfAnswer(checklistTypeOfAnswer)
            .questionName(checklistQuestionTempModel.getQuestionName())
            .photoResolution(checklistQuestionTempModel.isPhotoResolution())
            .weight(checklistQuestionTempModel.getWeight())
            .photoMandatory(checklistQuestionTempModel.isPhotoMandatory())
            .photoMaximum(checklistQuestionTempModel.getPhotoMaximum())
            .commentResolution(checklistQuestionTempModel.isCommentResolution())
            .photoCreate(photoUrl)
            .fileCreate(fileUrl)
            .build();
        checklistQuestionTempRepository.save(checklistQuestionTemp);

        return ChecklistQuestionTempModel.builder()
            .checklistChapter(checklistChapter)
            .checklistTypeOfAnswer(checklistTypeOfAnswer)
            .questionName(checklistQuestionTempModel.getQuestionName())
            .photoResolution(checklistQuestionTempModel.isPhotoResolution())
            .weight(checklistQuestionTempModel.getWeight())
            .photoMandatory(checklistQuestionTempModel.isPhotoMandatory())
            .photoMaximum(checklistQuestionTempModel.getPhotoMaximum())
            .commentResolution(checklistQuestionTempModel.isCommentResolution())
            .photoCreate(photoUrl)
            .fileCreate(fileUrl)
            .photoExtension(checklistQuestionTempModel.getPhotoExtension())
            .fileExtension(checklistQuestionTempModel.getFileExtension())
            .build();
    }

    @Override
    public ChecklistQuestionTempModel update(Long id, ChecklistQuestionTempModel checklistQuestionTempModel) {
        ChecklistChapter checklistChapter = checklistChapterService.findById(checklistQuestionTempModel.getChecklistChapter().getId());
        ChecklistTypeOfAnswer checklistTypeOfAnswer = checklistTypeOfAnswerService.findById(checklistQuestionTempModel.getChecklistTypeOfAnswer().getId());

        String photoUrl = null;
        String fileUrl = null;

        Map<String, String> fileUrls = saveFilesToS3(checklistQuestionTempModel);
        for (Map.Entry<String, String> entry : fileUrls.entrySet()) {
            if (entry.getKey() != null) {
                photoUrl = entry.getKey();
            }
            if (entry.getValue() != null) {
                fileUrl = entry.getValue();
            }
        }

        ChecklistQuestionTemp checklistQuestionTemp = checklistQuestionTempRepository.findById(id).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Раздел шаблона по управлению вопросами шапок с таким ID = %d не найден", id)));
        checklistQuestionTemp.setChecklistChapter(checklistChapter);
        checklistQuestionTemp.setChecklistTypeOfAnswer(checklistTypeOfAnswer);
        checklistQuestionTemp.setQuestionName(checklistQuestionTempModel.getQuestionName());
        checklistQuestionTemp.setPhotoResolution(checklistQuestionTempModel.isPhotoResolution());
        checklistQuestionTemp.setWeight(checklistQuestionTempModel.getWeight());
        checklistQuestionTemp.setPhotoMandatory(checklistQuestionTempModel.isPhotoMandatory());
        checklistQuestionTemp.setPhotoMaximum(checklistQuestionTempModel.getPhotoMaximum());
        checklistQuestionTemp.setCommentResolution(checklistQuestionTempModel.isCommentResolution());
        checklistQuestionTemp.setPhotoCreate(photoUrl);
        checklistQuestionTemp.setFileCreate(fileUrl);
        checklistQuestionTempRepository.save(checklistQuestionTemp);

        return ChecklistQuestionTempModel.builder()
            .checklistChapter(checklistChapter)
            .checklistTypeOfAnswer(checklistTypeOfAnswer)
            .questionName(checklistQuestionTempModel.getQuestionName())
            .photoResolution(checklistQuestionTempModel.isPhotoResolution())
            .weight(checklistQuestionTempModel.getWeight())
            .photoMandatory(checklistQuestionTempModel.isPhotoMandatory())
            .photoMaximum(checklistQuestionTempModel.getPhotoMaximum())
            .commentResolution(checklistQuestionTempModel.isCommentResolution())
            .photoCreate(photoUrl)
            .fileCreate(fileUrl)
            .photoExtension(checklistQuestionTempModel.getPhotoExtension())
            .fileExtension(checklistQuestionTempModel.getFileExtension())
            .build();
    }

    @Override
    public void delete(Long id) {
        checklistQuestionTempRepository.deleteById(id);
    }

    private Map<String, String> saveFilesToS3(ChecklistQuestionTempModel checklistQuestionTempModel) {
        String photoUrl = null;
        String fileUrl = null;

        if (checklistQuestionTempModel.getPhotoCreate() != null) {
            File photo = new File();
            photo.setFileContents(Base64.decodeBase64(checklistQuestionTempModel.getPhotoCreate()));
            photo.setFileExtension(checklistQuestionTempModel.getPhotoExtension());
            String photoId = fileService.storeFile(photo);
            photoUrl = fileService.getFileUrl(photoId, checklistQuestionTempModel.getPhotoExtension());
        }

        if (checklistQuestionTempModel.getFileCreate() != null) {
            File file = new File();
            file.setFileContents(Base64.decodeBase64(checklistQuestionTempModel.getFileCreate()));
            file.setFileExtension(checklistQuestionTempModel.getFileExtension());
            String fileId = fileService.storeFile(file);
            fileUrl = fileService.getFileUrl(fileId, checklistQuestionTempModel.getFileExtension());
        }

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put(photoUrl, fileUrl);
        return resultMap;
    }
}