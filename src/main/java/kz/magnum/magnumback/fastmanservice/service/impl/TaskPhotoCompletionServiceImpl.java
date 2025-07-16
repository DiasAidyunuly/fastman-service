package kz.magnum.magnumback.fastmanservice.service.impl;

import kz.magnum.magnumback.fastmanservice.entity.fastman.kafka.FastmanTask;
import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.Execution;
import kz.magnum.magnumback.fastmanservice.entity.fastman.subp.ExecutionTask;
import kz.magnum.magnumback.fastmanservice.exception.FastmanNotFoundException;
import kz.magnum.magnumback.fastmanservice.mapper.ExecutionTaskMapper;
import kz.magnum.magnumback.fastmanservice.model.file.File;
import kz.magnum.magnumback.fastmanservice.model.pdt.UpdateTaskPhotoCompletionResponse;
import kz.magnum.magnumback.fastmanservice.model.pdt.UpdateTaskPhotoCompletionParam;
import kz.magnum.magnumback.fastmanservice.repository.fastman.ExecutionTaskRepository;
import kz.magnum.magnumback.fastmanservice.service.ExecutionService;
import kz.magnum.magnumback.fastmanservice.service.TaskPhotoCompletionService;
import kz.magnum.magnumback.fastmanservice.service.TaskService;
import kz.magnum.magnumback.fastmanservice.service.file.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskPhotoCompletionServiceImpl implements TaskPhotoCompletionService {
    private final ExecutionTaskRepository executionTaskRepository;
    private final TaskService taskService;
    private final ExecutionService executionService;
    private final ExecutionTaskMapper executionTaskMapper;
    private final FileService fileService;


    @Override
    public List<UpdateTaskPhotoCompletionResponse> update(Long id, List<UpdateTaskPhotoCompletionParam> updateTaskPhotoCompletionParams) {
        List<UpdateTaskPhotoCompletionResponse> resultList = new ArrayList<>();
        FastmanTask fastmanTask = taskService.findById(id);

        for (UpdateTaskPhotoCompletionParam param : updateTaskPhotoCompletionParams) {
            Execution execution = executionService.findById(param.getExecutionId());
            File photo = new File();
            photo.setFileContents(Base64.decodeBase64(param.getPhotoCompletion()));
            photo.setFileExtension(param.getPhotoExtension());
            String photoId = fileService.storeFile(photo);
            String photoUrl = fileService.getFileUrl(photoId, param.getPhotoExtension());

            ExecutionTask executionTask;
            if (param.getExecutionTaskId() == null) {
                executionTask = new ExecutionTask();
            } else {
                executionTask = executionTaskRepository.findById(param.getExecutionTaskId()).orElseThrow(
                    () -> new FastmanNotFoundException(String.format("Execution task с таким ID = %d не найден", param.getExecutionTaskId())));
            }

            executionTask.setFastmanTask(fastmanTask);
            executionTask.setExecution(execution);
            executionTask.setExecutionValue(photoUrl);
            executionTaskRepository.save(executionTask);
            resultList.add(executionTaskMapper.toUpdateTaskPhotoCompletionDto(executionTask));
        }
        return resultList;
    }
}