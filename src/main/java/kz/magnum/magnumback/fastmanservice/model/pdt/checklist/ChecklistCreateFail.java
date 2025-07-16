package kz.magnum.magnumback.fastmanservice.model.pdt.checklist;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistCreateFail {
    private Integer responseCode;
    private String responseMessage;
    private List<Integer> sites;

}