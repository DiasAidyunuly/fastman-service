package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IncidentStatusesDto {
    @Data
    public static class IncidentModel {
        private Long incidentId;
        private StatusModel status;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class StatusModel {
            private Short statusId;
            private Integer itemsCount;
        }

        public IncidentModel(Long incidentId, StatusModel status) {
            this.incidentId = incidentId;
            this.status = status;
        }
    }

    @Builder.Default
    private List<IncidentModel> incidentsList = new ArrayList<>();
}