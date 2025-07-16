package kz.magnum.magnumback.fastmanservice.service.file;

import kz.magnum.magnumback.fastmanservice.entity.file.FileEntity;
import kz.magnum.magnumback.fastmanservice.model.file.File;

import java.util.UUID;

public interface FileService {
    File getFile(UUID fileId);

    File getFile(FileEntity fileEntity);

    File getFileInfo(UUID fileId);

    String storeFile(File file);

    String getFileUrl(String fileId, String fileExtension);
}