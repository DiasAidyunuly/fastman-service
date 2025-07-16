package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeOfExecutionDto {
    private Long id;
    private String nameOfType;
}
