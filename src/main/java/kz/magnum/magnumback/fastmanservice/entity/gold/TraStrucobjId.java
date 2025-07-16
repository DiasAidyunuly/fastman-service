package kz.magnum.magnumback.fastmanservice.entity.gold;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class TraStrucobjId implements Serializable {
    private Long tsobcint;
    private String language;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        TraStrucobjId that = (TraStrucobjId) object;
        return Objects.equals(tsobcint, that.tsobcint) && Objects.equals(language, that.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tsobcint, language);
    }
}