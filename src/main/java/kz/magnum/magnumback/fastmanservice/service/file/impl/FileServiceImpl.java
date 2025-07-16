package kz.magnum.magnumback.fastmanservice.service.file.impl;

import com.google.common.io.ByteStreams;
import io.minio.*;
import io.minio.errors.MinioException;
import kz.magnum.magnumback.fastmanservice.entity.file.FileEntity;
import kz.magnum.magnumback.fastmanservice.model.file.File;
import kz.magnum.magnumback.fastmanservice.repository.file.FileRepository;
import kz.magnum.magnumback.fastmanservice.service.file.FileService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.kafka.common.errors.ApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.activation.MimetypesFileTypeMap;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    @Value("${minio.url}")
    private String url;
    @Value("${minio.access-key}")
    private String accessKey;
    @Value("${minio.secret-key}")
    private String secretKey;
    @Value("${minio.bucket-name}")
    private String bucket;
    private final FileRepository fileRepository;

    @Override
    public File getFile(UUID fileId) {
        val fileEntity = fileRepository.findById(fileId);
        return fileEntity.map(this::getFile)
            .orElse(null);
    }

    @Override
    public File getFile(FileEntity file) {
        try {
            MinioClient client = MinioClient.builder().endpoint(url).credentials(accessKey, secretKey).build();
            if (!client.bucketExists(BucketExistsArgs.builder().bucket(bucket).build())) {
                throw new IllegalArgumentException("Bucket not exists");
            }
            InputStream stream = client.getObject(GetObjectArgs.builder()
                .bucket(bucket)
                .object(file.getDirectoryPath() + "/" + getFullFileName(file.getFileId().toString(), file.getFileExtension()))
                .build());

            return File.builder()
                .fileId(file.getFileId())
                .fileNameWithoutExtension(getFileName(file))
                .fileContents(ByteStreams.toByteArray(stream))
                .directoryPath(file.getDirectoryPath())
                .build();
        } catch (MinioException | NoSuchAlgorithmException | IOException | InvalidKeyException e) {
            log.error(e.getMessage(), e);
            throw new ApiException(e.getMessage());
        }
    }

    @Override
    public File getFileInfo(UUID fileId) {
        val fileEntity = fileRepository.findById(fileId);
        if (fileEntity.isEmpty()) return null;
        val file = fileEntity.get();
        return File.builder()
            .fileId(file.getFileId())
            .fileNameWithoutExtension(getFileName(file))
            .build();
    }

    @SneakyThrows
    @Override
    public String storeFile(File file) {
        if (file == null || file.getFileContents() == null || file.getFileContents().length == 0) return null;
        val fileHash = hashFile(file.getFileContents());
        return fileRepository.findFirstByFileHash(fileHash).orElseGet(() -> storeFile(fileHash, file)).getFileId().toString();
    }

    @SneakyThrows
    public FileEntity storeFile(String fileHash, File file) {
        val fileId = UUID.randomUUID();
        val directoryPath = java.time.LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
        String fileName = fileId.toString();

        MinioClient client = MinioClient.builder().endpoint(url).credentials(accessKey, secretKey).build();
        if (!client.bucketExists(BucketExistsArgs.builder().bucket(bucket).build())) {
            client.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        }

        val fileEntity = fileRepository.save(FileEntity.builder()
            .fileId(fileId)
            .directoryPath(directoryPath)
            .fileExtension(file.getFileExtension())
            .fileNameWithoutExtension(fileName.length() > 128 ? fileName.substring(0, 127) : fileName)
            .fileHash(fileHash)
            .build());

        client.putObject(PutObjectArgs.builder()
            .bucket(bucket)
            .object(directoryPath + "/" + getFullFileName(fileName, file.getFileExtension()))
            .stream(new ByteArrayInputStream(file.getFileContents()), file.getFileContents().length, -1)
            .contentType(getMimeType(file))
            .build());

        return fileEntity;
    }

    private static String hashFile(byte[] bytes) {
        return org.apache.commons.codec.digest.DigestUtils.sha256Hex(bytes).toUpperCase();
    }

    private static String getFileName(FileEntity file) {
        val fileName = (StringUtils.hasText(file.getFileNameWithoutExtension()) ? file.getFileNameWithoutExtension() : file.getFileId().toString());
        return getFullFileName(fileName, file.getFileExtension());
    }

    private static String getFullFileName(String fileNameWithoutExtension, String fileExtension) {
        return fileNameWithoutExtension + fileExtension;
    }

    private static String getMimeType(File file) {
        MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
        return fileTypeMap.getContentType(getFullFileName(file.getFileNameWithoutExtension(), file.getFileExtension()));
    }

    @Override
    public String getFileUrl(String fileId, String fileExtension) {
        String directory = java.time.LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
        String fileName = getFullFileName(fileId, fileExtension);
        return String.format("%s/%s/%s/%s", url, bucket, directory, fileName);
    }
}