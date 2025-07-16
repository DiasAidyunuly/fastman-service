package kz.magnum.magnumback.fastmanservice.model.pdt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FastmanKafkaReceipt {
    private Integer index;
    private String itemId;
    private Short locationId;
    private String supplierName;
    private Long incidentId;
    private Date date;
    private Double price;
    private Double stockQty;
    private String departmentCode;
    private String description;
    private String photoOriginal;
}