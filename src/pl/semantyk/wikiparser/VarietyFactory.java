package pl.semantyk.wikiparser;

import java.util.ArrayList;
import java.util.List;

import pl.semantyk.domain.AdjectiveVar;
import pl.semantyk.domain.AdverbVar;
import pl.semantyk.domain.NounVar;
import pl.semantyk.domain.NumeralVar;
import pl.semantyk.domain.VerbVar;
import pl.semantyk.domain.WikiUnit;

public class VarietyFactory implements WikiRawParser {

    private final NumerationParser numerationParser = new NumerationParserImpl();
    private final VerbVarParserImpl<VerbVar> verbVarParser = new VerbVarParserImpl<>();
    private final NounVarParserImpl<NounVar> nounVarParser = new NounVarParserImpl<>();
    private final AdjectiveVarParserImpl<AdjectiveVar> adjectiveVarParser = new AdjectiveVarParserImpl<>();
    private final AdverbVarGenerator adverbVarParser = new AdverbVarGenerator();
    private final NumeralVarParserImpl<NumeralVar> numeralVarParser = new NumeralVarParserImpl<>();

    @Override
    public void parse(final WikiUnit tempUnit, final List<String> variety) {
        List<Integer> idxPartOfSpeech = new ArrayList<>();

        // ZNAJDŹ INDEKSY CZEŚCI MOWY
        for (int i = 0; i < variety.size(); i++) {
            if (variety.get(i).startsWith(":")) {
                idxPartOfSpeech.add(i);
            }
        }
        idxPartOfSpeech.add(variety.size());

        for (int i = 0; i < (idxPartOfSpeech.size() - 1); i++) {
            // PARSUJ NUMERACJE
            List<WikiNumeration> numeration = numerationParser.parse(tempUnit, variety.get(idxPartOfSpeech.get(i)));
            // WYDZIEL BLOK Z ODMIANĄ ZNACZENIA DANEGO SŁOWA.
            List<String> varietyBlock = new ArrayList<>(variety.subList(idxPartOfSpeech.get(i), idxPartOfSpeech.get(i + 1)));

            if (variety.get(idxPartOfSpeech.get(i)).contains("rzeczownik") || ((tempUnit.getPartsOfSpeech().size() < 2) && tempUnit.getPartsOfSpeech().get(0).getPartOfSpeech().contains("rzeczownik"))) {
                NounVar nounVar = nounVarParser.parse(tempUnit, varietyBlock);
                if (!numeration.isEmpty() && (nounVar != null)) {
                    for (WikiNumeration num : numeration) {
                        nounVar.setNumeration(num);
                        tempUnit.addNounVar(new NounVar(nounVar));
                    }
                }
            }

            if (variety.get(idxPartOfSpeech.get(i)).contains("czasownik") || variety.get(idxPartOfSpeech.get(i) - 1).contains("czasownik")
                    || (((tempUnit.getPartsOfSpeech().size() < 2) && tempUnit.getPartsOfSpeech().get(0).getPartOfSpeech().contains("czasownik")))) {

                VerbVar verbVar = verbVarParser.parse(tempUnit, varietyBlock);
                if (!numeration.isEmpty() && (verbVar != null)) {
                    for (WikiNumeration num : numeration) {
                        verbVar.setNumeration(num);
                        tempUnit.addVerbVar(new VerbVar(verbVar));
                    }
                }
            }

            if (variety.get(idxPartOfSpeech.get(i)).contains("przymiotnik")
                    || ((tempUnit.getPartsOfSpeech().size() < 2) && tempUnit.getPartsOfSpeech().get(0).getPartOfSpeech().contains("przymiotnik"))) {

                AdjectiveVar adjVar = adjectiveVarParser.parse(tempUnit, varietyBlock);
                if (!numeration.isEmpty() && (adjVar != null)) {
                    for (WikiNumeration num : numeration) {
                        adjVar.setNumeration(num);
                        tempUnit.addAdjectiveVar(new AdjectiveVar(adjVar));
                    }
                }
            }

            if (variety.get(idxPartOfSpeech.get(i)).contains("przysłówek") || ((tempUnit.getPartsOfSpeech().size() < 2) && tempUnit.getPartsOfSpeech().get(0).getPartOfSpeech().contains("przysłówek"))) {

                AdverbVar adverbVar = adverbVarParser.generate(tempUnit);
                if (!numeration.isEmpty() && (adverbVar != null)) {
                    for (WikiNumeration num : numeration) {
                        adverbVar.setNumeration(num);
                        tempUnit.addAdverbVar(new AdverbVar(adverbVar));
                    }
                }
            }

            if (variety.get(idxPartOfSpeech.get(i)).contains("liczebnik") || ((tempUnit.getPartsOfSpeech().size() < 2) && tempUnit.getPartsOfSpeech().get(0).getPartOfSpeech().contains("liczebnik"))) {

                NumeralVar numeralVar = numeralVarParser.parse(tempUnit, varietyBlock);
                if (!numeration.isEmpty() && (numeralVar != null)) {
                    for (WikiNumeration num : numeration) {
                        numeralVar.setNumeration(num);
                        tempUnit.addNumeralVar(new NumeralVar(numeralVar));
                    }
                }
            }
        }
    }
}
