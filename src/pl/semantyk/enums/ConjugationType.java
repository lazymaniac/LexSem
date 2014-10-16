package pl.semantyk.enums;

public enum ConjugationType {

    I("I"),
    II("II"),
    III("III"),
    IV("IV"),
    Va("Va"),
    Vb("Vb"),
    Vc("Vc"),
    VIa("VIa"),
    VIb("VIb"),
    VIIa("VIIa"),
    VIIb("VIIb"),
    VIIIa("VIIIa"),
    VIIIb("VIIIb"),
    IX("IX"),
    Xa("Xa"),
    Xb("Xb"),
    Xc("Xc"),
    XI("XI");

    private ConjugationType(String type) {
        this.type = type;
    }

    private final String type;

    public final String getValue() {
        return type;
    }

    public static ConjugationType enumOf(String text) {
        if (text != null) {
            for (ConjugationType b : ConjugationType.values()) {
                if (text.equalsIgnoreCase(b.getValue())) {
                    return b;
                }
            }
        }
        return null;
    }
}