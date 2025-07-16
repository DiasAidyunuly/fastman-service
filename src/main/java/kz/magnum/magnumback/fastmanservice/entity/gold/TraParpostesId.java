package kz.magnum.magnumback.fastmanservice.entity.gold;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class TraParpostesId implements Serializable {
    private Integer tpartabl;
    private Integer tparpost;
    private Integer tparcmag;
    private String langue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TraParpostesId that = (TraParpostesId) o;
        return Objects.equals(tpartabl, that.tpartabl) &&
                Objects.equals(tparpost, that.tparpost) &&
                Objects.equals(tparcmag, that.tparcmag) &&
                Objects.equals(langue, that.langue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tpartabl, tparpost, tparcmag, langue);
    }
}
