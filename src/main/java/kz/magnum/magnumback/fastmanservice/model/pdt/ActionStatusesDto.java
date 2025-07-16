package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActionStatusesDto {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ActionModel {
        private Short actionId;
        private StatusModel status;

        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class StatusModel {
            private Short statusId;
            private Integer itemsCount;
        }
    }

    @Builder.Default
    private List<ActionModel> actionsList = new ArrayList<>();
}