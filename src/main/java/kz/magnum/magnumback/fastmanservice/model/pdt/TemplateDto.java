package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemplateDto {
    private Short id;
    private String templateName;
    private boolean manual;
}
