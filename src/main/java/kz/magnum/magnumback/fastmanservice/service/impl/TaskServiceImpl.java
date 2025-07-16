package kz.magnum.magnumback.fastmanservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import kz.magnum.magnumback.fastmanservice.config.GoldDataSourceConfiguration;
import kz.magnum.magnumback.fastmanservice.entity.fastman.KafkaLog;
import kz.magnum.magnumback.fastmanservice.entity.fastman.Status;
import kz.magnum.magnumback.fastmanservice.entity.fastman.kafka.FastmanTask;
import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.Action;
import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.ExecutionTask;
import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.Incident;
import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.SiteException;
import kz.magnum.magnumback.fastmanservice.entity.general.Filial;
import kz.magnum.magnumback.fastmanservice.entity.portal.DocStorageEntity;
import kz.magnum.magnumback.fastmanservice.entity.portal.MagnumSystemModulesEntity;
import kz.magnum.magnumback.fastmanservice.exception.FastmanNotFoundException;
import kz.magnum.magnumback.fastmanservice.mapper.ExecutionTaskMapper;
import kz.magnum.magnumback.fastmanservice.model.Constants;
import kz.magnum.magnumback.fastmanservice.model.FastmanTaskSaveResult;
import kz.magnum.magnumback.fastmanservice.model.file.File;
import kz.magnum.magnumback.fastmanservice.model.pdt.*;
import kz.magnum.magnumback.fastmanservice.procedure.GetItemsStockProcedure;
import kz.magnum.magnumback.fastmanservice.repository.fastman.*;
import kz.magnum.magnumback.fastmanservice.repository.portal.DocStorageRepository;
import kz.magnum.magnumback.fastmanservice.repository.portal.MagnumSystemModulesRepository;
import kz.magnum.magnumback.fastmanservice.service.ActionService;
import kz.magnum.magnumback.fastmanservice.service.IncidentService;
import kz.magnum.magnumback.fastmanservice.service.StatusService;
import kz.magnum.magnumback.fastmanservice.service.TaskService;
import kz.magnum.magnumback.fastmanservice.service.file.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final static Byte INCIDENT_DISABLE_CODE_ACTIVITY = 0;
    private final static Byte INCIDENT_CODE_ACTIVITY_BY_DAY_OF_THE_WEEK = 1;
    private final static Byte SUCCESS_SAVE_STATUS = 1;
    private final static Byte FAILED_SAVE_STATUS = 0;
    private final static String ERROR_CONVERTING = "Error converting";
    private final FastmanTaskRepository fastmanTaskRepository;
    private final ActionRepository actionRepository;
    private final StatusRepository statusRepository;
    private final IncidentService incidentService;
    private final ActionService actionService;
    private final StatusService statusService;
    private final FilialRepository filialRepository;
    private final SiteExceptionRepository siteExceptionRepository;
    private final KafkaLogRepository kafkaLogRepository;
    private final MagnumSystemModulesRepository magnumSystemModulesRepository;
    private final DocStorageRepository docStorageRepository;
    private final ExecutionTaskRepository executionTaskRepository;
    private final ExecutionTaskMapper executionTaskMapper;
    private final ObjectMapper objectMapper;
    private final FileService fileService;
    private MinioClient minioClient;
    private GetItemsStockProcedure getItemsStockProcedure;
    private final GoldDataSourceConfiguration goldDataSourceConfiguration;
    @Value("${minio.bucket-name}")
    private String bucketName;
    @Value("${minio.url}")
    private String minioUrl;
    @Value("${minio.access-key}")
    private String accessKey;
    @Value("${minio.secret-key}")
    private String secretKey;

    @PostConstruct
    public void init() {
        this.minioClient = MinioClient.builder()
            .endpoint(minioUrl)
            .credentials(accessKey, secretKey)
            .build();
        this.getItemsStockProcedure = new GetItemsStockProcedure(goldDataSourceConfiguration.secondDataSource());
    }

    @Override
    public IncidentStatusesDto getIncidentsWithStatuses(Short siteCode) {
        List<FastmanTask> tasks = fastmanTaskRepository.findBySiteCode(siteCode);
        List<IncidentStatusesDto.IncidentModel> incidents = tasks.stream()
            .filter(task -> task.getIncident() != null)
            .collect(Collectors.groupingBy(task -> task.getIncident().getId()))
            .entrySet().stream()
            .flatMap(incidentEntry -> {
                Long incidentId = incidentEntry.getKey();
                List<FastmanTask> incidentTasks = incidentEntry.getValue();
                Map<Short, Long> statusCountMap = incidentTasks.stream()
                    .filter(task -> task.getStatus() != null)
                    .collect(Collectors.groupingBy(
                        task -> task.getStatus().getId(),
                        Collectors.mapping(FastmanTask::getItemCode, Collectors.toList())
                    ))
                    .entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, entry -> (long) entry.getValue().size()));
                return statusCountMap.entrySet().stream().map(statusEntry -> {
                    Short statusId = statusEntry.getKey();
                    int itemsCount = statusEntry.getValue().intValue();
                    IncidentStatusesDto.IncidentModel.StatusModel statusModel =
                        new IncidentStatusesDto.IncidentModel.StatusModel(statusId, itemsCount);
                    return new IncidentStatusesDto.IncidentModel(incidentId, statusModel);
                });
            })
            .collect(Collectors.toList());
        return IncidentStatusesDto.builder()
            .incidentsList(incidents)
            .build();
    }

    @Override
    public ActionStatusesDto getActionsWithStatuses(Short siteCode) {
        List<FastmanTask> tasks = fastmanTaskRepository.findBySiteCode(siteCode);
        List<ActionStatusesDto.ActionModel> actions = tasks.stream()
            .filter(task -> task.getAction() != null)
            .collect(Collectors.groupingBy(task -> task.getAction().getId()))
            .entrySet().stream()
            .flatMap(actionEntry -> {
                Short actionId = actionEntry.getKey();
                List<FastmanTask> actionTasks = actionEntry.getValue();
                Map<Short, Long> statusCountMap = actionTasks.stream()
                    .filter(task -> task.getStatus() != null)
                    .collect(Collectors.groupingBy(
                        task -> task.getStatus().getId(),
                        Collectors.mapping(FastmanTask::getItemCode, Collectors.toList())
                    ))
                    .entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, entry -> (long) entry.getValue().size()));
                return statusCountMap.entrySet().stream().map(statusEntry -> {
                    Short statusId = statusEntry.getKey();
                    int itemsCount = statusEntry.getValue().intValue();
                    ActionStatusesDto.ActionModel.StatusModel statusModel =
                        new ActionStatusesDto.ActionModel.StatusModel(statusId, itemsCount);
                    return ActionStatusesDto.ActionModel.builder()
                        .actionId(actionId)
                        .status(statusModel)
                        .build();
                });
            })
            .collect(Collectors.toList());
        return ActionStatusesDto.builder()
            .actionsList(actions)
            .build();
    }

    @Override
    public List<FastmanItemModel> getItems(Short siteCode, Long incidentCode, Short actionCode) {
        if (incidentCode == null && actionCode == null) {
            throw new FastmanNotFoundException("ID инцидента или действия не введены");
        }
        List<FastmanTask> items;
        if (incidentCode != null) {
            items = fastmanTaskRepository.findBySiteCodeAndIncident(siteCode, incidentCode);
        } else {
            items = fastmanTaskRepository.findBySiteCodeAndAction(siteCode, actionCode);
        }
        return items
            .stream()
            .map(e -> FastmanItemModel
                .builder()
                .fastmanTaskId(e.getId())
                .itemCode(e.getItemCode())
                .siteCode(e.getSiteCode())
                .supplierName(e.getSupplierName())
                .incidentCode(e.getIncident().getId())
                .statusCode(e.getStatus().getId())
                .actionCode(e.getAction().getId())
                .date(e.getDate())
                .stockQty(e.getStockQty())
                .dateAction(e.getDateAction())
                .executor(e.getExecutor())
                .description(e.getDescription())
                .dateCompletion(e.getDateCompletion())
                .dateCreate(e.getDateCreate())
                .dateEnd(e.getDateEnd())
                .dateCompletion(e.getDateCompletion())
                .dateCheck(e.getDateCheck())
                .taskCheck(e.getTaskCheck())
                .build())
            .collect(Collectors.toList());
    }

    @Override
    public List<FastmanTask> saveTasks(CreateTaskParamModel taskParam) {
        Incident incident = incidentService.findById(taskParam.getIncidentCode());
        Action action = actionService.findById(taskParam.getActionCode());
        Status status = statusService.findById((short) 1);

        String photoUrl;
        if (StringUtils.hasText(taskParam.getPhotoCreate()) || StringUtils.hasText(taskParam.getPhotoExtension())) {
            File photo = new File();
            photo.setFileContents(Base64.decodeBase64(taskParam.getPhotoCreate()));
            photo.setFileExtension(taskParam.getPhotoExtension());
            String photoId = fileService.storeFile(photo);
            photoUrl = fileService.getFileUrl(photoId, taskParam.getPhotoExtension());
        } else {
            photoUrl = null;
        }

        String fileUrl;
        if (StringUtils.hasText(taskParam.getFileCreate()) || StringUtils.hasText(taskParam.getFileExtension())) {
            File file = new File();
            file.setFileContents(Base64.decodeBase64(taskParam.getFileCreate()));
            file.setFileExtension(taskParam.getFileExtension());
            String fileId = fileService.storeFile(file);
            fileUrl = fileService.getFileUrl(fileId, taskParam.getFileExtension());
        } else {
            fileUrl = null;
        }

        List<Short> sites = taskParam.getListSiteCode();
        List<String> items = taskParam.getItemCodes();
        items = (items == null || items.isEmpty()) ? Collections.singletonList(null) : items;
        List<FastmanTask> fastmanTasks = new ArrayList<>();
        items.parallelStream().forEach(item ->
            sites.parallelStream().forEach(site ->
                {
                    FastmanTask fastmanTask = FastmanTask.builder()
                        .itemCode(item)
                        .incident(incident)
                        .action(action)
                        .status(status)
                        .description(taskParam.getDescription())
                        .executor(taskParam.getExecutor())
                        .siteCode(site)
                        .taskCheck(taskParam.getTaskCheck())
                        .photoCreate(taskParam.getPhotoCreate())
                        .userChange(taskParam.getUserChange())
                        .isManual(true)
                        .dateCreate(taskParam.getDateCreate())
                        .dateEnd(taskParam.getDateEnd())
                        .photoCreate(photoUrl)
                        .fileTask(fileUrl)
                        .build();
                    fastmanTasks.add(fastmanTask);
                }
            )
        );
        return fastmanTaskRepository.saveAll(fastmanTasks);
    }

    @Override
    public void saveTaskFromKafka(String param) {
        LocalDateTime dateCreateAndAction = LocalDateTime.now();
        byte saveTaskStatus = FAILED_SAVE_STATUS;
        String errMsg = "";
        String body = param;
        try {
            FastmanKafkaReceipt fastmanKafkaReceipt;
            if (!isValidJson(param)) {
                errMsg = "Error kafka message syntax";
                return;
            } else {
                fastmanKafkaReceipt = objectMapper.readValue(param, FastmanKafkaReceipt.class);
            }

            Incident incident = incidentService.findById(fastmanKafkaReceipt.getIncidentId());
            if (!isIncidentDisableCodeActivityValid(incident)) {
                errMsg = "Incident disable code activity is not valid";
                return;
            }

            if (!isIncidentCodeActivityByDayOfTheWeekValid(incident)) {
                errMsg = "Incident code activity by day of the week is not valid";
                return;
            }

            if (!isSiteCodeValid(fastmanKafkaReceipt.getLocationId())) {
                errMsg = "There is no site with code: " + fastmanKafkaReceipt.getLocationId();
                throw new FastmanNotFoundException(String.format("Сайт с таким кодом = %d не найден", fastmanKafkaReceipt.getLocationId()));
            }

            Status status = getStatusByIncidentId(incident);
            boolean isManual = incident.getTemplate().isManual();
            Action action = actionNotNullValidation(incident.getAction());

            Integer kafkaTaskCount = getKafkaTaskCount(fastmanKafkaReceipt, incident);
            if (kafkaTaskCount == null) {
                FastmanTaskSaveResult fastmanTaskSaveResult =
                    saveFastmanTask(fastmanKafkaReceipt, incident, isManual, status, action, dateCreateAndAction, dateCreateAndAction);
                saveTaskStatus = fastmanTaskSaveResult.getStatus();
                body = fastmanTaskSaveResult.getBody();
                errMsg = fastmanTaskSaveResult.getErrMsg();
            } else {
                for (int i = 1; i <= kafkaTaskCount; i++) {
                    if (fastmanKafkaReceipt.getIndex() > kafkaTaskCount) {
                        body = param;
                        errMsg = "Kafka message index must be less than kafkaTaskCount: " + kafkaTaskCount;
                        break;
                    } else {
                        if (fastmanKafkaReceipt.getIndex().equals(i)) {
                            FastmanTaskSaveResult fastmanTaskSaveResult =
                                saveFastmanTask(fastmanKafkaReceipt, incident, isManual, status, action, dateCreateAndAction, dateCreateAndAction);
                            saveTaskStatus = fastmanTaskSaveResult.getStatus();
                            body = fastmanTaskSaveResult.getBody();
                            errMsg = fastmanTaskSaveResult.getErrMsg();
                            saveTaskStatus = SUCCESS_SAVE_STATUS;
                        }
                    }
                }
            }
        } catch (Exception e) {
            errMsg = e.getMessage();
        } finally {
            saveKafkaLogs(saveTaskStatus, body, errMsg);
        }
    }

    @Override
    public FastmanTask findById(Long id) {
        FastmanTask fastmanTask = fastmanTaskRepository.findById(id).orElseThrow(
            () -> new FastmanNotFoundException(String.format("Задача с таким ID = %d не найдена", id)));
        List<ExecutionTask> executionTasks = executionTaskRepository.findByFastmanTaskId(id);
        fastmanTask.setExecutionTasks(executionTasks);
        return fastmanTask;
    }

    @Override
    public GetItemsStockModel findItemsStock(GetItemsStockParamModel param) {
        return getItemsStockProcedure.execute(param.getSiteCode(), param.getItems());
    }

    private boolean isValidJson(String input) {
        if (input == null) {
            return false;
        }
        try {
            objectMapper.readValue(input, FastmanKafkaReceipt.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String convertToJson(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            return ERROR_CONVERTING;
        }
    }

    private boolean isIncidentDisableCodeActivityValid(Incident incident) {
        return incident.getDisable().equals(INCIDENT_DISABLE_CODE_ACTIVITY);
    }

    private boolean isIncidentCodeActivityByDayOfTheWeekValid(Incident incident) {
        return getCodeActivityByDayOfTheWeek(incident).equals(INCIDENT_CODE_ACTIVITY_BY_DAY_OF_THE_WEEK);
    }

    public Byte getCodeActivityByDayOfTheWeek(Incident incident) {
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        switch (dayOfWeek) {
            case MONDAY:
                return incident.getMon();
            case TUESDAY:
                return incident.getTue();
            case WEDNESDAY:
                return incident.getWed();
            case THURSDAY:
                return incident.getThu();
            case FRIDAY:
                return incident.getFri();
            case SATURDAY:
                return incident.getSat();
            case SUNDAY:
                return incident.getSun();
            default:
                return null;
        }
    }

    private boolean isSiteCodeValid(Short siteCode) {
        List<Filial> filials = filialRepository.findAll();
        return filials.stream()
            .anyMatch(filial -> filial.getCodeSiteTz().shortValue() == siteCode ||
                filial.getCodeSiteSp().shortValue() == siteCode);
    }

    private Status getStatusByIncidentId(Incident incident) {
        short statusId = 1;
        if (incident.getAction() != null) {
            statusId = 2;
        }
        return statusService.findById(statusId);
    }

    private Action actionNotNullValidation(Action actionParam) {
        if (actionParam == null) {
            Short actionId = 0;
            return actionService.findById(actionId);
        }
        return actionParam;
    }

    private Integer getKafkaTaskCount(FastmanKafkaReceipt fastmanKafkaReceipt, Incident incident) {
        List<SiteException> siteExceptions = siteExceptionRepository.findAll();
        return siteExceptions.stream()
            .filter(siteException ->
                siteException.getIncident().getId().equals(fastmanKafkaReceipt.getIncidentId()) &&
                    siteException.getLocationId().equals(fastmanKafkaReceipt.getLocationId()))
            .map(SiteException::getExcQtyTask)
            .findFirst()
            .orElse(incident.getQty_task());
    }


    private FastmanTaskSaveResult saveFastmanTask(FastmanKafkaReceipt fastmanKafkaReceipt, Incident incident,
                                                  boolean isManual, Status status, Action action,
                                                  LocalDateTime dateAction, LocalDateTime dateCreate) {
        FastmanTask fastmanTask =
            createFastmanTask(fastmanKafkaReceipt, fastmanKafkaReceipt.getLocationId(), incident, isManual, status, action, dateAction, dateCreate);
        String body = convertToJson(fastmanTask);
        if (body.equals(ERROR_CONVERTING)) {
            String errMsg = "Error converting FastmanTask object to JSON";
            return FastmanTaskSaveResult.builder()
                .status(FAILED_SAVE_STATUS)
                .body(body)
                .errMsg(errMsg)
                .build();
        }
        fastmanTaskRepository.save(fastmanTask);
        return FastmanTaskSaveResult.builder()
            .status(SUCCESS_SAVE_STATUS)
            .body(body)
            .errMsg("")
            .build();
    }

    private FastmanTask createFastmanTask(FastmanKafkaReceipt fastmanKafkaReceipt, Short siteCode, Incident incident,
                                          boolean isManual, Status status, Action action, LocalDateTime dateAction,
                                          LocalDateTime dateCreate) {
        return FastmanTask.builder()
            .itemCode(fastmanKafkaReceipt.getItemId())
            .siteCode(siteCode)
            .supplierName(fastmanKafkaReceipt.getSupplierName())
            .incident(incident)
            .date(fastmanKafkaReceipt.getDate())
            .price(fastmanKafkaReceipt.getPrice())
            .stockQty(fastmanKafkaReceipt.getStockQty())
            .isManual(isManual)
            .status(status)
            .action(action)
            .dateAction(dateAction)
            .dateCreate(dateCreate)
            .dateEnd(dateCreate.plusDays(incident.getCompleteDay()))
            .changeDate(LocalDateTime.now())
            .photoOriginal(fastmanKafkaReceipt.getPhotoOriginal())
            .departmentCode(fastmanKafkaReceipt.getDepartmentCode())
            .description(fastmanKafkaReceipt.getDescription())
            .build();
    }

    private void saveKafkaLogs(Byte saveTaskStatus, String body, String errMsg) {
        KafkaLog kafkaLog = KafkaLog.builder()
            .status(saveTaskStatus)
            .body(body)
            .dateCreate(LocalDateTime.now())
            .errText(errMsg)
            .build();
        kafkaLogRepository.save(kafkaLog);
    }

    @Override
    public CompletedItemModel saveCompletedItem(Long id, CompletedItemParam completedItemDto) {
        FastmanTask fastmanTask = findById(id);
        CompletedItemModel completedItemModel = new CompletedItemModel();

        LocalDateTime localDateTimeDateAction = LocalDateTime.now();
        Date dateAction = Date.from(localDateTimeDateAction.atZone(ZoneId.systemDefault()).toInstant());

        if (completedItemDto.getActionCode() != null && completedItemDto.getActionCode() > 0) {
            actionRepository.findById(completedItemDto.getActionCode())
                .ifPresent(fastmanTask::setAction);
            fastmanTask.setDateAction(localDateTimeDateAction);
            completedItemModel.setActionCode(completedItemDto.getActionCode());
            completedItemModel.setDateAction(dateAction);
        }

        if (completedItemDto.getStatusCode() != null) {
            completedItemModel.setStatusCode(completedItemDto.getStatusCode());
            statusRepository.findById(completedItemDto.getStatusCode())
                .ifPresent(fastmanTask::setStatus);
            if (completedItemDto.getStatusCode().equals((short) 3) ||
                completedItemDto.getStatusCode().equals((short) 4)) {
                fastmanTask.setDateCompletion(localDateTimeDateAction);
                completedItemModel.setDateCompletion(dateAction);
            }
        }

        List<ExecutionTask> executionTasks = new ArrayList<>();
        if (completedItemDto.getExecutionTasks() != null) {
            for (ExecutionTaskDto executionTaskDto : completedItemDto.getExecutionTasks()) {
                ExecutionTask executionTask = executionTaskMapper.toEntity(executionTaskDto);
                executionTask.setFastmanTask(fastmanTask);
                executionTaskRepository.save(executionTask);
                executionTasks.add(executionTask);
            }
        }

        fastmanTask.setUserChange(completedItemDto.getUserChange());
        completedItemModel.setExecutionTask(executionTasks);
        completedItemModel.setUserChange(completedItemDto.getUserChange());
        fastmanTaskRepository.save(fastmanTask);
        return completedItemModel;
    }

    @Override
    public String uploadFile(String base64, String fileName, String extension) {
        java.io.File tempFile;
        try {
            byte[] decodedBytes = Base64.decodeBase64(base64);
            tempFile = java.io.File.createTempFile("fastman-", "-" + fileName);
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(decodedBytes);
            }
            String objectName = fileName + extension;
            Tika tika = new Tika();
            String contentType = tika.detect(tempFile);
            try (InputStream inputStream = Files.newInputStream(tempFile.toPath())) {
                // Upload the file to MinIO
                minioClient.putObject(
                    PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .stream(inputStream, tempFile.length(), -1)
                        .contentType(contentType)
                        .build()
                );
            }
            String url = String.format("%s/%s/%s", minioUrl, bucketName, objectName);
            saveFileDb(url, extension, tempFile);
            return url;
        } catch (Exception e) {
            throw new RuntimeException("Error uploading file to MinIO: " + e.getMessage(), e);
        }
    }

    private void saveFileDb(String url, String extension, java.io.File file) {
        String fileCode = Constants.FASTMAN.getCode();
        Optional<MagnumSystemModulesEntity> magnumSystemModulesEntity = magnumSystemModulesRepository.findByModuleCode(fileCode);
        if (magnumSystemModulesEntity.isPresent()) {
            DocStorageEntity docStorageEntity = new DocStorageEntity();
            String fileGuid = docStorageEntity.getDocId().toString().replaceAll("[^A-Za-z0-9]", "") + extension;
            docStorageEntity.setMagnumSystemModulesEntity(magnumSystemModulesEntity.get());
            docStorageEntity.setDocExtension(extension);
            docStorageEntity.setDocName(file.getName());
            docStorageEntity.setDocGuid(fileGuid);
            docStorageEntity.setDocSize((float) file.length() / (1024 * 1024));
            docStorageEntity.setDocPath(fileCode + ": " + url);
            docStorageEntity.setDocUrl(url);
            docStorageRepository.save(docStorageEntity);
        }
    }
}