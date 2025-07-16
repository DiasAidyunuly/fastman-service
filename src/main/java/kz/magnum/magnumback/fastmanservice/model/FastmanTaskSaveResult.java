package kz.magnum.magnumback.fastmanservice.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FastmanTaskSaveResult {
    private Byte status;
    private String errMsg;
    private String body;
}
