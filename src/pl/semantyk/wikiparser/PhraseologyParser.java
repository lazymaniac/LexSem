package pl.semantyk.wikiparser;

import pl.semantyk.domain.Phraseology;
import pl.semantyk.domain.WikiUnit;

import java.util.List;

import static pl.semantyk.utils.StringUtils.removeParenthesis;
import static pl.semantyk.utils.StringUtils.removeNumeration;
import static pl.semantyk.utils.StringUtils.removeReferences;

public class PhraseologyParser implements WikiRawParser {

    private final NumerationParser numerationParser = new NumerationParserImpl();

    @Override
    public void parse(WikiUnit tempUnit, List<String> data) {

        // USUŃ PUSTE FRAZEOLOGIE
        data.set(0, (data.get(0).replace("{{frazeologia}}", "")).trim());
        data.remove("");

        if (data != null && !data.isEmpty()) {

            for (String raw : data) {
                List<WikiNumeration> numeration = null;
                if (raw.contains("(") && raw.contains(")")) {
                    numeration = numerationParser.parse(tempUnit, raw);
                    raw = removeNumeration(raw);
                }

                String line = removeParenthesis(raw);

                String[] examples;
                // PODZIEL DANE PO ZNAKU SERPARACJI.
                if (line.contains(" • ")) {
                    examples = line.split(" • ");
                } else {
                    examples = new String[1];
                    examples[0] = line;
                }

                // USUN ZBEDNA SPCJE I ZNAKI.
                for (int i = 0; i < examples.length; i++) {
                    examples[i] = examples[i].replace(":", "").trim();
                }

                if (numeration != null && !numeration.isEmpty()) {
                    for (WikiNumeration num : numeration) {
                        for (String fraz : examples) {
                            tempUnit.addPhraseology(new Phraseology(num, removeReferences(fraz)));
                        }
                    }
                } else {
                    for (String fraz : examples) {
                        tempUnit.addPhraseology(new Phraseology(null, removeReferences(fraz)));
                    }
                }

            }
        }
    }
}
