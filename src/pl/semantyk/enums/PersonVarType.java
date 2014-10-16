package pl.semantyk.enums;

public enum PersonVarType {

    /**
     * Odmiana w czasie terazniejszym.
     */
    CZAS_TERAZ("C_TERAZ"),

    /**
     * Odmiana meskoosobowa w czasie przeszlym.
     */
    CZAS_PRZESZ_M("C_PRZESZ_M"),

    /**
     * Odmiana zenskoosobowa w czasie przeszlym.
     */
    CZAS_PRZESZ_F("C_PRZESZ_F"),

    /**
     * Odmiana nijka w czasie przeszlym.
     */
    CZAS_PRZESZ_N("C_PRZESZ_N"),

    /**
     * Odmiana męskoosobowa czasu przyszłego.
     */
    CZAS_PRZYSZ_M("C_PRZYSZ_M"),

    /**
     * Odmiana żenśkoosobowa czasu przyszłego.
     */
    CZAS_PRZYSZ_F("C_PRZYSZ_F"),

    /**
     * Odminana nijaka czasy przyszłego.
     */
    CZAS_PRZYSZ_N("C_PRZYSZ_N"),

    /**
     * Odmiana trybu rozkazujacego.
     */
    TRYB_ROZKAZ("T_ROZKAZ"),

    /**
     * Odmiana meskoosobowa trybu przypuszczajacego.
     */
    TRYB_PRZYP_M("T_PRZYP_M"),

    /**
     * Odmiana zenskoosobowa trybu przypuszczajacego.
     */
    TRYB_PRZYP_F("T_PRZYP_F"),

    /**
     * Odmiana nijaka trybu przypuszczajacego.
     */
    TRYB_PRZYP_N("T_PRZYP_N"),

    /**
     * Odmiana meskoosobowa imieslowu przymiotnikowego czynnego.
     */
    IMIES_PRZYM_CZYNNY_M("I_PRZYM_CZYNNY_M"),

    /**
     * Odmiana zenskoosobowa imieslowu przymiotnikowego czynnego.
     */
    IMIES_PRZYM_CZYNNY_F("I_PRZYM_CZYNNY_F"),

    /**
     * Odmiana nijaka imieslowu przymiotnikowego czynnego.
     */
    IMIES_PRZYM_CZYNNY_N("I_PRZYM_CZYNNY_N"),

    /**
     * Odmiana meskoosobowa imieslowu przymiotnikowego biernego.
     */
    IMIES_PRZYM_BIERNY_M("I_PRZYM_BIERNY_M"),

    /**
     * Odmiana zenskoosobowa imieslowu przymiotnikowego biernego.
     */
    IMIES_PRZYM_BIERNY_F("I_PRZYM_BIERNY_F"),

    /**
     * Odmiana nijaka imieslowy przymiotnikowego biernego.
     */
    IMIES_PRZYM_BIERNY_N("I_PRZYM_BIERNY_N");

    private PersonVarType(String type) {
        this.type = type;
    }

    private final String type;

    public final String getValue() {
        return type;
    }

    public static PersonVarType enumOf(String text) {
        if (text != null) {
            for (PersonVarType b : PersonVarType.values()) {
                if (text.equalsIgnoreCase(b.getValue())) {
                    return b;
                }
            }
        }
        return null;
    }

}
