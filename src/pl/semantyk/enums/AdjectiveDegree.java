package pl.semantyk.enums;

/**
 * Enum representing gradation of adjective.
 *
 * @author Sebastian Fabisz.
 */
public enum AdjectiveDegree {

    ZERO("STOPIEŃ ZEROWY"),
    HIGHER("STOPIEŃ WYŻSZY"),
    HIGHEST("STOPIEŃ NAJWYŻSZY");

    private AdjectiveDegree(String text) {
        this.type = text;
    }

    private final String type;

    public final String getValue() {
        return type;
    }

    public static AdjectiveDegree enumOf(String text) {
        if (text != null) {
            for (AdjectiveDegree stopien : AdjectiveDegree.values()) {
                if (text.equalsIgnoreCase(stopien.getValue())) {
                    return stopien;
                }
            }
        }
        return null;
    }
}
