package kz.magnum.magnumback.fastmanservice.model.enums;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum StructureLevelEnum {
    DIRECTION((short) 2),
    DEPARTMENT((short) 3),
    GROUP((short) 4),
    SUBGROUP((short) 5);

    private final Short level;

    public static StructureLevelEnum fromLevel(int level) {
        return Arrays.stream(values())
            .filter(e -> e.level == level)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Unknown structure level: " + level));
    }
}