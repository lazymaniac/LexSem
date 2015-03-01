package pl.semantyk.wikiparser;

import static pl.semantyk.utils.StringUtils.getStringAfterEqual;
import static pl.semantyk.utils.StringUtils.getStringBeforeEqual;
import static pl.semantyk.utils.StringUtils.removeAdditionalComment;
import static pl.semantyk.utils.StringUtils.removeNumeration;
import static pl.semantyk.utils.StringUtils.removeParenthesis;
import static pl.semantyk.utils.StringUtils.removePunctuation;
import static pl.semantyk.utils.StringUtils.removeReferences;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import pl.semantyk.domain.PersonVar;
import pl.semantyk.domain.VerbVar;
import pl.semantyk.domain.WikiUnit;
import pl.semantyk.enums.PersonVarType;

public class VerbVarParserImpl<T> implements VarietyParser<T> {

	private static final Logger LOG = Logger.getLogger(VerbVarParserImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public T parse(WikiUnit tempUnit, List<String> data) {
		VerbVar verVar = new VerbVar();
		List<String> variety = new ArrayList<>();

		boolean simpleForm = false; // FLAGA PRZECHOWUJĄCA INFORMACJE O TYPIE
									// FORMULARZA ODMIANY.

		// USUŃ ZBĘDNE DANE.
		for (String s : data) {
			variety.add(removeAdditionalComment(removeNumeration(removeReferences(s))));
		}

		if (data.get(0).contains("nieodm") || data.get(0).contains("zob")) {
			return (T) verVar;
		}

		for (String str : variety) {
			if (str.contains("{{KoniugacjaPL|"))
				simpleForm = true;
		}

		if (simpleForm) {
			parserSimpleForm(verVar, variety, tempUnit);
		} else {
			parseNormalForm(verVar, variety);
		}

		// print("Odmiana czasownika: " + czasOdm);

		return (T) verVar;
	}

	private void parserSimpleForm(VerbVar verbVar, List<String> variety,
			WikiUnit tempUnit) {
		for (String line : variety) {
			String whiteSpaceSplited[];

			whiteSpaceSplited = line.split("\\s");

			for (int i = 0; i < whiteSpaceSplited.length; i++) {
				if (tempUnit.getName().equals("kupować")) {
					LOG.debug(whiteSpaceSplited[i]);
				}

				if (whiteSpaceSplited[i].contains("|")
						&& !whiteSpaceSplited[i].contains("oniugacja")) {
					String topicAndTip[] = whiteSpaceSplited[i].split("\\|");
					if (topicAndTip.length > 0) {
						verbVar.setTopic(removePunctuation(topicAndTip[0]));
					}
				}

				if (whiteSpaceSplited[i].contains("oniugacja")) {
					String temp = removePunctuation(removeParenthesis(whiteSpaceSplited[i]));
					String conjugation[] = temp.split("\\|");
					if (conjugation.length > 1) {
						verbVar.setConjugation(conjugation[1]);
					}

				}

				if (whiteSpaceSplited[i].contains("{{dk}}")) {
					// TODO DODAĆ OBSŁUGĘ TRYBU DOKONANEGO W POSTACI {{dk}} LUB
					// TEKSTOWO NP: ''aspekt dokonany''
					// treść.
					if (whiteSpaceSplited.length > (i + 1)) {
						verbVar.setPerfective(removeParenthesis(whiteSpaceSplited[i + 1]));
					}
				}

				if (whiteSpaceSplited[i].contains("{{ndk}}")) {
					if (whiteSpaceSplited.length > (i + 1)) {
						verbVar.setImperfective(removeParenthesis(whiteSpaceSplited[i + 1]));
					}
				}
				// debug
				if (tempUnit.getName().equals("kupować")) {
					LOG.debug("temat: " + verbVar.getTopic() + " koniugacja: "
							+ verbVar.getConjugation());
				}
			}

			verbVar.fillConjugationVariety();
		}
	}

	private void parseNormalForm(VerbVar verbVar, List<String> veriaty) {
		PersonVar czasTeraz = new PersonVar(PersonVarType.CZAS_TERAZ);
		PersonVar czasPrzeszlyM = new PersonVar(PersonVarType.CZAS_PRZESZ_M); // Czas
																				// przeszły
		PersonVar czasPrzeszlyF = new PersonVar(PersonVarType.CZAS_PRZESZ_F);
		PersonVar czasPrzeszlyN = new PersonVar(PersonVarType.CZAS_PRZESZ_N);
		PersonVar czasPrzyszlyM = new PersonVar(PersonVarType.CZAS_PRZYSZ_M);
		PersonVar czasPrzyszlyF = new PersonVar(PersonVarType.CZAS_PRZYSZ_F);
		PersonVar czasPrzyszlyN = new PersonVar(PersonVarType.CZAS_PRZYSZ_N);
		PersonVar trybRozkaz = new PersonVar(PersonVarType.TRYB_ROZKAZ); // Tryb
																			// rozujący
		PersonVar trybPrzypM = new PersonVar(PersonVarType.TRYB_PRZYP_M); // Tryb
																			// przypuszczający
		PersonVar trybPrzypF = new PersonVar(PersonVarType.TRYB_PRZYP_F);
		PersonVar trybPrzypN = new PersonVar(PersonVarType.TRYB_PRZYP_N);
		PersonVar imiesPrzymCzynnyM = new PersonVar(
				PersonVarType.IMIES_PRZYM_CZYNNY_M); // Imiesłów przymiotnikowy
														// czynny
		PersonVar imiesPrzymCzynnyF = new PersonVar(
				PersonVarType.IMIES_PRZYM_CZYNNY_F);
		PersonVar imiesPrzymCzynnyN = new PersonVar(
				PersonVarType.IMIES_PRZYM_CZYNNY_N);
		PersonVar imiesPrzymBiernyM = new PersonVar(
				PersonVarType.IMIES_PRZYM_BIERNY_M); // Imiesłów przymiotnikowy
														// bierny
		PersonVar imiesPrzymBiernyF = new PersonVar(
				PersonVarType.IMIES_PRZYM_BIERNY_F);
		PersonVar imiesPrzymBiernyN = new PersonVar(
				PersonVarType.IMIES_PRZYM_BIERNY_N);

		for (String line : veriaty) {

			String type = getStringBeforeEqual(line);

			// print("linia:" + line + " typ: " + typ);

			if (type.equals("dokonany")) {
				String dk = getStringAfterEqual(line);
				if (dk != null && dk.equals("tak")) {
					verbVar.setIsPerfective(true);
				}
			}

			if (type.equals("się")) {
				verbVar.setIsReflexiveVerb(true);
				verbVar.setReflexivePronoun(getStringAfterEqual(line));
			}

			if (type.equals("koniugacja")) {
				verbVar.setConjugation(getStringAfterEqual(line));
			}
			if (type.equals("robić") || type.equals("zrobić")) {
				verbVar.setInfinitive(getStringAfterEqual(line));
			}
			if (type.equals("robię") || type.equals("zrobię")) {
				czasTeraz.setPer1Sin(getStringAfterEqual(line));
			}
			if (type.equals("robi") || type.equals("zrobi")) {
				czasTeraz.setPer3Sin(getStringAfterEqual(line));
			}
			if (type.equals("robisz") || type.equals("zrobisz")) {
				czasTeraz.setPer2Sin(getStringAfterEqual(line));
			}
			if (type.equals("robią") || type.equals("zrobią")) {
				czasTeraz.setPer3Plu(getStringAfterEqual(line));
			}
			if (type.equals("robimy") || type.equals("zrobimy")) {
				czasTeraz.setPer1Plu(getStringAfterEqual(line));
			}
			if (type.equals("robicie") || type.equals("zrobicie")) {
				czasTeraz.setPer2Plu(getStringAfterEqual(line));
			}
			if (type.equals("robiłem") || type.equals("zrobiłem")) {
				czasPrzeszlyM.setPer1Sin(getStringAfterEqual(line));
			}
			if (type.equals("robił") || type.equals("zrobił")) {
				czasPrzeszlyM.setPer3Sin(getStringAfterEqual(line));
			}
			if (type.equals("robiła") || type.equals("zrobiła")) {
				czasPrzeszlyF.setPer3Sin(getStringAfterEqual(line));
			}
			if (type.equals("robili") || type.equals("zrobię")) {
				czasPrzeszlyM.setPer3Plu(getStringAfterEqual(line));
			}
			if (type.equals("robiono") || type.equals("zrobiono")) {
				czasPrzeszlyN.setPer3Sin(getStringAfterEqual(line));
			}
			if (type.equals("będęrobił") || type.equals("będęzrobił")) {
				czasPrzyszlyM.setPer1Sin(getStringAfterEqual(line));
			}
			if (type.equals("będęrobiła") || type.equals("będęzrobiła")) {
				czasPrzyszlyF.setPer1Sin(getStringAfterEqual(line));
			}
			if (type.equals("będęrobiło") || type.equals("będęzrobiło")) {
				czasPrzyszlyN.setPer1Sin(getStringAfterEqual(line));
			}
			if (type.equals("będzieszrobił") || type.equals("będzieszzrobił")) {
				czasPrzyszlyM.setPer2Sin(getStringAfterEqual(line));
			}
			if (type.equals("będzieszrobiła") || type.equals("będzieszzrobiła")) {
				czasPrzyszlyF.setPer2Sin(getStringAfterEqual(line));
			}
			if (type.equals("będzieszrobiło") || type.equals("będzieszzrobiło")) {
				czasPrzyszlyN.setPer2Sin(getStringAfterEqual(line));
			}
			if (type.equals("będzierobił") || type.equals("będziezrobił")) {
				czasPrzyszlyM.setPer3Sin(getStringAfterEqual(line));
			}
			if (type.equals("będzierobiła") || type.equals("będziezrobiła")) {
				czasPrzyszlyF.setPer3Sin(getStringAfterEqual(line));
			}
			if (type.equals("będzierobiło") || type.equals("będziezrobiła")) {
				czasPrzyszlyN.setPer3Sin(getStringAfterEqual(line));
			}
			if (type.equals("będziemyrobili") || type.equals("będziemyzrobili")) {
				czasPrzyszlyM.setPer1Plu(getStringAfterEqual(line));
			}
			if (type.equals("będziemyrobiły") || type.equals("będziemyzrobiły")) {
				czasPrzyszlyF.setPer1Plu(getStringAfterEqual(line));
				czasPrzyszlyN.setPer1Plu(getStringAfterEqual(line));
			}
			if (type.equals("będziecierobili")
					|| type.equals("będzieciezrobili")) {
				czasPrzyszlyM.setPer2Plu(getStringAfterEqual(line));
			}
			if (type.equals("będziecierobiły")
					|| type.equals("będzieciezrobiły")) {
				czasPrzyszlyF.setPer2Plu(getStringAfterEqual(line));
				czasPrzyszlyN.setPer2Plu(getStringAfterEqual(line));
			}
			if (type.equals("będąrobili") || type.equals("będązrobili")) {
				czasPrzyszlyM.setPer3Plu(getStringAfterEqual(line));
			}
			if (type.equals("będąrobiły") || type.equals("będązrobiły")) {
				czasPrzyszlyF.setPer3Plu(getStringAfterEqual(line));
			}
			if (type.equals("robiłbym") || type.equals("zrobiłbym")) {
				trybPrzypM.setPer1Sin(getStringAfterEqual(line));
			}
			if (type.equals("robiłbyś") || type.equals("zrobiłbyś")) {
				trybPrzypM.setPer2Sin(getStringAfterEqual(line));
			}
			if (type.equals("robiłby") || type.equals("zrobiłby")) {
				trybPrzypM.setPer3Sin(getStringAfterEqual(line));
			}
			if (type.equals("robilibyśmy") || type.equals("zrobilibyśmy")) {
				trybPrzypM.setPer1Plu(getStringAfterEqual(line));
			}
			if (type.equals("robilibyście") || type.equals("zrobilibyście")) {
				trybPrzypM.setPer2Plu(getStringAfterEqual(line));
			}
			if (type.equals("robiliby") || type.equals("zrobiliby")) {
				trybPrzypM.setPer3Plu(getStringAfterEqual(line));
			}
			if (type.equals("robiłabym") || type.equals("zrobiłabym")) {
				trybPrzypF.setPer1Sin(getStringAfterEqual(line));
			}
			if (type.equals("robiłabyś") || type.equals("zrobiłabyś")) {
				trybPrzypF.setPer2Sin(getStringAfterEqual(line));
			}
			if (type.equals("robiłaby") || type.equals("zrobiłaby")) {
				trybPrzypF.setPer3Sin(getStringAfterEqual(line));
			}
			if (type.equals("robiłobym") || type.equals("zrobiłobym")) {
				trybPrzypN.setPer1Sin(getStringAfterEqual(line));
			}
			if (type.equals("robiłobyś") || type.equals("zrobiłobyś")) {
				trybPrzypN.setPer2Sin(getStringAfterEqual(line));
			}
			if (type.equals("robiłoby") || type.equals("zrobiłoby")) {
				trybPrzypN.setPer3Sin(getStringAfterEqual(line));
			}
			if (type.equals("robiłybyśmy") || type.equals("zrobiłybyśmy")) {
				trybPrzypF.setPer1Plu(getStringAfterEqual(line));
				trybPrzypN.setPer1Plu(getStringAfterEqual(line));
			}
			if (type.equals("robiłybyście") || type.equals("zrobiłybyście")) {
				trybPrzypF.setPer2Plu(getStringAfterEqual(line));
				trybPrzypN.setPer2Plu(getStringAfterEqual(line));
			}
			if (type.equals("robiłby") || type.equals("zrobiłby")) {
				trybPrzypF.setPer3Plu(getStringAfterEqual(line));
				trybPrzypN.setPer3Plu(getStringAfterEqual(line));
			}
			if (type.equals("niechrobi") || type.equals("niechzrobi")) {
				trybRozkaz.setPer3Sin(getStringAfterEqual(line));
			}
			if (type.equals("niechrobią") || type.equals("niechzrobią")) {
				trybRozkaz.setPer3Plu(getStringAfterEqual(line));
			}
			if (type.equals("robiono") || type.equals("zrobiono")) {
			}
			if (type.equals("rób") || type.equals("zrób")) {
				trybRozkaz.setPer2Sin(getStringAfterEqual(line));
			}

			if (type.equals("robiąc") || type.equals("zrobiąc")) {
			}
			if (type.equals("zrobiwszy")) {
			}
			if (type.equals("robienie") || type.equals("zrobienie")) {
			}
			if (type.equals("brakzaprzeszłego")) {
			}
		}
		if (varNotEmpty(czasTeraz)) {
			czasTeraz.setVerbVar(verbVar.getId());
			verbVar.addPersonVar(czasTeraz);
		}

		if (varNotEmpty(czasPrzeszlyM)) {
			czasPrzeszlyM.setVerbVar(verbVar.getId());
			verbVar.addPersonVar(czasPrzeszlyM);
		}

		if (varNotEmpty(czasPrzeszlyF)) {
			czasPrzeszlyF.setVerbVar(verbVar.getId());
			verbVar.addPersonVar(czasPrzeszlyF);
		}

		if (varNotEmpty(czasPrzeszlyN)) {
			czasPrzeszlyN.setVerbVar(verbVar.getId());
			verbVar.addPersonVar(czasPrzeszlyN);
		}

		if (varNotEmpty(czasPrzyszlyM)) {
			czasPrzyszlyM.setVerbVar(verbVar.getId());
			verbVar.addPersonVar(czasPrzyszlyM);
		}

		if (varNotEmpty(czasPrzyszlyF)) {
			czasPrzyszlyF.setVerbVar(verbVar.getId());
			verbVar.addPersonVar(czasPrzyszlyF);
		}

		if (varNotEmpty(czasPrzyszlyN)) {
			czasPrzyszlyN.setVerbVar(verbVar.getId());
			verbVar.addPersonVar(czasPrzyszlyN);
		}

		if (varNotEmpty(trybRozkaz)) {
			trybRozkaz.setVerbVar(verbVar.getId());
			verbVar.addPersonVar(trybRozkaz);
		}

		if (varNotEmpty(trybPrzypM)) {
			trybPrzypM.setVerbVar(verbVar.getId());
			verbVar.addPersonVar(trybPrzypM);
		}

		if (varNotEmpty(trybPrzypN)) {
			trybPrzypN.setVerbVar(verbVar.getId());
			verbVar.addPersonVar(trybPrzypN);
		}

		if (varNotEmpty(trybPrzypF)) {
			trybPrzypF.setVerbVar(verbVar.getId());
			verbVar.addPersonVar(trybPrzypF);
		}

		if (varNotEmpty(imiesPrzymCzynnyM)) {
			imiesPrzymCzynnyM.setVerbVar(verbVar.getId());
			verbVar.addPersonVar(imiesPrzymCzynnyM);
		}

		if (varNotEmpty(imiesPrzymCzynnyF)) {
			imiesPrzymCzynnyF.setVerbVar(verbVar.getId());
			verbVar.addPersonVar(imiesPrzymCzynnyF);
		}

		if (varNotEmpty(imiesPrzymCzynnyN)) {
			imiesPrzymCzynnyN.setVerbVar(verbVar.getId());
			verbVar.addPersonVar(imiesPrzymCzynnyN);
		}

		if (varNotEmpty(imiesPrzymBiernyM)) {
			imiesPrzymBiernyM.setVerbVar(verbVar.getId());
			verbVar.addPersonVar(imiesPrzymBiernyM);
		}

		if (varNotEmpty(imiesPrzymBiernyF)) {
			imiesPrzymBiernyF.setVerbVar(verbVar.getId());
			verbVar.addPersonVar(imiesPrzymBiernyF);
		}

		if (varNotEmpty(imiesPrzymBiernyN)) {
			imiesPrzymBiernyN.setVerbVar(verbVar.getId());
			verbVar.addPersonVar(imiesPrzymBiernyN);
		}
	}

	private boolean varNotEmpty(PersonVar var) {
		if (var.getPer1Plu() != null ||
				var.getPer1Sin() != null ||
				var.getPer2Plu() != null ||
				var.getPer3Plu() != null ||
				var.getPer2Sin() != null ||
				var.getPer3Plu() != null)
		return true;
			else
		return false;
	}

}
