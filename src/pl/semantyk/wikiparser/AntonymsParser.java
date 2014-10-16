package pl.semantyk.wikiparser;

import pl.semantyk.domain.Antonym;
import pl.semantyk.domain.WikiUnit;

import java.util.List;

import static pl.semantyk.utils.StringUtils.*;

public class AntonymsParser implements WikiRawParser {

    private final NumerationParser numerationParser = new NumerationParserImpl();

    @Override
    public void parse(WikiUnit tempUnit, List<String> data) {
        // USUŃ PUSTE ANTONIMY
        data.set(0, (data.get(0).replace("{{antonimy}}", "")).trim());

        while (data.contains("")) {
            data.remove("");
        }

        if (!data.isEmpty()) {
            // print(tempUnit.getNazwa() + " antonimy: " + data);

            for (String raw : data) {
                List<WikiNumeration> numerations = numerationParser.parse(tempUnit, raw);

                String clean = removeParenthesis(removeNumeration(removeReferences(raw))).trim();

                for (WikiNumeration num : numerations) {
                    tempUnit.addAntonym(new Antonym(num, clean));
                }
            }
            // print("Antonimy parsed: " + tempUnit.getAntonimy());
        }
    }
}
