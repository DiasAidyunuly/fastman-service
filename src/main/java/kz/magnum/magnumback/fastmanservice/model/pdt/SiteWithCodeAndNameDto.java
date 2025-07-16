package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SiteWithCodeAndNameDto {
    private Integer siteCode;
    private String siteName;
}
