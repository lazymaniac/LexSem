package pl.semantyk.main;

import org.apache.log4j.*;
import pl.semantyk.database.DatabaseCreator;
import pl.semantyk.enums.TimeUnit;
import pl.semantyk.utils.PropertyProvider;
import pl.semantyk.utils.StopWatch;

import java.util.Collections;
import java.util.List;

/**
 * Main class.
 * 
 * @author Sebastian Fabisz
 */
public final class WNPMain {

	/**
	 * Private constructor. Protects form instantiating this class outside main
	 * method.
	 */
	private WNPMain() {
	}

	/**
	 * Creates Dictionary object and start parsing with options passed. /todo
	 * reading arguments from command line.
	 * 
	 * @param args
	 *            array of arguments read from console.
	 */
	public static void main(final String[] args) {
        //BasicConfigurator.configure();
        PropertyConfigurator.configure("src/main/resources/META-INF/log4j.properties");
        setupLogging();
		StopWatch watch = new StopWatch(WNPMain.class, "Program started.",
				TimeUnit.SECOND);
		watch.start();
		if (args.length < 1) {
			usage();
		}

		WNPMain main = new WNPMain();
		main.readArgs(args);

		if (setupDb) {
			DatabaseCreator dbCreator = new DatabaseCreator();
			dbCreator.setupDatabase();
		}

		Dictionary dict = new Dictionary();
		DictionaryBuilder builder = new DictionaryBuilder(dict);

		builder.processWiktionary(processWikt).processWordnet(processWn)
				.mergeDatabases(merge).saveResultToFiles(doSave)
				.normalize(doNorm).removeForeignUnits(doRmForeign);

		builder.buildDictionary();
		watch.stop();

	}

	@SuppressWarnings("unchecked")
	private static void setupLogging() {
		String logLvl = PropertyProvider.getProperty(PROP_LOG_LVL);
		BasicConfigurator.configure();
		List<Logger> loggers = Collections.<Logger> list(LogManager
				.getCurrentLoggers());
		loggers.add(LogManager.getRootLogger());
		for (Logger logger : loggers) {
			logger.setLevel(Level.toLevel(logLvl));
		}
	}

	private void readArgs(String[] args) {
		for (String s : args) {
			switch (s) {
			case SETUP_DB:
				setupDb = true;
				break;
			case PROCESS_WN:
				processWn = true;
				break;
			case PROCESS_WIKT:
				processWikt = true;
				break;
			case MERGE_DB:
				merge = true;
				break;
			case REMOVE_FOREIGN_UNITS:
				doRmForeign = true;
				break;
			case SAVE:
				doSave = true;
				break;
			default:
				usage();
				break;
			}
		}
	}

	/**
	 * Explains usage of this program in command line
	 */
	private static void usage() {
		System.out
				.println("Database .xml files should be placed in /database/ directory.\n\n"
						+ "Allowed parameters: \n" + "1. "
						+ SETUP_DB
						+ " Clear existing databse and create schema: "
						+ "\n"
						+ "2. "
						+ PROCESS_WN
						+ " Parse WordNet databse and persist: "
						+ "\n"
						+ "3. "
						+ PROCESS_WIKT
						+ " Parse Wiktionary database and persist: "
						+ "\n"
						+ "4. "
						+ MERGE_DB
						+ " Merge databases: "
						+ "\n\n"
						+ "Additional parameters: \n"
						+ "Remove foreign units from Wordnet: "
						+ REMOVE_FOREIGN_UNITS
						+ "\n"
						+ "Save output to separate files: "
						+ SAVE
						+ "\n\n"
						+ "Due the problems with JPA library and MySQL database it's recommended to run first with parameters 1 and 2, \n"
						+ "then second run with parameters 3 and 4.");
		System.exit(1);
	}

	private static boolean setupDb = false;
	private static boolean processWn = false;
	private static boolean processWikt = false;
	private static boolean merge = false;
	private static boolean doNorm = false;
	private static boolean doRmForeign = false;
	private static boolean doSave = false;

	private final static String REMOVE_FOREIGN_UNITS = "-rm-foreign";
	private final static String SAVE = "-save";
	private final static String SETUP_DB = "-setup-db";
	private final static String PROCESS_WN = "-process-wn";
	private final static String PROCESS_WIKT = "-process-wikt";
	private final static String MERGE_DB = "-merge-db";

	private final static String PROP_LOG_LVL = "logging.level";

}
