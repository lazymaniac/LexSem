package pl.semantyk.wikiparser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import pl.semantyk.domain.RawWikiUnit;
import pl.semantyk.main.Dictionary;
import pl.semantyk.utils.StopWatch;
import pl.semantyk.wordnetparser.IUnitsFilter;

public class WikiCleaner implements IUnitsFilter, Runnable {

	private static final Logger LOG = Logger.getLogger(WikiCleaner.class);

	public WikiCleaner(final Dictionary aDict) {
		dict = aDict;
		rawWikiUnits = new LinkedHashSet<>(dict.getRawWikiUnits());
		trashUnits = new HashSet<>();
		LOG.info("WikiCleaner raw units size: " + rawWikiUnits.size());
	}

	@Override
	public void run() {
		clearUnits();
	}

	@Override
	public void clearUnits() {
		clearForms();
		System.out.println("zostalo jednostek: " + rawWikiUnits.size());
		clearForeignLanguages();
		System.out.println("zostalo jednostek: " + rawWikiUnits.size());
		clearUselessElemenets();
		System.out.println("zostalo jednostek: " + rawWikiUnits.size());
		dict.setRawWikiUnits(new ArrayList<>(rawWikiUnits));
		rawWikiUnits = null;
	}

	/**
	 * Usuwa elementy niewykorzystywane w projekcie do zmniejszenia rozmiaru
	 * bazy danych.
	 */
	private void clearUselessElemenets() {
		StopWatch watch = new StopWatch(this.getClass(), "removing useless data");
		watch.start();
		Pattern p = Pattern.compile("\\n");

		String wymowa = "{{wymowa";
		String uwagi = "{{uwagi";
		String tlumaczenia = "{{tłumaczenia";
		String zrodla = "{{źródła";
		String podobne = "{{podobne";
		String toc = "__TOC__";
		String etymologia = "{{etymologia";
		String dopracowac = "{{dopracować";

		List<String> result = new ArrayList<>();
		for (RawWikiUnit ru : rawWikiUnits) {
			String text = ru.getText();

			String[] lines = p.split(text);

			for (String line : lines) {
				if (line.startsWith("*") || line.startsWith("[[") || line.startsWith(wymowa) || line.startsWith(uwagi) || line.startsWith(tlumaczenia) || line.startsWith(zrodla)
						|| line.startsWith(podobne) || line.startsWith(toc) || line.startsWith(dopracowac))
					continue;

				result.add(line);
			}

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < (result.size() - 1); i++) {
				if (result.get(i).startsWith(etymologia)) {
					break;
				}
				sb.append(result.get(i)).append("\n");
			}

			ru.setText(sb.toString().trim());
			result.clear();
		}

		watch.stop();
	}

	/**
	 * Usuwa wszystkie dane w innych jezykach niz polski.
	 */
	private void clearForeignLanguages() {
		StopWatch watch = new StopWatch(this.getClass(), "removing foreign languages");
		watch.start();
		Pattern pattern = Pattern.compile("\\n[\\n]+");

		Set<RawWikiUnit> empty = new LinkedHashSet<>();

		for (RawWikiUnit rawWikiUnit : rawWikiUnits) {
			String text = rawWikiUnit.getText().trim();

			String[] separated = pattern.split(text);
			for (String s : separated) {
				if (s.contains("{{język polski}}")) {
					rawWikiUnit.setText(s);
					break;
				} else {
					rawWikiUnit.setText("");
				}
			}

			if (!rawWikiUnit.getText().contains("{{znaczenia}}") || StringUtils.isBlank(rawWikiUnit.getText())) {
				empty.add(rawWikiUnit);
			}
		}

		LOG.info("Number of empty raw units: " + empty.size() + "\n");
		rawWikiUnits.removeAll(empty);
		watch.stop();
	}

	/**
	 * Clears all elements which Wiktionary is using as page templates.
	 */
	private void clearForms() {
		StopWatch watch = new StopWatch(this.getClass(), "removing wiki form pages");
		watch.start();
		for (RawWikiUnit ru : rawWikiUnits) {
			if (!ru.getText().contains("{{znaczenia}}")) // jezeli nie zawiera
				// tagu
				// {{znaczenia}} nie
				// jest jednostka
				// slownikowa
				trashUnits.add(ru);
			if (ru.getTitle().contains(":")) // usun szblony wiki
				trashUnits.add(ru);
		}

		LOG.info("removed: " + trashUnits.size() + " trash units.");

		rawWikiUnits.removeAll(trashUnits);
		trashUnits = null;
		watch.stop();
	}

	/**
	 * Temporary container of raw wiki units.
	 */
	private Set<RawWikiUnit> rawWikiUnits;
	/**
	 * Container for removed units.
	 */
	private Set<RawWikiUnit> trashUnits;
	/**
	 * Reference to main dictionary
	 */
	private final Dictionary dict;
}
