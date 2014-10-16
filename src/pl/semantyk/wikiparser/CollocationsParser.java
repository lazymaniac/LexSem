package pl.semantyk.wikiparser;

import pl.semantyk.domain.WikiUnit;
import pl.semantyk.domain.Collocation;

import java.util.List;

import static pl.semantyk.utils.StringUtils.removeNumeration;
import static pl.semantyk.utils.StringUtils.removeReferences;

public class CollocationsParser implements WikiRawParser {

    private final NumerationParser numerationParser = new NumerationParserImpl();

    @Override
    public void parse(WikiUnit tempUnit, List<String> data) {

        // USUŃ PUSTE KOLOKACJE
        data.set(0, (data.get(0).replace("{{kolokacje}}", "")).trim());
        data.remove("");

        if (data != null && !data.isEmpty()) {
            // print(tempUnit.getNazwa() + " kolokacje: " + data);

            for (String raw : data) {
                List<WikiNumeration> numeration = null;
                if (raw.contains("(") && raw.contains(")")) {
                    numeration = numerationParser.parse(tempUnit, raw);
                    raw = removeNumeration(raw);
                }

                String[] examples;
                // PODZIEL DANE PO ZNAKU SERPARACJI.
                if (raw.contains(" • ")) {
                    examples = raw.split(" • ");
                } else {
                    examples = new String[1];
                    examples[0] = raw;
                }

                // USUN ZBEDNA SPCJE I ZNAKI.
                for (int i = 0; i < examples.length; i++) {
                    examples[i] = examples[i].replace(":", "").trim();
                }

                if (numeration != null && !numeration.isEmpty()) {
                    for (WikiNumeration num : numeration) {
                        for (String col : examples) {
                            tempUnit.addCollocation(new Collocation(num, removeReferences(col)));
                        }
                    }
                } else {
                    for (String kol : examples) {
                        tempUnit.addCollocation(new Collocation(null, removeReferences(kol)));
                    }
                }

            }
        }
    }
}
