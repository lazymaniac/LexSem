package pl.semantyk.wikiparser;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import pl.semantyk.domain.RawWikiUnit;
import pl.semantyk.main.Dictionary;
import pl.semantyk.utils.StopWatch;

/**
 * Przepisuje slownik dictionary do nowego pliku. Usuwa niepotrzebne dane.
 */
public class WiktionaryXMLExporter {

	private static final Logger LOG = Logger
			.getLogger(WiktionaryXMLExporter.class);

	private String absolutePath;
	private final String outputFilename = "clear_wiktionary.xml";
	private Set<RawWikiUnit> rawUnits;
	/**
	 * referencja do glownego slownika.
	 */
	private final Dictionary dict;
	/**
	 * Domyślne wyjscie.
	 */
	private PrintWriter outStream;

	public WiktionaryXMLExporter(Dictionary aDict) {
		this.dict = aDict;
		rawUnits = new LinkedHashSet<>(dict.getRawWikiUnits());
		absolutePath = System.getProperty("user.dir");
		absolutePath += "/database/";
	}

	/**
	 * zapisuje baze danych do nowego pliku.
	 */
	public void printDatabse() {
		StopWatch watch = new StopWatch(this.getClass(),
				"rewriting dictionary to new file");
		watch.start();
		openOutputStream(absolutePath + outputFilename);

		outStream.println("<?xml version=\"1.0\"?>\n<wiki>");

		for (RawWikiUnit ru : rawUnits) {
			if (ru.getTitle().contains(":"))
				continue;
			outStream.print("<page>\n");
			outStream.print("<title>" + ru.getTitle() + "</title>\n");
			outStream.print("<text>\n");
			outStream.print(ru.getText() + "\n");
			outStream.print("</text>\n");
			outStream.print("</page>\n\n");
		}

		outStream.println("</wiki>");
		outStream.close();

		LOG.info("Saved: " + rawUnits.size() + "raw units");
		watch.stop();

		rawUnits = null;
	}

	private PrintWriter openOutputStream(final String file) {
		try {
			outStream = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			LOG.debug("Error while saving to file");
			LOG.debug(e);
		}

		return outStream;
	}
}
