package pl.semantyk.main;

import org.apache.log4j.Logger;

import pl.semantyk.database.WiktionaryToDBExporter;
import pl.semantyk.database.WordnetToDBExporter;
import pl.semantyk.database.DatabaseMerger;
import pl.semantyk.enums.DatabaseType;
import pl.semantyk.exceptions.SystemException;
import pl.semantyk.utils.PropertyProvider;
import pl.semantyk.wikiparser.WikiCleaner;
import pl.semantyk.wikiparser.WikiParser;
import pl.semantyk.wikiparser.WikiRawDataFormatter;
import pl.semantyk.wikiparser.WiktionaryXMLExporter;
import pl.semantyk.wordnetparser.EnglishUnitsFilter;
import pl.semantyk.wordnetparser.Normalizer;
import pl.semantyk.wordnetparser.WordnetParser;
import pl.semantyk.wordnetparser.WordnetXMLExporter;

import static pl.semantyk.enums.DatabaseType.WIKTIONARY;
import static pl.semantyk.enums.DatabaseType.WORDNET;

/**
 * Builder working on Dictionary object. Configured in main method.
 *
 * @author Sebastian Fabisz
 */
public class DictionaryBuilder {

	private final static Logger LOG = Logger.getLogger(DictionaryBuilder.class);

	private boolean PROCESS_WN = false;
	private boolean PROCESS_WIKT = false;
	private boolean MERGE_DB = false;
	private boolean REMOVE_UNITS = false;
	private boolean NORMALIZE = false;
	private boolean SAVE = false;

	private Dictionary dictionary;

	private String absolutePath = "";

	private WordnetParser wnParser;

	private WikiParser wikiParser;

	private EnglishUnitsFilter englishUnitsFilter;

	private WordnetXMLExporter wordnetXMLExporter;

	private Normalizer normalizer;

	private WordnetToDBExporter wnDbExporter;

	private WiktionaryToDBExporter wkDbExporter;

	// <------------------------ THREADS ----------------------->
	/**
	 * Wordnet parser thread.
	 */
	private Thread wnParserThread;

	/**
	 * Wikiparser thread.
	 */
	private Thread wikiParserThread;

	/**
	 * Wordnet english units filter thread.
	 */
	private Thread wnEnglishUnitsFilterThread;

	/**
	 * Index normalizer thread.
	 */
	private Thread normThread;

	// <------------------------- FILE LOCATIONS ---------------->
	private String wnDumpFilename = "plwordnet_2_0.xml";

	private final String WN_FILENAME_PROP = "wordnet.filename";

	private String wikiFilename = "plwiktionary.xml";

	private final String WIKI_FILENAME_PROP = "wiktionary.filename";

	public DictionaryBuilder(Dictionary dictionary) {
		wnDumpFilename = PropertyProvider.getProperty(WN_FILENAME_PROP);
		wikiFilename = PropertyProvider.getProperty(WIKI_FILENAME_PROP);
		this.dictionary = dictionary;
		absolutePath = System.getProperty("user.dir");
		LOG.info("Absolute path: " + absolutePath);
		absolutePath += "/database/";
	}

	/**
	 * Main method of class Dictionary. Checks all flags and invoke necessary
	 * methods corresponding to each flag.
	 */
	public final void buildDictionary() {

		if (PROCESS_WIKT) {
			buildWiktionaryDatabase();
			saveDictionaryToDatabase(WIKTIONARY);
		}

		if (PROCESS_WN) {
			buildWordNetDatabase();
			saveDictionaryToDatabase(WORDNET);
		}

		if (SAVE)
			exportDictionary();

		if (MERGE_DB)
			mergeDatabases();

	}

	private void saveDictionaryToDatabase(DatabaseType databaseType) {

		if (databaseType.equals(WORDNET)) {
			if (wnDbExporter == null) {
				wnDbExporter = new WordnetToDBExporter(dictionary);
			}

			wnDbExporter.exportDatabase(databaseType);
		}

		if (databaseType.equals(WIKTIONARY)) {
			if (wkDbExporter == null) {
				wkDbExporter = new WiktionaryToDBExporter(dictionary);
			}

			wkDbExporter.exportDatabase();
		}

	}

	private void mergeDatabases() {
		try {
			if (wnParserThread != null)
				wnParserThread.join();
			if (wikiParserThread != null)
				wikiParserThread.join();
			if (wnEnglishUnitsFilterThread != null)
				wnEnglishUnitsFilterThread.join();
			if (normThread != null)
				normThread.join();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		DatabaseMerger merger = new DatabaseMerger();
		merger.merge();
	}

	private void exportDictionary() {
		WiktionaryXMLExporter fileExporter = new WiktionaryXMLExporter(
				dictionary);
		fileExporter.printDatabse();
	}

	private void buildWordNetDatabase() {
		if (wnParser == null) {
			wnParser = new WordnetParser(absolutePath + wnDumpFilename,
					dictionary);
		}

		wnParser.parseDocument();

		if (REMOVE_UNITS) {
			clearEnglishUnits();
		}

		if (NORMALIZE) {
			normalizeData();
		}

		if (SAVE) {
			exportToXMLFiles(WORDNET);
		}
	}

	private void buildWiktionaryDatabase() {

		if (wikiParser == null) {
			wikiParser = new WikiParser(absolutePath + wikiFilename, dictionary);
		}

		wikiParser.parseDocument();

		WikiCleaner wikiCleaner = new WikiCleaner(dictionary);
		wikiCleaner.clearUnits();

		WikiRawDataFormatter wikiRawDataFormatter = new WikiRawDataFormatter(
				dictionary);
		wikiRawDataFormatter.format();

		if (SAVE) {
			exportToXMLFiles(WIKTIONARY);
		}
	}

	/**
	 * Configure builder to remove all units in foreign languages.
	 * 
	 * @param removeForeign
	 * @return configured instance of builder
	 */
	public final DictionaryBuilder removeForeignUnits(
			final boolean removeForeign) {
		REMOVE_UNITS = removeForeign;
		return this;
	}

	/**
	 * Configure builder to parse and export Wiktionary database.
	 * 
	 * @param processWikt
	 * @return configured instance of builder
	 */
	public final DictionaryBuilder processWiktionary(boolean processWikt) {
		PROCESS_WIKT = processWikt;
		return this;
	}

	/**
	 * Configure builder to parse and export Wordnet database.
	 * 
	 * @param processWn
	 * @return configured instance of builder
	 */
	public final DictionaryBuilder processWordnet(boolean processWn) {
		PROCESS_WN = processWn;
		return this;
	}

	/**
	 * Configure builder to run merging script.
	 * 
	 * @param merge
	 * @return configured instance of builder
	 */
	public final DictionaryBuilder mergeDatabases(boolean merge) {
		MERGE_DB = merge;
		return this;
	}

	/**
	 * Configure builder to normalize indexes of parsed units before persisting
	 * in database.
	 * 
	 * @param doNormalize
	 *            if true normalize.
	 * @return configured instance of builder
	 */
	public final DictionaryBuilder normalize(final boolean doNormalize) {
		NORMALIZE = doNormalize;
		return this;
	}

	/**
	 * Configure builder to export processed data to XML files.
	 * 
	 * @param doSave
	 *            if true save to separate files.
	 * @return configured instance of builder
	 */
	public final DictionaryBuilder saveResultToFiles(final boolean doSave) {
		SAVE = doSave;
		return this;
	}

	/**
	 * Clears the English jednostki form Dictionary data set. If
	 * EnglishUnitFilter object reference contains null create default object.
	 */
	private void clearEnglishUnits() {
		if (englishUnitsFilter == null) {
			englishUnitsFilter = new EnglishUnitsFilter(dictionary);
		}

		try {
			englishUnitsFilter.clearUnits();
		} catch (SystemException e) {
			LOG.debug(e);
		}
	}

	/**
	 * Normalize data in Dictionary. If Normalizer object reference contains
	 * null create default object
	 */
	private void normalizeData() {
		if (normalizer == null) {
			normalizer = new Normalizer(dictionary);
		}

		normalizer.doNormalize();
	}

	/**
	 * Save different types of data to separate files. database Tool needs this
	 * files form filling database.
	 */
	private void exportToXMLFiles(DatabaseType databaseType) {
		if (wordnetXMLExporter == null) {
			wordnetXMLExporter = new WordnetXMLExporter(dictionary);
		}

		WiktionaryXMLExporter fileService = new WiktionaryXMLExporter(
				dictionary);
		fileService.printDatabse();

		Thread segThread = new Thread(wordnetXMLExporter);
		segThread.start();
	}

	/**
	 * Sets configured WnParser object. If WnParser needs to be default don't
	 * pass anything.
	 * 
	 * @param p
	 *            Reference to configured parsed.
	 */
	public final void setSAXParser(final WordnetParser p) {
		this.wnParser = p;
	}

	/**
	 * Sets configured EnglishUnitFilter object. If EnglishUnitFilter needs to
	 * be default don't pass anything.
	 * 
	 * @param aEuf
	 *            reference to EnglishUnitFilter.
	 */
	public final void setEUF(final EnglishUnitsFilter aEuf) {
		this.englishUnitsFilter = aEuf;
	}

	/**
	 * Sets configured Segmenter object. If Segmenter needs to be default don't
	 * pass anything.
	 * 
	 * @param aSeg
	 *            reference to Segmenter.
	 */
	public final void setSegmenter(final WordnetXMLExporter aSeg) {
		this.wordnetXMLExporter = aSeg;
	}

	public Dictionary getDictionary() {
		return dictionary;
	}

	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	public WordnetParser getWNparser() {
		return wnParser;
	}

	public void setWNparser(WordnetParser WNparser) {
		this.wnParser = WNparser;
	}

	public WikiParser getWikiParser() {
		return wikiParser;
	}

	public void setWikiParser(WikiParser wikiParser) {
		this.wikiParser = wikiParser;
	}

	public EnglishUnitsFilter getEuf() {
		return englishUnitsFilter;
	}

	public void setEuf(EnglishUnitsFilter euf) {
		this.englishUnitsFilter = euf;
	}

	public WordnetXMLExporter getWordnetXMLExporter() {
		return wordnetXMLExporter;
	}

	public Normalizer getNormalizer() {
		return normalizer;
	}

	public void setNormalizer(Normalizer normalizer) {
		this.normalizer = normalizer;
	}

	public WordnetToDBExporter getExporter() {
		return wnDbExporter;
	}

	public void setExporter(WordnetToDBExporter exporter) {
		this.wnDbExporter = exporter;
	}

	public String getWordNetDbfilename() {
		return wnDumpFilename;
	}

	public String getWikiFilename() {
		return wikiFilename;
	}

}
