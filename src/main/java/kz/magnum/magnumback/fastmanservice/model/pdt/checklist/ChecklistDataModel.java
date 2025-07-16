package kz.magnum.magnumback.fastmanservice.model.pdt.checklist;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistDataModel {
    private LocalDate date;
    private LocalTime time;
    private List<ChecklistModel> checklists;
}