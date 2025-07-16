package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewSiteExceptionDto {
    private List<SiteWithCodeAndNameDto> sites;
    private List<IncidentNameDto> incidents;
}
