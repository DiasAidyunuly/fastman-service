package kz.magnum.magnumback.fastmanservice.model.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class File {
    private UUID fileId;
    private String fileNameWithoutExtension;
    private String fileExtension;
    private String directoryPath;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String fileHash;
    private byte[] fileContents;
}