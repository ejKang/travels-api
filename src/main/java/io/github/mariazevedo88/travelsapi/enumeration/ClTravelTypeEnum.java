package io.github.mariazevedo88.travelsapi.enumeration;

public enum ClTravelTypeEnum {
    
    RETURN("RETURN"), ONE_WAY("ONE-WAY"), MULTI_CITY("MULTI-CITY");

    private String value;

    private ClTravelTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ClTravelTypeEnum getEnum(String value) {
        for (ClTravelTypeEnum t : values()) {
            if (value.equals(t.getValue())) {
                return t;
            }
        }

        throw new RuntimeException("Type not found ! ");
    }
}
