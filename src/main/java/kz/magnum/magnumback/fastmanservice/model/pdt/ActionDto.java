package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActionDto {
    private Short id;
    private String actionName;
    private Boolean completionWithCheck;
}
