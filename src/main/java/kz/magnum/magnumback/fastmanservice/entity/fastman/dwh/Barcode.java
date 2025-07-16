package kz.magnum.magnumback.fastmanservice.entity.fastman.dwh;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "barcodes", schema = "fastman")
public class Barcode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "barcode_id", nullable = false)
    private Long id;
    @Column(name = "change_date")
    private LocalDateTime changeDate;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
