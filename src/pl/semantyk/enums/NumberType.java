package pl.semantyk.enums;

/**
 * Specify plural or singular.
 */
public enum NumberType {

    /**
     * Liczba pojedyncza.
     */
    SINGULAR("lp"),

    /**
     * Liczba mnoga.
     */
    PLURAL("lm");

    private NumberType(String type) {
        this.type = type;
    }

    private final String type;

    public final String getValue() {
        return type;
    }

    public static NumberType enumOf(String text) {
        if (text != null) {
            for (NumberType b : NumberType.values()) {
                if (text.equalsIgnoreCase(b.getValue())) {
                    return b;
                }
            }
        }
        return null;
    }

}
