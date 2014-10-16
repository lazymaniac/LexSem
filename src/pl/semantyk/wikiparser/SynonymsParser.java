package pl.semantyk.wikiparser;

import pl.semantyk.domain.WikiUnit;
import pl.semantyk.domain.Synonym;

import java.util.List;

import static pl.semantyk.utils.StringUtils.removeReferences;

public class SynonymsParser implements WikiRawParser {

    private final NumerationParser numeracjaParser = new NumerationParserImpl();

    @Override
    public void parse(WikiUnit tempUnit, List<String> data) {

        // USUŃ PUSTE SYNONIMY
        data.set(0, (data.get(0).replace("{{synonimy}}", "")).trim());

        while (data.contains("")) {
            data.remove("");
        }

        if (!data.isEmpty()) {

            for (String raw : data) {
                List<WikiNumeration> numerations = numeracjaParser.parse(tempUnit, raw);

                String clean = removeReferences(raw);

                for (WikiNumeration num : numerations) {
                    tempUnit.addSynonym(new Synonym(num, clean));
                }
            }
        }
    }
}
