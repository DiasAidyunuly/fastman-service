package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncidentDto {
    private Long id;
    private String incidentName;
    private Integer completeDay;
    private TemplateDto template;
    private ActionDto action;
    private Byte quarantine;
    private Integer qty_task;
    private Byte disable;
    private Byte mon;
    private Byte tue;
    private Byte wed;
    private Byte thu;
    private Byte fri;
    private Byte sat;
    private Byte sun;
    private Short priority;
}
