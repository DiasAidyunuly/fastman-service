package kz.magnum.magnumback.fastmanservice.entity.fastman.dwh;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "items", schema = "fastman")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", nullable = false)
    private Long id;
    @Column(name = "item_name")
    private String itemName;
    @ManyToOne
    @JoinColumn(name = "level_id_0", referencedColumnName = "level_id")
    private Level levelCode0;
    @ManyToOne
    @JoinColumn(name = "level_id_1", referencedColumnName = "level_id")
    private Level levelCode1;
    @ManyToOne
    @JoinColumn(name = "level_id_2", referencedColumnName = "level_id")
    private Level levelCode2;
    @ManyToOne
    @JoinColumn(name = "level_id_3", referencedColumnName = "level_id")
    private Level levelCode3;
    @Column(name = "unit_meas_name")
    private String unitMeasName;
    @Column(name = "change_date")
    private LocalDateTime changeDate;
    @JsonIgnore
    @OneToMany(mappedBy = "item")
    private List<Supplier> suppliers;
    @JsonIgnore
    @OneToMany(mappedBy = "item")
    private List<ItemProperty> itemProperties;
    @JsonIgnore
    @OneToMany(mappedBy = "item")
    private List<Barcode> barcodes;
}
