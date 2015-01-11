package pl.semantyk.utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Źródło Wikisłownik:
 * 
 * Stopniowanie przysłówków:
 * 
 * Tworzenie stopnia wyższego przysłówków odbywa się poprzez zamianę ich
 * końcówek według zależności: 
 * -ąco → -ęcej (gorąco → goręcej) 
 * -do/-dzo → -dziej (twardo → twardziej, bardzo → bardziej) 
 * -go → -żej (drogo → drożej) 
 * -ko/-to → -ciej (szybko → szybciej, żółto → żółciej) 
 * -dko → -dzej (prędko → prędzej)
 * -ło → -lej (ciepło → cieplej) 
 * -oło → -elej (wesoło → weselej) 
 * -no → -niej (wolno → wolniej) 
 * -ono → -ieniej (czerwono → czerwieniej) 
 * -sno → -śniej (jasno → jaśniej) 
 * -ro/-rze → -rzej (staro → starzej, mądrze → mądrzej) 
 * -wo → -wiej (łatwo → łatwiej) 
 * 
 * W pozostałych przypadkach -o → -ej. Stopień najwyższy
 * tworzy się przez dodanie przedrostka naj-. Z niektórych przysłówków nie
 * tworzy się stopnia wyższego przez zamianę końcówek. Można jednak utworzyć
 * stopień wyższy przysłówka przez użycie słowa "bardziej" (w stopniu
 * najwyższym: "najbardziej"). Można utworzyć w ten sposób stopień wyższy z
 * każdego przysłówka. Wyjątki: 
 * Przysłówek | Stopień wyższy | Stopień najwyższy
 * ------------------------------------------------
 * dobrze     | lepiej         | najlepiej 
 * źle        | gorzej 		   | najgorzej 
 * dużo       | więcej         | najwięcej 
 * mało       | mniej          | najmniej
 * 
 * @author Sebastian Fabisz
 *
 */
public class AdverbGraduationForm {

	private final Map<String, String> SUFFIXES;
	
	private final String SUFFIX_1 = "ąco";
	private final String SUFFIX_1_REP = "ęcej";
	
	private final String SUFFIX_2_1 = "do";
	private final String SUFFIX_2_2 = "dzo";
	private final String SUFFIX_2_REP = "dziej";
	
	private final String SUFFIX_3 = "go";
	private final String SUFFIX_3_REP = "żej";
	
	private final String SUFFIX_4 = "dko";
	private final String SUFFIX_4_REP = "dzej";
	
	private final String SUFFIX_5_1 = "ko";
	private final String SUFFIX_5_2 = "to";
	private final String SUFFIX_5_REP = "ciej";
	
	private final String SUFFIX_6 = "oło";
	private final String SUFFIX_6_REP = "elej";
	
	private final String SUFFIX_7 = "ło";
	private final String SUFFIX_7_REP = "lej";
	
	private final String SUFFIX_8 = "ono";
	private final String SUFFIX_8_REP = "ieniej";
	
	private final String SUFFIX_9 = "sno";
	private final String SUFFIX_9_REP = "śniej";
	
	private final String SUFFIX_10 = "no";
	private final String SUFFIX_10_REP = "niej";

	private final String SUFFIX_11_1 = "ro";
	private final String SUFFIX_11_2 = "rze";
	private final String SUFFIX_11_REP = "rzej";
	
	private final String SUFFIX_12 = "wo";
	private final String SUFFIX_12_REP = "wiej";
	
	private final String SUFFIX_13 = "o";
	private final String SUFFIX_13_REP = "ej";
	
	public AdverbGraduationForm() {
		SUFFIXES = new LinkedHashMap<String, String>();
		
		SUFFIXES.put(SUFFIX_1, SUFFIX_1_REP);
		SUFFIXES.put(SUFFIX_2_1, SUFFIX_2_REP);
		SUFFIXES.put(SUFFIX_2_2, SUFFIX_2_REP);
		SUFFIXES.put(SUFFIX_3, SUFFIX_3_REP);
		SUFFIXES.put(SUFFIX_4, SUFFIX_4_REP);
		SUFFIXES.put(SUFFIX_5_1, SUFFIX_5_REP);
		SUFFIXES.put(SUFFIX_5_2, SUFFIX_5_REP);
		SUFFIXES.put(SUFFIX_6, SUFFIX_6_REP);
		SUFFIXES.put(SUFFIX_7, SUFFIX_7_REP);
		SUFFIXES.put(SUFFIX_8, SUFFIX_8_REP);
		SUFFIXES.put(SUFFIX_9, SUFFIX_9_REP);
		SUFFIXES.put(SUFFIX_10, SUFFIX_10_REP);
		SUFFIXES.put(SUFFIX_11_1, SUFFIX_11_REP);
		SUFFIXES.put(SUFFIX_11_2, SUFFIX_11_REP);
		SUFFIXES.put(SUFFIX_12, SUFFIX_12_REP);
		SUFFIXES.put(SUFFIX_13, SUFFIX_13_REP);
	}

	public Map<String, String> getAllSuffixes() {
		return SUFFIXES;
	}

	public String getSUFFIX_1() {
		return SUFFIX_1;
	}

	public String getSUFFIX_1_REP() {
		return SUFFIX_1_REP;
	}

	public String getSUFFIX_2_1() {
		return SUFFIX_2_1;
	}

	public String getSUFFIX_2_2() {
		return SUFFIX_2_2;
	}

	public String getSUFFIX_2_REP() {
		return SUFFIX_2_REP;
	}

	public String getSUFFIX_3() {
		return SUFFIX_3;
	}

	public String getSUFFIX_3_REP() {
		return SUFFIX_3_REP;
	}

	public String getSUFFIX_4() {
		return SUFFIX_4;
	}

	public String getSUFFIX_4_REP() {
		return SUFFIX_4_REP;
	}

	public String getSUFFIX_5_1() {
		return SUFFIX_5_1;
	}

	public String getSUFFIX_5_2() {
		return SUFFIX_5_2;
	}

	public String getSUFFIX_5_REP() {
		return SUFFIX_5_REP;
	}

	public String getSUFFIX_6() {
		return SUFFIX_6;
	}

	public String getSUFFIX_6_REP() {
		return SUFFIX_6_REP;
	}

	public String getSUFFIX_7() {
		return SUFFIX_7;
	}

	public String getSUFFIX_7_REP() {
		return SUFFIX_7_REP;
	}

	public String getSUFFIX_8() {
		return SUFFIX_8;
	}

	public String getSUFFIX_8_REP() {
		return SUFFIX_8_REP;
	}

	public String getSUFFIX_9() {
		return SUFFIX_9;
	}

	public String getSUFFIX_9_REP() {
		return SUFFIX_9_REP;
	}

	public String getSUFFIX_10() {
		return SUFFIX_10;
	}

	public String getSUFFIX_10_REP() {
		return SUFFIX_10_REP;
	}

	public String getSUFFIX_11_1() {
		return SUFFIX_11_1;
	}

	public String getSUFFIX_11_2() {
		return SUFFIX_11_2;
	}

	public String getSUFFIX_11_REP() {
		return SUFFIX_11_REP;
	}

	public String getSUFFIX_12() {
		return SUFFIX_12;
	}

	public String getSUFFIX_12_REP() {
		return SUFFIX_12_REP;
	}

	public String getSUFFIX_13() {
		return SUFFIX_13;
	}

	public String getSUFFIX_13_REP() {
		return SUFFIX_13_REP;
	}
	
}
