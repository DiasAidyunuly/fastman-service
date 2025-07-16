package kz.magnum.magnumback.fastmanservice.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Site {
    private String codeSite;
    private String siteName;
}