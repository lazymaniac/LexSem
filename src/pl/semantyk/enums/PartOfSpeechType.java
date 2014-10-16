package pl.semantyk.enums;

public enum PartOfSpeechType {

    VERB("CZASOWNIK"),

    NOUN("RZECZOWNIK"),

    ADJECTIVE("PRZYMIOTNIK"),

    ADVERB("PRZYSŁÓWEK"),

    NUMERAL("LICZEBNIK"),

    PRONOUN("ZAIMEK");


    private PartOfSpeechType(String type) {
        this.type = type;
    }

    private final String type;

    public final String getValue() {
        return type;
    }

    public static PartOfSpeechType enumOf(String text) {
        if (text != null) {
            for (PartOfSpeechType b : PartOfSpeechType.values()) {
                if (text.equalsIgnoreCase(b.getValue())) {
                    return b;
                }
            }
        }
        return null;
    }
}
