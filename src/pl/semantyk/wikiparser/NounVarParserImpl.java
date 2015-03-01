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
        NounVar nounVar = new NounVar();
        boolean simpleVar = false;

        if (data.isEmpty()) { // SPRAWDŹ CZY JEST ODMIANA.
            return (T) nounVar;
        }

        for (String s : data) { // SPRAWDŹ TYP SZABLONU.
            if (s.contains("Mianownik")) {
                simpleVar = true;
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
                nounVar.setVarietyAble(false);
                return (T) nounVar;
            }
        }

        if (simpleVar) { // WYLISTOWANA ODMIANA.
            CasesVar lp = parseSimpleVar(nounVar, data, NumberType.SINGULAR);
            lp.setTyp(CasesType.NOUN_SING);
            CasesVar lm = parseSimpleVar(nounVar, data, NumberType.PLURAL);
            lm.setTyp(CasesType.NOUN_PLUR);
            nounVar.addCasesVar(lp);
            nounVar.addCasesVar(lm);

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

                    String singular = "";
                    String plural = "";
                    if (splited.length > 0) {
                        singular = splited[0];
                    }
                    if (splited.length > 1) {
                        plural = splited[1];
                    }

                    String[] singularSplited = singular.split("\\s");
                    String[] pluralSplited = plural.split("\\s");

                    int begLP = -1;
                    int begLM = -1;

                    for (int i = 0; i < singularSplited.length; i++) {
                        if (singularSplited[i].contains("{{przypadki}}")) { // WYZANCZ INDEKS POCZATKOWY ODMIANY LICZBY
                            // POJEDYNCZEJ.
                            begLP = i;
                            break;
                        }
                        if (singularSplited[i].contains("{{blp}}")) { // BRAK ODMIANY LICZBY POJEDYCZNEJ.
                            brakLP = true;
                            nounVar.setNoSingular(brakLP);
                            break;
                        }
                    }

                    for (int i = 0; i < pluralSplited.length; i++) {
                        if (pluralSplited[i].contains("{{przypadki}}")) { // WYZNACZ INDEKS POCZĄTKOWY ODMIANY LICZBY
                            // MNOGIEJ.
                            begLM = i;
                            break;
                        }
                        if (pluralSplited[i].contains("{{blm}}")) { // BRAK ODMIANY LICZBY MNOGIEJ.
                            brakLM = true;
                            nounVar.setNoPlural(brakLM);
                            break;
                        }
                    }

                    if (!brakLP) {
                        CasesVar lp = parseVarWithPostfix(nounVar, pluralSplited, begLP, NumberType.SINGULAR);
                        lp.setTyp(CasesType.NOUN_SING);
                        nounVar.addCasesVar(lp);
                    }

                    if (!brakLM) {
                        CasesVar lm = parseVarWithPostfix(nounVar, pluralSplited, begLM, NumberType.PLURAL);
                        lm.setTyp(CasesType.NOUN_PLUR);
                        nounVar.addCasesVar(lm);
                    }
                }
            }
        }

        // print ("Rzeczownik odmiana: " + nounVar.toString());
        return (T) nounVar;
    }

    private CasesVar parseSimpleVar(NounVar nounVar, List<String> data, NumberType numberType) {
        CasesVar casesVar = new CasesVar();

        casesVar.setNounVar(nounVar.getId());

        for (String s : data) {
            if (s.startsWith("|Mianownik " + numberType.getValue())) {
                if (getStringAfterEqual(s).equals("")) { // sparwdz czy nie ma liczby pojedynczej
                    if (numberType.equals(NumberType.SINGULAR)) {
                        nounVar.setNoSingular(true);
                    }
                    if (numberType.equals(NumberType.PLURAL)) {
                        nounVar.setNoPlural(true);
                    }
                }
                casesVar.setMianownik(getStringAfterEqual(s));
            }
            if (s.startsWith("|Dopełniacz " + numberType.getValue())) {
                casesVar.setDopelniacz(removeReferences(getStringAfterEqual(s)));
            }
            if (s.startsWith("|Celownik " + numberType.getValue())) {
                casesVar.setCelownik(removeReferences(getStringAfterEqual(s)));
            }
            if (s.startsWith("|Biernik " + numberType.getValue())) {
                casesVar.setBiernik(removeReferences(getStringAfterEqual(s)));
            }
            if (s.startsWith("|Narzędnik " + numberType.getValue())) {
                casesVar.setNarzednik(removeReferences(getStringAfterEqual(s)));
            }
            if (s.startsWith("|Miejscownik " + numberType.getValue())) {
                casesVar.setMiejscownik(removeReferences(getStringAfterEqual(s)));
            }
            if (s.startsWith("|Wołacz " + numberType.getValue())) {
                casesVar.setWolacz(removeReferences(getStringAfterEqual(s)));
            }
        }
        return casesVar;
    }

    private CasesVar parseVarWithPostfix(NounVar nounVar, String[] data, int begIdx, NumberType numberType) {

        CasesVar casesVar = new CasesVar();

        casesVar.setNounVar(nounVar.getId());

        String topic = "";
        if (data[begIdx + 1].equals("jak")) {
            return casesVar; // TODO kopiowanie odmiany w przypadku gdy jest ona odnosnikiem do innego wiersza.
        }

        if (numberType.equals(NumberType.SINGULAR)) {
            // MIANOWNIK I TEMAT
            if (data.length > (begIdx + 1) && data[begIdx + 1].contains("|")) {
                String[] temp = data[begIdx + 1].split("\\|");
                topic = temp[0];
                casesVar.setMianownik(removeReferences(topic + temp[1])); // mianownik topic + koncowka po |
                nounVar.setTopic(topic);
            } else if (data.length > (begIdx + 1)) {
                topic = data[begIdx + 1];
                nounVar.setTopic(topic);
            }
        }

        if (numberType.equals(NumberType.PLURAL)) {
            // MIANOWNIK I TEMAT
            if (data[begIdx + 1].contains("|")) {
                String[] temp = data[begIdx + 1].split("\\|");
                topic = temp[0];
                casesVar.setMianownik(removeReferences(topic + temp[1]));
            }
            // MIANOWNIK I TEMAT
            if (data[begIdx + 1].contains("~")) {
                topic = nounVar.getTopic();
                casesVar.setMianownik(removeReferences(topic + data[begIdx + 1].replace("~", "")));
            }
        }

        // DOPEŁNIACZ
        String koncowka;
        if (data.length > (begIdx + 2) && data[begIdx + 2].contains("~")) {
            koncowka = data[begIdx + 2].replace("~", "");
            casesVar.setDopelniacz(topic + koncowka);
        } else if (data.length > (begIdx + 2)) {
            casesVar.setDopelniacz(data[begIdx + 2]);
        }

        // CELOWNIK
        koncowka = "";
        if (data.length > (begIdx + 3) && data[begIdx + 3].contains("~")) {
            koncowka = data[begIdx + 3].replace("~", "");
            casesVar.setCelownik(removeReferences(topic + koncowka));
        } else if (data.length > (begIdx + 3)) {
            casesVar.setCelownik(removeReferences(data[begIdx + 3]));
        }

        // BIERNIK
        koncowka = "";
        if (data.length > (begIdx + 4) && data[begIdx + 4].contains("~")) {
            koncowka = data[begIdx + 4].replace("~", "");
            casesVar.setBiernik(removeReferences(topic + koncowka));
        } else if (data.length > (begIdx + 4)) {
            casesVar.setBiernik(removeReferences(data[begIdx + 4]));
        }

        // NARZĘDNIK
        koncowka = "";
        if (data.length > (begIdx + 5) && data[begIdx + 5].contains("~")) {
            koncowka = data[begIdx + 5].replace("~", "");
            casesVar.setNarzednik(removeReferences(topic + koncowka));
        } else if (data.length > (begIdx + 5)) {
            casesVar.setNarzednik(removeReferences(data[begIdx + 5]));
        }

        // MIEJSCOWNIK
        koncowka = "";
        if (data.length > (begIdx + 6) && data[begIdx + 6].contains("~")) {
            koncowka = data[begIdx + 6].replace("~", "");
            casesVar.setMiejscownik(removeReferences(topic + koncowka));
        } else if (data.length > (begIdx + 6)) {
            casesVar.setMiejscownik(removeReferences(data[begIdx + 6]));
        }

        // WOŁACZ
        koncowka = "";
        if (data.length > (begIdx + 7) && data[begIdx + 7].contains("~")) {
            koncowka = data[begIdx + 7].replace("~", "");
            casesVar.setWolacz(removeReferences(topic + koncowka));
        } else if (data.length > (begIdx + 7)) {
            casesVar.setWolacz(removeReferences(data[begIdx + 7]));
        }

        return casesVar;

    }

}
