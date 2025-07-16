package kz.magnum.magnumback.fastmanservice.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SiteWithCodeAndName {
    private Integer siteCode;
    private String siteName;
}
