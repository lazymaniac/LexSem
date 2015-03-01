package pl.semantyk.enums;

/**

 *         <p/>
 *         Źródło wikipedia.pl
 *         Rzeczownik w liczbie pojedynczej występuje w rodzaju męskim, żeńskim lub nijakim.
 *         Rzeczowniki rodzaju męskiego najczęściej zakończone są na spółgłoskę (np.: słoń, but, stolarz), niekiedy na –o (np.: Henio,
 *         wujcio) lub –a (np.: wyga, mężczyzna, monarcha), niekiedy na –y (np.: myśliwy, dzielnicowy). Rzeczowniki w rodzaju męskim można podzielić na:
 *         <p/>
 *         męskoosobowe – to rzeczowniki żywotne osobowe, mają biernik liczby pojedynczej i biernik liczby mnogiej taki sam jak dopełniacz, np.: D. nie ma mądrego filozofa, B. widzę mądrego filozofa; D. nie ma mądrych filozofów, B. widzę mądrych filozofów;
 *         męskożywotne (inaczej męskozwierzęce) – to rzeczowniki żywotne nieosobowe, mają w liczbie pojedynczej biernik identyczny jak dopełniacz, a w liczbie mnogiej taki jak mianownik, np.: D. nie ma upartego osła, B. widzę upartego osła; M. te uparte osły, B. widzę uparte osły;
 *         męskonieżywotne (inaczej męskorzeczowe) – to rzeczowniki nieżywotne, mają w liczbie pojedynczej i w liczbie mnogiej biernik taki sam jak mianownik, np.: M. ten nieosiągalny cel, B. widzę nieosiągalny cel; M. te nieosiągalne cele, B. widzę nieosiągalne cele;
 *         <p/>
 *         Rzeczowniki rodzaju żeńskiego kończą się najczęściej na samogłoskę –a (np.: praca, pestka, żyrafa) lub –i (np.: monarchini, wychowawczyni), niekiedy także na spółgłoskę (np.: krew, płeć, mysz);
 *         Rzeczowniki rodzaju nijakiego kończą się najczęściej na –o (np.: oko, niebo), –e (np.: powietrze, zakole), –ę (np.: prosię).
 *         <p/>
 *         W liczbie mnogiej rzeczownik można przyporządkować do jednego z dwóch rodzajów gramatycznych:
 *         męskoosobowego – przybierają go te rzeczowniki osobowe, które w liczbie pojedynczej są rodzaju męskiego. W mianowniku liczby mnogiej łączą się z zaimkiem ci (np.: ci mądrzy filozofowie, ci znudzeni kochankowie). Mają one taką sama formę w bierniku i dopełniaczu, np.: D. nie ma mądrych filozofów, B. widzę mądrych filozofów.
 *         niemęskoosobowego – tu zaliczają się wszystkie pozostałe rzeczowniki, w mianowniku liczby mnogiej łączą się one z zaimkiem te (np.: te stracone złudzenia, te czarne charaktery, te nieukończone partytury). Rzeczowniki te maja taką samą formę w mianowniku i bierniku, np.: M. te nieukończone partytury, B. widzę nieukończone partytury[2].
 *
 * @author Sebastian Fabisz
 */
public enum NounGender {

    MESKI ("meski"),
    MESKOOSOBOWY ("meskoosobowy"),
    MESKOZYWOTNY ("meskozywotny"),
    MESKONIEZYWOTNY ("meskoniezywotny"),
    ZENSKI ("zenski"),
    NIJAKI ("nijaki"),
    NIEMESKOOSBOWY ("niemeskoosobowy"),
    EMPTY ("empty");

    NounGender (String type) {
        this.type = type;
    }

    private final String type;

    public final String getValue() {
        return type;
    }

    public static NounGender enumOf(String text) {
        if (text != null) {
            for (NounGender val : NounGender.values()) {
                if (text.equalsIgnoreCase(val.getValue())) {
                    return val;
                }
            }
        }
        return null;
    }
}
