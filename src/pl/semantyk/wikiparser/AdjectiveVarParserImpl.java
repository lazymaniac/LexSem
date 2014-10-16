package pl.semantyk.wikiparser;

import pl.semantyk.domain.WikiUnit;
import pl.semantyk.domain.AdjectiveVar;

import java.util.List;

import static pl.semantyk.utils.StringUtils.removeParenthesis;
import static pl.semantyk.utils.StringUtils.removeNumeration;

/**
 * Parsuje informacje o odmianie przymiotnika i przy pomocy generatora tworzy obiekt odmiany. Przykłady: [: (1.1)
 * {{odmiana-przymiotnik-polski|bardziej}}] [: (1.1) {{odmiana-przymiotnik-polski|brak}}] [: (1.1)
 * {{odmiana-przymiotnik-polski}}]
 *
 * @param <T> typ jaki przyjmuje parser (PrzymiotnikOdmiana.class).
 */
public class AdjectiveVarParserImpl<T> implements VarietyParser<T> {

    AdjectiveVarGenerator odmGenerator = new AdjectiveVarGenerator();

    @SuppressWarnings("unchecked")
    @Override
    public T parse(WikiUnit tempUnit, List<String> odmiana) {

        for (String line : odmiana) {
            String data = removeParenthesis(removeNumeration(line));
            if (data.contains("odmiana-przymiotnik-polski") && !data.contains("|")) {
                AdjectiveVar result = odmGenerator.genAdjVar(tempUnit.getName(), null, null);
                return (T) result;
            }

            if (data.contains("odmiana-przymiotnik-polski") && data.contains("|")) {
                String stopnie = data.replace("odmiana-przymiotnik-polski", "");
                String stopWyzszy = "";
                String stopNajwyzszy = "";
                String[] splited = stopnie.split("\\|");
                if (splited.length < 2 && !splited[0].equals("brak")) {
                    stopWyzszy = splited[0];
                }
                if (splited.length > 1 && !splited[1].equals("brak")) {
                    stopNajwyzszy = splited[1];
                }

                AdjectiveVar result = odmGenerator.genAdjVar(tempUnit.getName(), stopWyzszy, stopNajwyzszy);

                return (T) result;
            }
        }

        return null;
    }
}
