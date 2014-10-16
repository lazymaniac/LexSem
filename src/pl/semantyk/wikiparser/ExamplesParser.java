package pl.semantyk.wikiparser;

import pl.semantyk.domain.WikiUnit;
import pl.semantyk.domain.Example;

import java.util.List;
import java.util.ListIterator;

import static pl.semantyk.utils.StringUtils.removeNumeration;
import static pl.semantyk.utils.StringUtils.removeReferences;

public class ExamplesParser implements WikiRawParser {

    private NumerationParser numerationsParser = new NumerationParserImpl();

    @Override
    public void parse(WikiUnit tempUnit, List<String> data) {

        //USUŃ PUSTE PRZYKŁADY
        data.set(0, (data.get(0).replace("{{przykłady}}", "")).trim());
        if (data.get(1).startsWith(":") && data.get(1).trim().endsWith(")")) {
            String temp = removeNumeration(data.get(1).trim());
            data.set(1, temp);
        }

        ListIterator<String> iter = data.listIterator();
        while (iter.hasNext()) {
            if (iter.next().equals("")) {
                iter.remove();
            }
        }

        if (!data.isEmpty()) {

            for (String raw : data) {
                List<WikiNumeration> numerations;

                numerations = numerationsParser.parse(tempUnit, raw);

                String clean = removeReferences(removeNumeration(raw));

                for (WikiNumeration num : numerations) {
                    tempUnit.addExample(new Example(num, clean.trim()));
                }
            }
        }
    }
}
