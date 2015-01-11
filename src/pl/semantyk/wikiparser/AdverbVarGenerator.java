package pl.semantyk.wikiparser;

import java.util.Map;

import pl.semantyk.domain.AdverbVar;
import pl.semantyk.domain.WikiUnit;
import pl.semantyk.utils.AdverbGraduationForm;

/**
 * Źródło Wikisłownik:
 *
 * Stopniowanie przysłówków[edytuj]
 *
 * Tworzenie stopnia wyższego przysłówków odbywa się poprzez zamianę ich
 * końcówek według zależności: -ąco → -ęcej (gorąco → goręcej) -do/-dzo → -dziej
 * (twardo → twardziej, bardzo → bardziej) -go → -żej (drogo → drożej) -ko/-to →
 * -ciej (szybko → szybciej, żółto → żółciej) -dko → -dzej (prędko → prędzej)
 * -ło → -lej (ciepło → cieplej) -oło → -elej (wesoło → weselej) -no → -niej
 * (wolno → wolniej) -ono → -ieniej (czerwono → czerwieniej) -sno → -śniej
 * (jasno → jaśniej) -ro/-rze → -rzej (staro → starzej, mądrze → mądrzej) -wo →
 * -wiej (łatwo → łatwiej)
 *
 * W pozostałych przypadkach -o → -ej. Stopień najwyższy tworzy się przez
 * dodanie przedrostka naj-. Z niektórych przysłówków nie tworzy się stopnia
 * wyższego przez zamianę końcówek. Można jednak utworzyć stopień wyższy
 * przysłówka przez użycie słowa "bardziej" (w stopniu najwyższym:
 * "najbardziej"). Można utworzyć w ten sposób stopień wyższy z każdego
 * przysłówka. Wyjątki: Przysłówek | Stopień wyższy | Stopień najwyższy
 * ------------------------------------------------ dobrze | lepiej | najlepiej
 * źle | gorzej | najgorzej dużo | więcej | najwięcej mało | mniej | najmniej
 *
 * @author Sebastian Fabisz
 *
 */
public class AdverbVarGenerator {

	private final AdverbGraduationForm form = new AdverbGraduationForm();

	public AdverbVar generate(final WikiUnit tempUnit) {
		AdverbVar result = null;

		String word = tempUnit.getName();

		result = checkExceptionCases(word);
		if (result != null)
			return result;

		for (Map.Entry<String, String> entry : form.getAllSuffixes().entrySet()) {
			if (word.endsWith(entry.getKey())) {
				String replacement = entry.getValue();
				result = new AdverbVar();
				int endOfTopic = word.lastIndexOf(entry.getKey());
				String topic = word.substring(0, endOfTopic);
				result.setHigherDegree(topic + replacement);
				result.setHighestDegree(PREFIX_HIGEHST + topic + replacement);
				result.setMoreHigherDegree(HIGHER_DEF + word);
				result.setMoreHighestDegree(HIGHEST_DEF + word);
				break;
			}
		}

		return result;
	}

	private AdverbVar checkExceptionCases(final String word) {
		AdverbVar exception = null;

		if (word.equalsIgnoreCase(EXCEPTION_1_1)) {
			exception = new AdverbVar();
			exception.setHigherDegree(EXCEPTION_1_2);
			exception.setHighestDegree(EXCEPTION_1_3);
			exception.setMoreHigherDegree(HIGHER_DEF + EXCEPTION_1_1);
			exception.setMoreHighestDegree(HIGHEST_DEF + EXCEPTION_1_1);
		}

		if (word.equalsIgnoreCase(EXCEPTION_2_1)) {
			exception = new AdverbVar();
			exception.setHigherDegree(EXCEPTION_2_2);
			exception.setHighestDegree(EXCEPTION_2_3);
			exception.setMoreHigherDegree(HIGHER_DEF + EXCEPTION_2_1);
			exception.setMoreHighestDegree(HIGHEST_DEF + EXCEPTION_2_1);
		}

		if (word.equalsIgnoreCase(EXCEPTION_3_1)) {
			exception = new AdverbVar();
			exception.setHigherDegree(EXCEPTION_3_2);
			exception.setHighestDegree(EXCEPTION_3_3);
			exception.setMoreHigherDegree(HIGHER_DEF + EXCEPTION_3_1);
			exception.setMoreHighestDegree(HIGHEST_DEF + EXCEPTION_3_1);
		}

		if (word.equalsIgnoreCase(EXCEPTION_4_1)) {
			exception = new AdverbVar();
			exception.setHigherDegree(EXCEPTION_4_2);
			exception.setHighestDegree(EXCEPTION_4_3);
			exception.setMoreHigherDegree(HIGHER_DEF + EXCEPTION_4_1);
			exception.setMoreHighestDegree(HIGHEST_DEF + EXCEPTION_4_1);
		}

		return exception;
	}

	private final String PREFIX_HIGEHST = "naj";

	private final String HIGHER_DEF = "bardziej ";
	private final String HIGHEST_DEF = "najbardziej ";

	private final String EXCEPTION_1_1 = "dobrze";
	private final String EXCEPTION_1_2 = "lepiej";
	private final String EXCEPTION_1_3 = "najlepiej";

	private final String EXCEPTION_2_1 = "źle";
	private final String EXCEPTION_2_2 = "gorzej";
	private final String EXCEPTION_2_3 = "najgorzej";

	private final String EXCEPTION_3_1 = "dużo";
	private final String EXCEPTION_3_2 = "więcej";
	private final String EXCEPTION_3_3 = "najwięcej";

	private final String EXCEPTION_4_1 = "mało";
	private final String EXCEPTION_4_2 = "mniej";
	private final String EXCEPTION_4_3 = "najmniej";

}
