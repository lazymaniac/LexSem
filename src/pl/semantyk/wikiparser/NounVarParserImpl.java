package pl.semantyk.wikiparser;

import pl.semantyk.domain.WikiUnit;
import pl.semantyk.domain.CasesVar;
import pl.semantyk.domain.NounVar;
import pl.semantyk.enums.NumberType;
import pl.semantyk.enums.CasesType;

import java.util.ArrayList;
import java.util.List;

import static pl.semantyk.utils.StringUtils.getStringAfterEqual;
import static pl.semantyk.utils.StringUtils.removeNumeration;
import static pl.semantyk.utils.StringUtils.removeReferences;

public class NounVarParserImpl<T> implements VarietyParser<T> {

    @SuppressWarnings("unchecked")
    @Override
    public T parse(WikiUnit tempUnit, List<String> data) {
        NounVar rzeczOdm = new NounVar();
        boolean prostaOdm = false;

        if (data.isEmpty()) { // SPRAWDŹ CZY JEST ODMIANA.
            return (T) rzeczOdm;
        }

        for (String s : data) { // SPRAWDŹ TYP SZABLONU.
            if (s.contains("Mianownik")) {
                prostaOdm = true;
                break;
            }
        }

        List<String> clean = new ArrayList<>();
        for (String s: data) {
            clean.add(removeReferences(s));
        }

        data = clean;

        for (String s : data) { // SPRAWDŹ CZY JEST NIEODMIENIALNY.
            if (s.contains("{{nieodm}}")) {
                rzeczOdm.setVarietyAble(false);
                return (T) rzeczOdm;
            }
        }

        if (prostaOdm) { // WYLISTOWANA ODMIANA.
            CasesVar lp = parsujProstaOdmiane(rzeczOdm, data, NumberType.SINGULAR);
            lp.setTyp(CasesType.NOUN_SING);
            CasesVar lm = parsujProstaOdmiane(rzeczOdm, data, NumberType.PLURAL);
            lm.setTyp(CasesType.NOUN_PLUR);
            rzeczOdm.addCasesVar(lp);
            rzeczOdm.addCasesVar(lm);

        } else { // ODMIANA Z KOŃCÓWKAMI
            boolean brakLP = false;
            boolean brakLM = false;

            for (String s : data) {

                // USUŃ NUMERACJĘ I ODNOŚNIKI
                String line = removeNumeration(removeReferences(s));

                if (line.startsWith(":")) {
                    String[] splited;
                    // ODDIEL LICZBĘ MNOGĄ OD POJEDYNCZEJ.
                    if (line.contains(";")) {
                        splited = line.split(";");
                    } else if (line.contains("/")) {
                        splited = line.split("/");
                    } else {
                        splited = new String[1];
                        splited[0] = line;
                    }

                    String liczbaPoj = "";
                    String liczbaMn = "";
                    if (splited.length > 0) {
                        liczbaPoj = splited[0];
                    }
                    if (splited.length > 1) {
                        liczbaMn = splited[1];
                    }

                    String[] liczbaPojSplited = liczbaPoj.split("\\s");
                    String[] liczbaMnSplited = liczbaMn.split("\\s");

                    int begLP = -1;
                    int begLM = -1;

                    for (int i = 0; i < liczbaPojSplited.length; i++) {
                        if (liczbaPojSplited[i].contains("{{przypadki}}")) { // WYZANCZ INDEKS POCZATKOWY ODMIANY LICZBY
                            // POJEDYNCZEJ.
                            begLP = i;
                            break;
                        }
                        if (liczbaPojSplited[i].contains("{{blp}}")) { // BRAK ODMIANY LICZBY POJEDYCZNEJ.
                            brakLP = true;
                            rzeczOdm.setNoSingular(brakLP);
                            break;
                        }
                    }

                    for (int i = 0; i < liczbaMnSplited.length; i++) {
                        if (liczbaMnSplited[i].contains("{{przypadki}}")) { // WYZNACZ INDEKS POCZĄTKOWY ODMIANY LICZBY
                            // MNOGIEJ.
                            begLM = i;
                            break;
                        }
                        if (liczbaMnSplited[i].contains("{{blm}}")) { // BRAK ODMIANY LICZBY MNOGIEJ.
                            brakLM = true;
                            rzeczOdm.setNoPlural(brakLM);
                            break;
                        }
                    }

                    if (!brakLP) {
                        CasesVar lp = parsujOdmianeZKoncowkami(rzeczOdm, liczbaMnSplited, begLP, NumberType.SINGULAR);
                        lp.setTyp(CasesType.NOUN_SING);
                        rzeczOdm.addCasesVar(lp);
                    }

                    if (!brakLM) {
                        CasesVar lm = parsujOdmianeZKoncowkami(rzeczOdm, liczbaMnSplited, begLM, NumberType.PLURAL);
                        lm.setTyp(CasesType.NOUN_PLUR);
                        rzeczOdm.addCasesVar(lm);
                    }
                }
            }
        }

        // print ("Rzeczownik odmiana: " + rzeczOdm.toString());
        return (T) rzeczOdm;
    }

    private CasesVar parsujProstaOdmiane(NounVar rzeczOdm, List<String> data, NumberType liczba) {
        CasesVar przypadki = new CasesVar();

        przypadki.setNounVar(rzeczOdm);

        for (String s : data) {
            if (s.startsWith("|Mianownik " + liczba.getValue())) {
                if (getStringAfterEqual(s).equals("")) { // sparwdz czy nie ma liczby pojedynczej
                    if (liczba.equals(NumberType.SINGULAR)) {
                        rzeczOdm.setNoSingular(true);
                    }
                    if (liczba.equals(NumberType.PLURAL)) {
                        rzeczOdm.setNoPlural(true);
                    }
                }
                przypadki.setMianownik(getStringAfterEqual(s));
            }
            if (s.startsWith("|Dopełniacz " + liczba.getValue())) {
                przypadki.setDopelniacz(removeReferences(getStringAfterEqual(s)));
            }
            if (s.startsWith("|Celownik " + liczba.getValue())) {
                przypadki.setCelownik(removeReferences(getStringAfterEqual(s)));
            }
            if (s.startsWith("|Biernik " + liczba.getValue())) {
                przypadki.setBiernik(removeReferences(getStringAfterEqual(s)));
            }
            if (s.startsWith("|Narzędnik " + liczba.getValue())) {
                przypadki.setNarzednik(removeReferences(getStringAfterEqual(s)));
            }
            if (s.startsWith("|Miejscownik " + liczba.getValue())) {
                przypadki.setMiejscownik(removeReferences(getStringAfterEqual(s)));
            }
            if (s.startsWith("|Wołacz " + liczba.getValue())) {
                przypadki.setWolacz(removeReferences(getStringAfterEqual(s)));
            }
        }
        return przypadki;
    }

    private CasesVar parsujOdmianeZKoncowkami(NounVar rzeczOdm, String[] data, int begIdx, NumberType liczba) {

        CasesVar przypadki = new CasesVar();

        przypadki.setNounVar(rzeczOdm);

        String temat = "";
        if (data[begIdx + 1].equals("jak")) {
            return przypadki; // TODO kopiowanie odmiany w przypadku gdy jest ona odnosnikiem do innego wiersza.
        }

        if (liczba.equals(NumberType.SINGULAR)) {
            // MIANOWNIK I TEMAT
            if (data.length > (begIdx + 1) && data[begIdx + 1].contains("|")) {
                String[] temp = data[begIdx + 1].split("\\|");
                temat = temp[0];
                przypadki.setMianownik(removeReferences(temat + temp[1])); // mianownik temat + koncowka po |
                rzeczOdm.setTopic(temat);
            } else if (data.length > (begIdx + 1)) {
                temat = data[begIdx + 1];
                rzeczOdm.setTopic(temat);
            }
        }

        if (liczba.equals(NumberType.PLURAL)) {
            // MIANOWNIK I TEMAT
            if (data[begIdx + 1].contains("|")) {
                String[] temp = data[begIdx + 1].split("\\|");
                temat = temp[0];
                przypadki.setMianownik(removeReferences(temat + temp[1]));
            }
            // MIANOWNIK I TEMAT
            if (data[begIdx + 1].contains("~")) {
                temat = rzeczOdm.getTopic();
                przypadki.setMianownik(removeReferences(temat + data[begIdx + 1].replace("~", "")));
            }
        }

        // DOPEŁNIACZ
        String koncowka;
        if (data.length > (begIdx + 2) && data[begIdx + 2].contains("~")) {
            koncowka = data[begIdx + 2].replace("~", "");
            przypadki.setDopelniacz(temat + koncowka);
        } else if (data.length > (begIdx + 2)) {
            przypadki.setDopelniacz(data[begIdx + 2]);
        }

        // CELOWNIK
        koncowka = "";
        if (data.length > (begIdx + 3) && data[begIdx + 3].contains("~")) {
            koncowka = data[begIdx + 3].replace("~", "");
            przypadki.setCelownik(removeReferences(temat + koncowka));
        } else if (data.length > (begIdx + 3)) {
            przypadki.setCelownik(removeReferences(data[begIdx + 3]));
        }

        // BIERNIK
        koncowka = "";
        if (data.length > (begIdx + 4) && data[begIdx + 4].contains("~")) {
            koncowka = data[begIdx + 4].replace("~", "");
            przypadki.setBiernik(removeReferences(temat + koncowka));
        } else if (data.length > (begIdx + 4)) {
            przypadki.setBiernik(removeReferences(data[begIdx + 4]));
        }

        // NARZĘDNIK
        koncowka = "";
        if (data.length > (begIdx + 5) && data[begIdx + 5].contains("~")) {
            koncowka = data[begIdx + 5].replace("~", "");
            przypadki.setNarzednik(removeReferences(temat + koncowka));
        } else if (data.length > (begIdx + 5)) {
            przypadki.setNarzednik(removeReferences(data[begIdx + 5]));
        }

        // MIEJSCOWNIK
        koncowka = "";
        if (data.length > (begIdx + 6) && data[begIdx + 6].contains("~")) {
            koncowka = data[begIdx + 6].replace("~", "");
            przypadki.setMiejscownik(removeReferences(temat + koncowka));
        } else if (data.length > (begIdx + 6)) {
            przypadki.setMiejscownik(removeReferences(data[begIdx + 6]));
        }

        // WOŁACZ
        koncowka = "";
        if (data.length > (begIdx + 7) && data[begIdx + 7].contains("~")) {
            koncowka = data[begIdx + 7].replace("~", "");
            przypadki.setWolacz(removeReferences(temat + koncowka));
        } else if (data.length > (begIdx + 7)) {
            przypadki.setWolacz(removeReferences(data[begIdx + 7]));
        }

        return przypadki;

    }

}
