package kz.magnum.magnumback.fastmanservice.model;

public enum Constants {
    FASTMAN("fastman");

    private final String code;

    Constants(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
