package kz.magnum.magnumback.fastmanservice.model;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewSiteException {
    private List<SiteWithCodeAndName> sites;
    private List<IncidentName> incidents;
}
