package pl.semantyk.wikiparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import pl.semantyk.domain.RawWikiUnit;
import pl.semantyk.domain.WikiUnit;
import pl.semantyk.main.Dictionary;

// TODO wyczyscic cala baze z referencji do wiki
public class WikiRawDataFormatter {

	private static final Logger LOG = Logger.getLogger(WikiRawDataFormatter.class);

	private int importancesBegIndex = -1;
	private int varietiesBegIndex = -1;
	private int examplesBegIndex = -1;
	private int syntaxBegIndex = -1;
	private int collocationsBegIndex = -1;
	private int synonymsBegIndex = -1;
	private int antonymsBegIndex = -1;
	private int cognatesBegIndex = -1;
	private int phraseologiesBegIndex = -1;

	private List<String> importanceBlock;
	private List<String> varietiesBlock;
	private List<String> examplesBlock;
	private List<String> collocationsBlock;
	private List<String> synonymsBlock;
	private List<String> antonymsBlock;
	private List<String> cognatesBlock;
	private List<String> phraseologiesBlock;

	private final ImportanceParser importanceParser = new ImportanceParser();
	private final VarietyFactory varietyFactory = new VarietyFactory();
	private final ExamplesParser examplesParser = new ExamplesParser();
	private final CollocationsParser collocationsParser = new CollocationsParser();
	private final SynonymsParser synonymsParser = new SynonymsParser();
	private final AntonymsParser antonymsParser = new AntonymsParser();
	private final CognatesParser cognatesParser = new CognatesParser();
	private final PhraseologyParser phraseologiesParser = new PhraseologyParser();

	public WikiRawDataFormatter(final Dictionary aDict) {
		dict = aDict;
		rawUnits = new LinkedHashSet<>(dict.getRawWikiUnits());
		formatedUnits = new LinkedHashSet<>();
	}

	public void format() {
		LOG.info("\n\n RawUnits size: " + rawUnits.size());

		if ((rawUnits != null) && !rawUnits.isEmpty()) {
			List<String> lines;

			for (RawWikiUnit rawWikiUnit : rawUnits) {
				if (StringUtils.isBlank(rawWikiUnit.getText()) || rawWikiUnit.getTitle().equals("nie (być)")) {
					continue;
				}

				tempUnit = new WikiUnit();
				tempUnit.setName(rawWikiUnit.getTitle());

				// LOG.info(tempUnit.getName());

				// PODZIEL BLOK DANYCH NA LINIE I ZAPISZ DO TABLICY.
				String[] temp = rawWikiUnit.getText().split("\n");
				lines = new ArrayList<>(Arrays.asList(temp));

				for (int i = 0; i < lines.size(); i++) {
					String s = lines.get(i);

					if (s.contains("{{znaczenia}}")) {
						importancesBegIndex = i;
					}
					if (s.contains("{{odmiana}}")) {
						varietiesBegIndex = i;
					}
					if (s.contains("{{przykłady}}")) {
						examplesBegIndex = i;
					}
					if (s.contains("{{składnia")) {
						syntaxBegIndex = i;
					}
					if (s.contains("{{kolokacje}}")) {
						collocationsBegIndex = i;
					}
					if (s.contains("{{synonimy}}")) {
						synonymsBegIndex = i;
					}
					if (s.contains("{{antonimy}}")) {
						antonymsBegIndex = i;
					}
					if (s.contains("{{pokrewne}}")) {
						cognatesBegIndex = i;
					}
					if (s.contains("{{frazeologia}}")) {
						phraseologiesBegIndex = i;
					}
				}

				if ((importancesBegIndex > -1) && (varietiesBegIndex > -1)) {
					importanceBlock = new ArrayList<>(lines.subList(importancesBegIndex, varietiesBegIndex));
				} else if ((importancesBegIndex > -1) && (varietiesBegIndex < 0)) {
					importanceBlock = new ArrayList<>(lines.subList(importancesBegIndex, lines.size()));
				}

				if ((varietiesBegIndex > -1) && (examplesBegIndex > -1)) {
					varietiesBlock = new ArrayList<>(lines.subList(varietiesBegIndex, examplesBegIndex));
				} else if ((varietiesBegIndex > -1) && (examplesBegIndex < 0)) {
					varietiesBlock = new ArrayList<>(lines.subList(varietiesBegIndex, lines.size()));
				}

				if ((examplesBegIndex > -1) && (syntaxBegIndex > -1)) {
					examplesBlock = new ArrayList<>(lines.subList(examplesBegIndex, syntaxBegIndex));
				} else if ((examplesBegIndex > -1) && (syntaxBegIndex < 0)) {
					examplesBlock = new ArrayList<>(lines.subList(examplesBegIndex, lines.size()));
				}

				if ((collocationsBegIndex > -1) && (synonymsBegIndex > -1)) {
					collocationsBlock = new ArrayList<>(lines.subList(collocationsBegIndex, synonymsBegIndex));
				} else if ((collocationsBegIndex > -1) && (synonymsBegIndex < 0)) {
					collocationsBlock = new ArrayList<>(lines.subList(collocationsBegIndex, lines.size()));
				}

				if ((synonymsBegIndex > -1) && (antonymsBegIndex > -1)) {
					synonymsBlock = new ArrayList<>(lines.subList(synonymsBegIndex, antonymsBegIndex));
				} else if ((synonymsBegIndex > -1) && (antonymsBegIndex < 0)) {
					synonymsBlock = new ArrayList<>(lines.subList(synonymsBegIndex, lines.size()));
				}

				if ((antonymsBegIndex > -1) && (cognatesBegIndex > -1)) {
					antonymsBlock = new ArrayList<>(lines.subList(antonymsBegIndex, cognatesBegIndex));
				} else if ((antonymsBegIndex > -1) && (cognatesBegIndex < 0)) {
					antonymsBlock = new ArrayList<>(lines.subList(antonymsBegIndex, lines.size()));
				}

				if ((cognatesBegIndex > -1) && (phraseologiesBegIndex > -1)) {
					cognatesBlock = new ArrayList<>(lines.subList(cognatesBegIndex, phraseologiesBegIndex));
				} else if ((cognatesBegIndex > -1) && (phraseologiesBegIndex < 0)) {
					cognatesBlock = new ArrayList<>(lines.subList(cognatesBegIndex, lines.size()));
				}

				if ((phraseologiesBegIndex > -1) && (lines.size() > phraseologiesBegIndex)) {
					phraseologiesBlock = new ArrayList<>(lines.subList(phraseologiesBegIndex, lines.size()));
				}

				lines.clear();

				if (importanceBlock.size() > 0) {
					importanceParser.parse(tempUnit, importanceBlock);
				}
				if (varietiesBlock.size() > 0) {
					varietyFactory.parse(tempUnit, varietiesBlock);
				}
				if (examplesBlock.size() > 0) {
					examplesParser.parse(tempUnit, examplesBlock);
				}
				if (collocationsBlock.size() > 0) {
					collocationsParser.parse(tempUnit, collocationsBlock);
				}
				if (synonymsBlock.size() > 0) {
					synonymsParser.parse(tempUnit, synonymsBlock);
				}
				if (antonymsBlock.size() > 0) {
					antonymsParser.parse(tempUnit, antonymsBlock);
				}
				if (cognatesBlock.size() > 0) {
					cognatesParser.parse(tempUnit, cognatesBlock);
				}
				if (phraseologiesBlock.size() > 0) {
					phraseologiesParser.parse(tempUnit, phraseologiesBlock);
				}

				formatedUnits.add(tempUnit);
				if (tempUnit.getName().equals("czytać")) {
					LOG.info(tempUnit.toString());
				}
				resetHelpers();
			}
		}

		LOG.info("Formated Units size:" + formatedUnits.size());
		dict.setWikiUnits(new ArrayList<>(formatedUnits));

		formatedUnits = null;
		rawUnits = null;
		dict.setRawWikiUnits(null);

	}

	private void resetHelpers() {
		importancesBegIndex = -1;
		varietiesBegIndex = -1;
		examplesBegIndex = -1;
		syntaxBegIndex = -1;
		collocationsBegIndex = -1;
		synonymsBegIndex = -1;
		antonymsBegIndex = -1;
		cognatesBegIndex = -1;
		phraseologiesBegIndex = -1;

		importanceBlock.clear();
		varietiesBlock.clear();
		examplesBlock.clear();
		collocationsBlock.clear();
		synonymsBlock.clear();
		antonymsBlock.clear();
		cognatesBlock.clear();
		phraseologiesBlock.clear();
	}

	@SuppressWarnings("unused")
	private void debugUnit(final RawWikiUnit ru) {
		if (ru.getTitle().equals("nie ma")) {
			LOG.info("Znaczenia index: " + importancesBegIndex + "\nodmiana index: " + varietiesBegIndex + "\nprzykłady index: " + examplesBegIndex + "\nkolkacje index: " + collocationsBegIndex
					+ "\nsynonimy index: " + synonymsBegIndex + "\nantonimy index: " + antonymsBegIndex + "\npokrewne index: " + cognatesBegIndex + "\nfrazeologia index: " + phraseologiesBegIndex);
			LOG.info("znaczenia list: " + importanceBlock.toString());
			LOG.info("odmiana list: " + varietiesBlock.toString());
			LOG.info("przyklady list: " + examplesBlock.toString());
			LOG.info("kolokacje list: " + collocationsBlock.toString());
			LOG.info("synonimy list: " + synonymsBlock.toString());
			LOG.info("antonimy list: " + antonymsBlock.toString());
			LOG.info("pokrewne list: " + cognatesBlock.toString());
			LOG.info("frazeologia list: " + phraseologiesBlock.toString());
			importanceParser.parse(tempUnit, importanceBlock);
			varietyFactory.parse(tempUnit, varietiesBlock);
			examplesParser.parse(tempUnit, examplesBlock);
			collocationsParser.parse(tempUnit, collocationsBlock);
			synonymsParser.parse(tempUnit, synonymsBlock);
			antonymsParser.parse(tempUnit, antonymsBlock);
			cognatesParser.parse(tempUnit, cognatesBlock);
			phraseologiesParser.parse(tempUnit, phraseologiesBlock);
			formatedUnits.add(tempUnit);
		}
	}

	private final Dictionary dict;
	private Set<RawWikiUnit> rawUnits;
	private Set<WikiUnit> formatedUnits;
	private WikiUnit tempUnit;

	private int wikiUnitIdCounter = 1;
}
