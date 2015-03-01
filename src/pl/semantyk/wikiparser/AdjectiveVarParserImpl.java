package pl.semantyk.wikiparser;

import pl.semantyk.domain.WikiUnit;
import pl.semantyk.domain.AdjectiveVar;

import java.util.List;

import static pl.semantyk.utils.StringUtils.removeParenthesis;
import static pl.semantyk.utils.StringUtils.removeNumeration;

/**
 * Parses variety of adjective using generator.
 * Examples:
 * [: (1.1)
 * {{odmiana-przymiotnik-polski|bardziej}}] [: (1.1) {{odmiana-przymiotnik-polski|brak}}] [: (1.1)
 * {{odmiana-przymiotnik-polski}}]
 *
 * @param <T> {@link AdjectiveVar}.
 */
public class AdjectiveVarParserImpl<T> implements VarietyParser<T> {

    AdjectiveVarGenerator adjectiveVarGenerator = new AdjectiveVarGenerator();

    @SuppressWarnings("unchecked")
    @Override
    public T parse(WikiUnit tempUnit, List<String> variety) {

        for (String line : variety) {
            String data = removeParenthesis(removeNumeration(line));
            if (data.contains("odmiana-przymiotnik-polski") && !data.contains("|")) {
                AdjectiveVar result = adjectiveVarGenerator.genAdjVar(tempUnit.getName(), null, null);
                return (T) result;
            }

            if (data.contains("odmiana-przymiotnik-polski") && data.contains("|")) {
                String degrees = data.replace("odmiana-przymiotnik-polski", "");
                String higherDegree = "";
                String highestDegree = "";
                String[] splited = degrees.split("\\|");
                if (splited.length < 2 && !splited[0].equals("brak")) {
                    higherDegree = splited[0];
                }
                if (splited.length > 1 && !splited[1].equals("brak")) {
                    highestDegree = splited[1];
                }

                AdjectiveVar result = adjectiveVarGenerator.genAdjVar(tempUnit.getName(), higherDegree, highestDegree);

                return (T) result;
            }
        }

        return null;
    }
}
