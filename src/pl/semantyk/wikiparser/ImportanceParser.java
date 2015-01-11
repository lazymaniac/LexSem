package pl.semantyk.wikiparser;

import pl.semantyk.domain.PartOfSpeech;
import pl.semantyk.domain.WikiUnit;
import pl.semantyk.domain.Importance;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static pl.semantyk.utils.StringUtils.*;

public class ImportanceParser implements WikiRawParser {

    private Set<String> nounTypes = new HashSet<>();

    @Override
    public void parse(WikiUnit tempUnit, List<String> importances) {
        List<PartOfSpeech> partsOfSpeech = new ArrayList<>(); // PARY : CZĘŚĆ MOWY - ZNACZENIA.

        List<Integer> partOfSpeechIdx = new ArrayList<>(); // INDESKY CZESCI MOWY.

        for (int i = 0; i < importances.size(); i++) { // ZNAJDŹ INDEKSY TYPÓW JEDNOSTEK.
            if (importances.get(i).startsWith("''")) {
                partOfSpeechIdx.add(i);
            }
        }
        partOfSpeechIdx.add(importances.size()); // DODAJ OSTATNI INDEKS ABY MOŻNA BYŁO WYZNACZYĆ PRZEDZIAŁ.

        int beg, end;

        for (int i = 0; i < partOfSpeechIdx.size() - 1; i++) {
            beg = partOfSpeechIdx.get(i);
            end = partOfSpeechIdx.get(i + 1);

            String type = importances.get(beg).replace("''", "");

            // POBIERA INFORMACJE O RODZAJACH RZECZOWNIKÓW (MĘSKOOSOBOWY, NIJAKI ITD.)
            if (importances.get(beg).contains("rzeczownik")) {
                nounTypes.add(importances.get(beg) + "\n");
            }

            List<String> tempImportances = new ArrayList<>();
            for (int j = beg + 1; j < end; j++) {
                String rawImportance = importances.get(j);
                String clean = removeReferences(removeApostrphes(removeNumeration(rawImportance)));
                tempImportances.add(clean.trim());
            }

            PartOfSpeech partOfSpeech = new PartOfSpeech(type, tempUnit.getId());
            List<Importance> importancesCont = new ArrayList<>();

            for (String znacz : tempImportances) {
                importancesCont.add(new Importance(znacz, partOfSpeech.getId()));
            }

            partOfSpeech.setImportances(importancesCont);

            partsOfSpeech.add(partOfSpeech);
        }

        tempUnit.setPartsOfSpeech(partsOfSpeech);
    }

    public Set<String> getNounTypes() {
        return nounTypes;
    }

    public void setNounTypes(Set<String> nounTypes) {
        this.nounTypes = nounTypes;
    }
}
