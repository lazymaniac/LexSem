package pl.semantyk.enums;

public enum TimeUnit {

    MILISECOND("ms"),
    SECOND("sec"),
    MINUTE("min");

    private TimeUnit(String type) {
        this.type = type;
    }

    private final String type;

    public final String getValue() {
        return type;
    }

    public static TimeUnit enumOf(String text) {
        if (text != null) {
            for (TimeUnit b : TimeUnit.values()) {
                if (text.equalsIgnoreCase(b.getValue())) {
                    return b;
                }
            }
        }
        return null;
    }

}
