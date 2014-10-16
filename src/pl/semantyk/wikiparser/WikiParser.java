package pl.semantyk.wikiparser;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import pl.semantyk.domain.RawWikiUnit;
import pl.semantyk.main.Dictionary;
import pl.semantyk.utils.StopWatch;
import pl.semantyk.wordnetparser.Parsable;

public class WikiParser extends DefaultHandler implements Parsable, Runnable {

	private static final Logger LOG = Logger.getLogger(WikiParser.class);

	private StopWatch watch = null;

	public WikiParser(String filename, Dictionary aDict) {
		this.dict = aDict;
		this.xmlFile = filename;

		rawUnits = new LinkedHashSet<>(dict.getRawWikiUnits());
	}

	@Override
	public void run() {
		watch = new StopWatch(this.getClass(), "parsing Wiktionary");
		watch.start();
		setParserAndParse();
	}

	@Override
	public void parseDocument() {
		watch = new StopWatch(this.getClass(), "parsing Wiktionary");
		watch.start();
		setParserAndParse();
	}

	@Override
	public void setInputFile(String inputFile) {
		this.xmlFile = inputFile;
	}

	private void setParserAndParse() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);

		try {
			SAXParser parser = factory.newSAXParser();
			XMLReader xmlReader = parser.getXMLReader();
			xmlReader.setContentHandler(this);
			xmlReader.setErrorHandler(new ParserErrorHandler(new PrintStream(
					System.out)));
			xmlReader.parse(xmlFile);
		} catch (ParserConfigurationException e) {
			LOG.debug("Parseconfig error");
			LOG.debug(e);
		} catch (SAXException e) {
			LOG.debug(e);
			LOG.debug("SAXException: plik xml uszkodzony");
		} catch (IOException e) {
			LOG.debug("IO error: Missing plWiktionary.xml file i /database/ folder");
			LOG.debug(e);
		}
	}

	@Override
	public final void startElement(final String uri, final String name,
			final String elementName, final Attributes attr)
			throws SAXException {

		strBuild = new StringBuilder();
		if (elementName.equalsIgnoreCase("page")) {
			tempUnit = new RawWikiUnit();
		}
	}

	@Override
	public final void endElement(final String uri, final String name,
			final String element) throws SAXException {

		if (element.equalsIgnoreCase("title")) {
			tempUnit.setTitle(tempValue);
		}

		if (element.equalsIgnoreCase("text")) {
			tempUnit.setText(strBuild.toString());
		}

		if (element.equalsIgnoreCase("page")) {
			rawUnits.add(tempUnit);
			// print(tempUnit.getNazwa());
		}
	}

	@Override
	public final void endDocument() throws SAXException {

		dict.setRawWikiUnits(new ArrayList<>(rawUnits));
		rawUnits = null;
		LOG.debug("Znaleziono :" + dict.getRawWikiUnits().size()
				+ " jednostek.");
		watch.stop();
	}

	@Override
	public final void characters(final char[] ac, final int offset,
			final int count) throws SAXException {
		tempValue = new String(ac, offset, count);
		strBuild.append(tempValue);
	}

	/**
	 * Handles and exceptions coming form WnParser.
	 * 
	 * @author Sebastian Fabisz
	 */
	private static class ParserErrorHandler implements ErrorHandler {
		/**
		 * Default output.
		 */
		private final PrintStream output;

		/**
		 * Dafault constructor.
		 * 
		 * @param out
		 *            output of message.
		 */
		public ParserErrorHandler(final PrintStream out) {
			this.output = out;
		}

		/**
		 * Helping method. Parses an exception.
		 * 
		 * @param exception
		 *            throws exception.
		 * @return parsed message.
		 */
		private String parseException(final SAXParseException exception) {
			String systemID = exception.getSystemId();

			if (systemID == null) {
				systemID = "null";
			}

			return "URI = " + systemID + " Line = " + exception.getLineNumber()
					+ ": " + exception.getMessage();
		}

		@Override
		public void warning(final SAXParseException exception)
				throws SAXException {
			output.println("Warining: " + parseException(exception));
		}

		@Override
		public void error(final SAXParseException exception)
				throws SAXException {
			String message = "Error: " + parseException(exception);
			throw new SAXException(message);
		}

		@Override
		public void fatalError(final SAXParseException exception)
				throws SAXException {
			String message = "Fatal Error: " + parseException(exception);
			throw new SAXException(message);
		}
	}

	private String xmlFile;
	private String tempValue;
	private StringBuilder strBuild;
	private RawWikiUnit tempUnit;

	private Set<RawWikiUnit> rawUnits;

	/**
	 * Reference to main dictionary.
	 */
	private final Dictionary dict;
}
