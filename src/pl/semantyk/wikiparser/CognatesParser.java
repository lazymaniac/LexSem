package pl.semantyk.wikiparser;

import pl.semantyk.domain.WikiUnit;
import pl.semantyk.domain.Cognate;

import java.util.List;

import static pl.semantyk.utils.StringUtils.*;

public class CognatesParser implements WikiRawParser {

	private static final String CZAS = "{{czas}}";

	private static final String RZECZ = "{{rzecz}}";
	
	private static final String PRZYM = "{{przym}}";
	
	private static final String PRZYS = "{{przysł}}";
	
    @Override
    public void parse(WikiUnit tempUnit, List<String> data) {

        // USUŃ PUSTE POKREWNE
        data.set(0, (data.get(0).replace("{{pokrewne}}", "")).trim());
        data.remove("");

        if (!data.isEmpty()) {

            for (String raw : data) {
            	String partOfSpeech = null;
                String[] cognates;
                
                if (raw.contains(CZAS)) {
                	partOfSpeech = removeParenthesis(CZAS);
                	raw = raw.replace(CZAS, "").trim();
                } 
                if (raw.contains(RZECZ)) {
                	partOfSpeech = removeParenthesis(RZECZ);
                	raw = raw.replace(RZECZ, "").trim();
                } 
                if (raw.contains(PRZYM)) {
                	partOfSpeech = removeParenthesis(PRZYM);
                	raw = raw.replace(PRZYM, "").trim();
                }
                if (raw.contains(PRZYS)) {
                	partOfSpeech = removeParenthesis(PRZYS);
                	raw = raw.replace(PRZYS, "").trim();
                }
                
                // PODZIEL DANE PO ZNAKU SERPARACJI.
                if (raw.contains(", ")) {
                    cognates = raw.split(", ");
                } else {
                    cognates = new String[1];
                    cognates[0] = raw;
                }

                // USUN ZBEDNA SPCJE I ZNAKI.
                for (int i = 0; i < cognates.length; i++) {
                    cognates[i] = cognates[i].replaceAll(":", "").trim();
                }
                for (String cog : cognates) {
                	Cognate cognate = new Cognate();
                	cognate.setCognate(removePersonTag(removeReferences(cog)).trim());
                	cognate.setPartOfSpeech(partOfSpeech);
                    tempUnit.addCognate(cognate);
                }

            }
        }
    }
}
