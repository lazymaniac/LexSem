package pl.semantyk.wikiparser;

import pl.semantyk.domain.WikiUnit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Integer.parseInt;

public class NumerationParserImpl implements NumerationParser {

	/**
	 * Searches block of data looking for ":" character
	 * then parse numeration.
	 * 
	 * @param line
	 *            line with numeration.
	 * @return list of parsed numerations.s
	 */
	@Override
	public List<WikiNumeration> parse(WikiUnit tempUnit, String line) {
		Set<WikiNumeration> result = new HashSet<>();

		String numeration;

		if (line.startsWith(":")) {
			// ZNAJDŹ INDEKSY POCZĄTKU I KOŃCA NUMERACJI.
			int beg = line.indexOf("(") + 1;
			int end = line.indexOf(")");

			// JEŻELI NIE MA INFORMACJI O NUMERACJI ZWRÓĆ NUMERACJĘ DLA
			// WSZYSTKICH ZNACZEŃ.
			if (beg == -1 || end == -1 || beg > 3) {
				for (int i = 0; i < tempUnit.getPartsOfSpeech().size(); i++) {
					for (int j = 0; j < tempUnit.getPartsOfSpeech().get(i).getImportances().size(); j++) {
						result.add(new WikiNumeration(i, j));
					}
				}
				return new ArrayList<>(result);
			}
			

			numeration = line.substring(beg, end);

			// PODZIEL NUMERACJĘ NA LICZBY I ZNAKI PUNKCYJNE.
			List<String> splited = splitNumeration(numeration);

			// UTÓRZ OBIEKTY NUMERACJI.
			for (int i = 0; i < splited.size(); i++) {
				// ex. : (1.1) text || : (1.1,2.1)
				if (splited.get(i).equals(".")) {
					if ((i - 2) < 0
							|| (splited.size() > (i + 2) && !splited.get(i + 2).contains("-"))) {
						Integer firstNumber = null;
						Integer secondNumber = null;
						try {
							firstNumber = parseInt(splited.get(i - 1)) - 1;
							secondNumber = parseInt(splited.get(i + 1)) - 1;
							result.add(new WikiNumeration(firstNumber,
									secondNumber));
						} catch (NumberFormatException ex) {
							result.addAll(handleNumerationException(tempUnit,
									firstNumber, secondNumber));
						}
					}
				}

				// ex. : (1.2-3) text
				if (splited.get(i).equals("-")) {
					if ((i - 2) > 0 && splited.get(i - 2).equals(".")) {
						Integer firstNumber = parseInt(splited.get(i - 3)) - 1;
						Integer secondFrom = parseInt(splited.get(i - 1)) - 1;
						Integer secondTo = parseInt(splited.get(i + 1)) - 1;

						for (int secondNumber = secondFrom; secondNumber < secondTo + 1; secondNumber++) {
							result.add(new WikiNumeration(firstNumber,
									secondNumber));
						}
					}

					if (((i - 2) > 0 && !splited.get(i - 2).equals("."))
							|| (i - 2) < 0) {
						Integer firstFrom = parseInt(splited.get(i - 1)) - 1;
						Integer firstTo = parseInt(splited.get(i + 1)) - 1;

						if (tempUnit.getPartsOfSpeech().size() > firstTo) {
							for (int firstNumber = firstFrom; firstNumber < firstTo + 1; firstNumber++) {
								for (int secondNumber = 0; secondNumber < tempUnit
										.getPartsOfSpeech().get(firstNumber)
										.getImportances().size(); secondNumber++) {
									result.add(new WikiNumeration(firstNumber,
											secondNumber));
								}
							}
						}
						// JEŻELI ZNACZNIA NIE ZAWIERAJĄ INDEKSÓW PSUJĄCYCH DO
						// PODANYCH.
						else {
							for (int firstNumber = 0; firstNumber < tempUnit
									.getPartsOfSpeech().size(); firstNumber++) {
								if (tempUnit.getPartsOfSpeech()
										.get(firstNumber).getImportances()
										.size() > firstTo) {
									for (int secondNumber = firstFrom; secondNumber <= firstTo; secondNumber++) {
										result.add(new WikiNumeration(
												firstNumber, secondNumber));
									}
									break;
								}
							}
						}
					}
				}

				// ex. : (1.2,3) text
				if (splited.get(i).equals(",") && (i - 2) > 0
						&& splited.get(i - 2).equals(".")) {
					result.add(new WikiNumeration(
							parseInt(splited.get(i - 3)) - 1, parseInt(splited
									.get(i - 1)) - 1));
					result.add(new WikiNumeration(
							parseInt(splited.get(i - 3)) - 1, parseInt(splited
									.get(i + 1)) - 1));
				}

				// ex. : (1,3)
				if (splited.get(i).equals(",") && 
					((i - 2) < 0 || ((i - 2) > 0 && 
					((!splited.get(i - 2).equals(".")) && 
					!splited.get(i - 2).equals("-"))))) 
				{
					Integer firstNumbers[] = new Integer[2];
					try {
						firstNumbers[0] = parseInt(splited.get(i - 1)) - 1;
						firstNumbers[1] = parseInt(splited.get(i + 1)) - 1;

						for (Integer firstNumber : firstNumbers) {
							for (int secondNumber = 0; secondNumber < tempUnit
									.getPartsOfSpeech().get(firstNumber)
									.getImportances().size(); secondNumber++) {
								result.add(new WikiNumeration(firstNumber,
										secondNumber));
							}
						}
					} catch (IndexOutOfBoundsException ex) {
						result.addAll(handleIndexOutOfBoundException(tempUnit,
								firstNumbers, null));
					}
				}

				// ex. : (1)
				if (splited.size() == 1) {
					Integer firstNumber;
					try {
						firstNumber = parseInt(splited.get(0)) - 1;
						for (int secondNumber = 0; secondNumber < tempUnit
								.getPartsOfSpeech().get(firstNumber)
								.getImportances().size(); secondNumber++) {
							result.add(new WikiNumeration(firstNumber,
									secondNumber));
						}
					} catch (IndexOutOfBoundsException | NumberFormatException ex) {
						// NIE MA TAKIEGO ZNACZENIA.
					}

				}
			}
		}

		return new ArrayList<>(result);
	}

	/**
	 * If exception thrown during parsing, this method is trying to fix this numeration anyway.
	 * 
	 * @param firstNumber
	 * @param secondNumber
	 * @return
	 */
	private Set<WikiNumeration> handleNumerationException(WikiUnit tempUnit,
			Integer firstNumber, Integer secondNumber) {
		Set<WikiNumeration> numerations = new HashSet<>();

		if (firstNumber != null
				&& tempUnit.getPartsOfSpeech().size() > firstNumber
				&& secondNumber == null) {
			for (int i = 0; i < tempUnit.getPartsOfSpeech().get(firstNumber)
					.getImportances().size(); i++) {
				numerations.add(new WikiNumeration(firstNumber, i));
			}
		}

		return numerations;
	}

	/**
	 * Hundles OutOfBoundException during parsing.
	 * 
	 * @param tempUnit
	 *            working unit.
	 * @param firstNumbers
	 *            index of part of speech.
	 * @param secondNumbers
	 *            index of inportance.
	 * @return
	 */
	private Set<WikiNumeration> handleIndexOutOfBoundException(
			WikiUnit tempUnit, Integer[] firstNumbers, Integer[] secondNumbers) {
		Set<WikiNumeration> numerations = new HashSet<>();

		if (firstNumbers != null) {
			for (Integer firstNumber : firstNumbers) {
				if (tempUnit.getPartsOfSpeech().size() < firstNumber) {
					for (int i = 0; i < tempUnit.getPartsOfSpeech().size(); i++) {
						if (tempUnit.getPartsOfSpeech().get(i).getImportances()
								.size() > firstNumber) {
							numerations.add(new WikiNumeration(i, firstNumber));
						}
					}
				}
			}
		}

		return numerations;
	}

	/**
	 * Splits passed numeration into table of strings.
	 * 
	 * @param numeration
	 *            numeracją bez nawiasów.
	 * @return lista stringów.
	 */
	private List<String> splitNumeration(String numeration) {
		String chars[] = numeration.split("");

		List<String> splited = new ArrayList<>();

		String number = "";

		for (String letter : chars) {
			if (letter.matches("[0-9]+")) {
				number += letter;
			}

			if (letter.matches("[\\.,-]+")) {
				splited.add(number);
				splited.add(letter);
				number = "";
			}
		}

		splited.add(number);

		return splited;
	}
}
