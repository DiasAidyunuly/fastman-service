package kz.magnum.magnumback.fastmanservice.service.checklist.impl;

import kz.magnum.magnumback.fastmanservice.entity.checklist.*;
import kz.magnum.magnumback.fastmanservice.mapper.checklist.converter.ChecklistChapterIsGroupedFalseConverter;
import kz.magnum.magnumback.fastmanservice.mapper.checklist.converter.GetChecklistsByQuestionStatusResponseConverter;
import kz.magnum.magnumback.fastmanservice.model.pdt.checklist.*;
import kz.magnum.magnumback.fastmanservice.repository.checklist.*;
import kz.magnum.magnumback.fastmanservice.service.checklist.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChecklistServiceImpl implements ChecklistService {
    private static final Integer SUCCESS_SAVE_CODE = 1001;
    private static final Integer FAIL_SAVE_CODE = 1002;
    private static final String SUCCESS_SAVE_MESSAGE = "Check-list created";
    private static final String FAIL_SAVE_MESSAGE = "Fail to create check-list";
    private static final String DEFAULT_VALUE_STRING = "-1";
    private static final Long DEFAULT_VALUE_LONG = -1L;
    private static final Long ALL_QUESTION_STATUSES_VALUE = 0L;
    private static final Long CHECKLIST_QUESTION_STATUS_ACTIVE_VALUE = 1L;
    private static final Long CHECKLIST_QUESTION_STATUS_DONE_VALUE = 2L;
    private static final Long CHECKLIST_HEAD_STATUS_NOT_STARTED = 1L;
    private static final Long CHECKLIST_HEAD_STATUS_ACTIVE = 2L;
    private static final Long CHECKLIST_HEAD_STATUS_ON_CONFIRMATION = 3L;

    private final ChecklistHeadRepository checklistHeadRepository;
    private final ChecklistChapterRepository checklistChapterRepository;
    private final ChecklistQuestionTempRepository checklistQuestionTempRepository;
    private final ChecklistQuestionRepository checklistQuestionRepository;
    private final GetChecklistsByQuestionStatusResponseConverter getChecklistsByQuestionStatusResponseConverter;
    private final ChecklistChapterIsGroupedFalseConverter checklistChapterIsGroupedFalseConverter;
    private final ChecklistQuestionStatusService checklistQuestionStatusService;
    private final ChecklistHeadTempService checklistHeadTempService;
    private final ChecklistHeadStatusService checklistHeadStatusService;
    private final ChecklistHeadService checklistHeadService;
    private final ChecklistQuestionService checklistQuestionService;

    @Override
    public ChecklistBySiteAndDataModel findChecklists(Integer site,
                                                      List<Long> status,
                                                      List<Long> type,
                                                      List<Long> temp,
                                                      List<String> performer,
                                                      List<String> role,
                                                      Date startDate,
                                                      Date endDate) {
        // Validation
        status = status.stream().anyMatch(s -> s == 0L) ? List.of(DEFAULT_VALUE_LONG) : status;
        type = type.stream().anyMatch(e -> e == 0L) ? List.of(DEFAULT_VALUE_LONG) : type;
        temp = temp.stream().anyMatch(e -> e == 0L) ? List.of(DEFAULT_VALUE_LONG) : temp;
        performer = performer.stream().anyMatch(e -> e.equals("0")) ? List.of(DEFAULT_VALUE_STRING) : performer;
        role = role.stream().anyMatch(e -> e.equals("0")) ? List.of(DEFAULT_VALUE_STRING) : role;
        if (startDate == null || endDate == null) {
            startDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
            endDate = Date.from(LocalDate.now().atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant());
        }
        List<ChecklistDataModel> data = null;
        // Find all ChecklistHead object by parameter filters
        List<ChecklistHead> checklistHeads = checklistHeadRepository
            .findAllByFilters(site, startDate, endDate, status, type, temp, performer, role);

        if (!checklistHeads.isEmpty()) {
            // Divide by date and time
            List<Date> dateTimeList = checklistHeadRepository.findDateTime(site, startDate, endDate, status, type, temp, performer, role);
            data = new ArrayList<>();
            for (Date dateTime : dateTimeList) {
                LocalDateTime localDateTime = dateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                LocalDate date = localDateTime.toLocalDate();
                LocalTime time = localDateTime.toLocalTime();
                List<ChecklistModel> checklistModels = createChecklistModelList(checklistHeads);
                // Date filtration
                checklistModels = filterByDate(checklistModels, dateTime);

                ChecklistDataModel checklistDataModel = ChecklistDataModel.builder()
                    .date(date)
                    .time(time)
                    .checklists(checklistModels)
                    .build();
                data.add(checklistDataModel);
            }
        }
        return ChecklistBySiteAndDataModel.builder()
            .site(site)
            .data(data)
            .build();
    }

    @Override
    public ChecklistCreateResponse saveChecklistHead(ChecklistHeadDtoParam checklistHeadDtoParam) {
        List<Integer> sites = checklistHeadDtoParam.getSite();
        ChecklistHeadStatus checklistHeadStatus = checklistHeadStatusService.findById(1L);
        ChecklistHeadTemp checklistHeadTemp = checklistHeadTempService.findById(checklistHeadDtoParam.getIdTemplate());
        List<Long> checklistChapterIds = checklistChapterRepository.findIdsByIdHeadTemp(checklistHeadDtoParam.getIdTemplate());
        Integer qtyCountTotal = checklistQuestionTempRepository.findCountByChecklistChapterIds(checklistChapterIds);

        List<Integer> successSaveSites = new ArrayList<>();
        List<Integer> failSaveSites = new ArrayList<>();
        for (Integer site : sites) {
            ChecklistHead checklistHead = ChecklistHead.builder()
                .checklistHeadStatus(checklistHeadStatus)
                .checklistHeadTemp(checklistHeadTemp)
                .site(site)
                .startDate(checklistHeadDtoParam.getStartDate())
                .startEndDate(checklistHeadDtoParam.getStartEndDate())
                .roleCreator(checklistHeadDtoParam.getRoleCreator())
                .nameCreator(checklistHeadDtoParam.getNameCreator())
                .tabNumCreator(checklistHeadDtoParam.getTabNumCreator())
                .commentCreator(checklistHeadDtoParam.getCommentCreator())
                .qtyQuestionsDone(0)
                .qtyQuestionsTotal(qtyCountTotal)
                .build();
            try {
                checklistHeadRepository.save(checklistHead);
                successSaveSites.add(site);

                ChecklistQuestionStatus checklistQuestionStatus = checklistQuestionStatusService.findById(1L);

                List<ChecklistQuestionTemp> checklistQuestionTemps = checklistQuestionTempRepository.findByChecklistHeadTempId(checklistHeadDtoParam.getIdTemplate());

                for (ChecklistQuestionTemp checklistQuestionTemp : checklistQuestionTemps) {
                    ChecklistQuestion checklistQuestion = ChecklistQuestion.builder()
                        .checklistHead(checklistHead)
                        .checklistQuestionTemp(checklistQuestionTemp)
                        .checklistQuestionStatus(checklistQuestionStatus)
                        .questionAnswer(null)
                        .comment(null)
                        .weightCompletion(null)
                        .photoCompletion(null)
                        .build();
                    checklistQuestionRepository.save(checklistQuestion);
                }
            } catch (Exception e) {
                failSaveSites.add(site);
            }
        }
        ChecklistCreateSuccess checklistCreateSuccess = ChecklistCreateSuccess
            .builder()
            .responseCode(SUCCESS_SAVE_CODE)
            .responseMessage(SUCCESS_SAVE_MESSAGE)
            .sites(successSaveSites)
            .build();

        ChecklistCreateFail checklistCreateFail = ChecklistCreateFail
            .builder()
            .responseCode(FAIL_SAVE_CODE)
            .responseMessage(FAIL_SAVE_MESSAGE)
            .sites(failSaveSites)
            .build();
        return ChecklistCreateResponse.builder()
            .successSave(checklistCreateSuccess)
            .failSave(checklistCreateFail)
            .build();
    }

    @Override
    public GetChecklistsByQuestionStatusResponseModel findChecklistsByQuestionStatus(Long headId, Long showQuestionStatus) {
        ChecklistHead checklistHead = checklistHeadService.findById(headId);
        showQuestionStatus = showQuestionStatus.equals(ALL_QUESTION_STATUSES_VALUE) ? DEFAULT_VALUE_LONG : showQuestionStatus;

        List<ChecklistChapterIsGroupedTrueModel> trueQuestionChapters = new ArrayList<>();
        List<ChecklistChapterIsGroupedFalseModel> falseQuestionChapters = new ArrayList<>();
        Map<String, List<ChecklistChapterIsGroupedFalseModel>> groupedByChapter = new HashMap<>();

        List<ChecklistQuestion> questions = getChecklistQuestionsByHeadIdAndStatus(headId, showQuestionStatus);

        for (ChecklistQuestion question : questions) {
            ChecklistQuestionTemp checklistQuestionTemp = question.getChecklistQuestionTemp();
            ChecklistChapter checklistChapter = checklistQuestionTemp.getChecklistChapter();

            List<String> files = checklistQuestionTemp.getFileCreate() != null ? getFiles(checklistQuestionTemp.getFileCreate()) : null;
            List<String> photos = checklistQuestionTemp.getPhotoCreate() != null ? getFiles(checklistQuestionTemp.getPhotoCreate()) : null;
            List<String> completePhotos = question.getPhotoCompletion() != null ? getFiles(question.getPhotoCompletion()) : null;

            ChecklistChapterIsGroupedFalseModel checklistChapterIsGroupedFalseModel
                = checklistChapterIsGroupedFalseConverter.toModel(question);
            checklistChapterIsGroupedFalseModel.setFileAttachment(files);
            checklistChapterIsGroupedFalseModel.setPhotoAttachment(photos);
            checklistChapterIsGroupedFalseModel.setPhotoAttachmentComplete(completePhotos);

            if (checklistChapter.getIsGrouped()) {
                String chapterName = checklistChapter.getChapterName();
                if (!groupedByChapter.containsKey(chapterName)) {
                    groupedByChapter.put(chapterName, new ArrayList<>());
                }
                groupedByChapter.get(chapterName).add(checklistChapterIsGroupedFalseModel);
            } else {
                falseQuestionChapters.add(checklistChapterIsGroupedFalseModel);
            }
        }

        for (Map.Entry<String, List<ChecklistChapterIsGroupedFalseModel>> entry : groupedByChapter.entrySet()) {
            ChecklistChapterIsGroupedTrueModel model = ChecklistChapterIsGroupedTrueModel
                .builder()
                .chapterName(entry.getKey())
                .checklistChapterIsGroupedFalseList(entry.getValue())
                .build();
            trueQuestionChapters.add(model);
        }

        ChecklistQuestionByChapterIsGroupedModel checklistQuestionByChapterIsGroupedModel =
            ChecklistQuestionByChapterIsGroupedModel.builder()
                .checklistChapterIsGroupedTrueList(trueQuestionChapters)
                .checklistChapterIsGroupedFalseList(falseQuestionChapters)
                .build();

        GetChecklistsByQuestionStatusResponseModel result =
            getChecklistsByQuestionStatusResponseConverter.toModel(checklistHead);
        result.setQuestions(checklistQuestionByChapterIsGroupedModel);
        return result;
    }

    @Override
    public ChecklistQuestion updateChecklistQuestion(Long id, UpdateChecklistQuestionParamModel param) {
        ChecklistQuestion checklistQuestion = checklistQuestionService.findById(id);
        ChecklistQuestionStatus checklistQuestionStatus = checklistQuestionStatusService.findById(param.getIdQuestionStatus());
        checklistQuestion.setChecklistQuestionStatus(checklistQuestionStatus);

        Optional.ofNullable(param.getComment()).ifPresent(checklistQuestion::setComment);
        Optional.ofNullable(param.getAnswerQuestion()).ifPresent(checklistQuestion::setQuestionAnswer);
        Optional.ofNullable(param.getWeightCompletion()).ifPresent(checklistQuestion::setWeightCompletion);
        checklistQuestion = checklistQuestionRepository.save(checklistQuestion);

        int getQtyQuestionsDoneDifference = 0;
        if (param.getIdQuestionStatus().equals(CHECKLIST_QUESTION_STATUS_ACTIVE_VALUE)) {
            getQtyQuestionsDoneDifference = -1;
        } else if (param.getIdQuestionStatus().equals(CHECKLIST_QUESTION_STATUS_DONE_VALUE)) {
            getQtyQuestionsDoneDifference = 1;
        }

        ChecklistHead checklistHead = checklistQuestion.getChecklistHead();
        Long checklistHeadStatusValue = CHECKLIST_HEAD_STATUS_ACTIVE;
        if (checklistHead.getQtyQuestionsDone().equals(0)) {
            checklistHeadStatusValue = CHECKLIST_HEAD_STATUS_NOT_STARTED;
        } else if (checklistHead.getQtyQuestionsDone().equals(checklistHead.getQtyQuestionsTotal())) {
            checklistHeadStatusValue = CHECKLIST_HEAD_STATUS_ON_CONFIRMATION;
        }

        checklistHead.setQtyQuestionsDone(checklistHead.getQtyQuestionsDone() + getQtyQuestionsDoneDifference);
        checklistHead.setChecklistHeadStatus(checklistHeadStatusService.findById(checklistHeadStatusValue));
        checklistHeadRepository.save(checklistHead);
        return checklistQuestion;
    }

    private List<String> getFiles(String files) {
        return Arrays.stream(files.split("[,;]\\s*")).collect(Collectors.toList());
    }

    private List<ChecklistQuestion> getChecklistQuestionsByHeadIdAndStatus(Long headId, Long showQuestionStatus) {
        return checklistQuestionRepository
            .findByChecklistHeadAndChecklistQuestionStatus(headId, showQuestionStatus);
    }

    private List<ChecklistModel> createChecklistModelList(List<ChecklistHead> checklistHeads) {
        return checklistHeads
            .stream()
            .map(e -> ChecklistModel.builder()
                .checkListId(e.getId())
                .status(e.getChecklistHeadStatus().getId())
                .templateTypeId(e.getChecklistHeadTemp().getChecklistTemplate().getId())
                .templateId(e.getChecklistHeadTemp().getId())
                .rolePerformer(e.getChecklistHeadTemp().getRolePerformer())
                .nameCreator(e.getNameCreator())
                .tabNumCreator(e.getTabNumCreator())
                .roleCreator(e.getRoleCreator())
                .commentCreator(e.getCommentCreator())
                .questionsTotal(e.getQtyQuestionsTotal())
                .questionsDone(e.getQtyQuestionsDone())
                .startDate(e.getStartDate())
                .startEndDate(e.getStartEndDate())
                .build())
            .collect(Collectors.toList());
    }

    private List<ChecklistModel> filterByDate(List<ChecklistModel> checklistModels, Date dateTime) {
        return checklistModels.stream().filter(e -> e.getStartDate() != null && e.getStartDate().equals(dateTime)).collect(Collectors.toList());
    }
}