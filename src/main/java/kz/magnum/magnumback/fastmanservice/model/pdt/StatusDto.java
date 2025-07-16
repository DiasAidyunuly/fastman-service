package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusDto {
    private Short id;
    private Integer statusCode;
    private String statusName;
}
