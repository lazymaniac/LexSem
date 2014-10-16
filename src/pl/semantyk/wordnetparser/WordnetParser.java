package pl.semantyk.wordnetparser;

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

import pl.semantyk.domain.RelationType;
import pl.semantyk.domain.WnUnit;
import pl.semantyk.domain.WnUnitSynsetRel;
import pl.semantyk.main.Dictionary;
import pl.semantyk.parse.entities.LexicalRelRaw;
import pl.semantyk.parse.entities.SynsetRaw;
import pl.semantyk.parse.entities.SynsetRelationRaw;
import pl.semantyk.utils.StopWatch;

/**
 * Main parser of plWordNet database saved in .xml file. It use reference to
 * main dictionary to fill readed data to corresponding collections. Class
 * extends DeaultHandler for SAXParser.
 * 
 * @author Sebastian Fabisz
 * @version 0.9
 */
public class WordnetParser extends DefaultHandler implements Parsable, Runnable {

	private static final Logger LOG = Logger.getLogger(WordnetParser.class);

	private StopWatch watch = null;

	/**
	 * Default construct initializes all necessary data.
	 * 
	 * @param filename
	 *            path to plWordNet database.
	 * @param aDict
	 *            reference to main dicitonary.
	 */
	public WordnetParser(final String filename, final Dictionary aDict) {
		xmlFile = filename;
		this.dict = aDict;

		/*
		 * Number of type of elements.
		 */
		int numOfElements = 5;
		elementTypes = new String[numOfElements];
		initTypesVec();

		engUnitsIDs = new LinkedHashSet<>();

		/*
		 * Array of elements name.
		 */
		wnUnits = new LinkedHashSet<>();
		synsetList = new LinkedHashSet<>();
		relationTypes = new LinkedHashSet<>();
		lexicalRelations = new LinkedHashSet<>();
		synsetRelations = new LinkedHashSet<>();
		wnUnitSynsetRels = new LinkedHashSet<>();

		watch = new StopWatch(this.getClass(), "Parsing wordnet...");
		watch.start();
	}

	/**
	 * Initalize array of element types.
	 */
	private void initTypesVec() {
		elementTypes[0] = "lexical-unit";
		elementTypes[1] = "synset";
		elementTypes[2] = "relationtypes";
		elementTypes[3] = "lexicalrelations";
		elementTypes[4] = "synsetrelations";
	}

	@Override
	public void run() {
		setParserAndParse();
	}

	@Override
	public final void parseDocument() {
		setParserAndParse();
	}

	@Override
	public final void setInputFile(final String inputFile) {
		this.xmlFile = inputFile;
	}

	/**
	 * Method use a SAXParserFactory to obtain a SAXParser object. Then set up a
	 * XMLReader and start parsing.
	 */
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
			LOG.info("Parse config error");
			LOG.debug(e.getCause());
		} catch (SAXException e) {
			LOG.info("SAXException: file is incorrect");
			LOG.debug(e.getCause());
		} catch (IOException e) {
			LOG.info("IO error: Missing plWordNet file i /database/ folder");
			LOG.debug(e.getCause());
			System.exit(-1);
		}
	}

	@Override
	public final void startElement(final String uri, final String name,
			final String elementName, final Attributes attr)
			throws SAXException {
		/*
		 * Sprawdź jaki elementy występują w pliku. if( "".equals(uri)) { if
		 * (!elements.contains(elementName)){ elements.add(elementName); } }
		 * else { if (!elements.contains(name)){ elements.add(name); } }
		 */
		if (elementName.equalsIgnoreCase("lexical-unit")) {
			createLexUnit(attr);
		} else if (elementName.equalsIgnoreCase("synset")) {
			createSynset(attr);
		} else if (elementName.equalsIgnoreCase("unit-id")) {
			createJednWnSynRel();
		} else if (elementName.equalsIgnoreCase("relationtypes")) {
			createRelationType(attr);
		} else if (elementName.equalsIgnoreCase("lexicalrelations")) {
			createLexRelation(attr);
		} else if (elementName.equalsIgnoreCase("synsetrelations")) {
			createSynsetRelation(attr);
		}

	}

	/**
	 * Odczytuje atrybuty obiektu jednostki leksykalnej. Jeżeli jednostka o
	 * takiej samej nazwe znajduje się już w kolekcji jednostki w słownik to
	 * łączy je razem, w przeciwnym przypadku tworzy nową,.
	 * 
	 * @param attr
	 *            lista atrybutów.
	 */
	private void createLexUnit(final Attributes attr) {
		tempUnit = new WnUnit();
		tempUnit.setId(Integer.parseInt(attr.getValue("id")));
		tempUnit.setName(attr.getValue("name"));
		tempUnit.setDomian(attr.getValue("domain"));
		tempUnit.setPosition(attr.getValue("pos"));
		tempUnit.setVariant(Integer.parseInt(attr.getValue("variant")));
	}

	/**
	 * Creates and fills Synset temporary object, recently parsed form file.
	 * 
	 * @param attr
	 *            list of attributes.
	 */
	private void createSynset(final Attributes attr) {
		tempSynset = new SynsetRaw();
		String temp = attr.getValue("id");
		tempSynset.setId(Integer.parseInt(temp));
		tempSynset.setAbstractsynstet(Boolean.parseBoolean(attr
				.getValue("abstract")));
		tempSynset.setSplit(Integer.parseInt(attr.getValue("split")));
	}

	/**
	 * Tworzy nową instancję JednWnSynRel.
	 */
	private void createJednWnSynRel() {
		tempWnUnitSynsetRel = new WnUnitSynsetRel();
	}

	/**
	 * Creates and fills RelationType temporary object, recently parsed form
	 * file.
	 * 
	 * @param attr
	 *            list of attributes.
	 */
	private void createRelationType(final Attributes attr) {
		tempRelationType = new RelationType();
		tempRelationType.setId(Integer.parseInt(attr.getValue("id")));
		tempRelationType.setType(attr.getValue("type"));

		String temp = attr.getValue("parent");
		if (temp == null) {
			tempRelationType.setParent(-1);
		} else {
			tempRelationType.setParent(Integer.parseInt(temp));
		}
		tempRelationType.setName(attr.getValue("name"));
		tempRelationType.setDescription(attr.getValue("description"));
		tempRelationType.setPosstr(attr.getValue("posstr"));
		tempRelationType.setDisplay(attr.getValue("display"));
		tempRelationType.setAbbreviation(attr.getValue("shortcut"));
		tempRelationType.setAutoOdwracanie(Boolean.parseBoolean(attr
				.getValue("autoreverse")));
	}

	/**
	 * Creates and fills RelacjaLeksykalna temporary object, recently parsed
	 * form file.
	 * 
	 * @param attr
	 *            list of attributes..
	 */
	private void createLexRelation(final Attributes attr) {
		tempLexicalRelation = new LexicalRelRaw();
		tempLexicalRelation.setChild(Integer.parseInt(attr.getValue("child")));
		tempLexicalRelation
				.setParent(Integer.parseInt(attr.getValue("parent")));
		tempLexicalRelation.setRelation(Integer.parseInt(attr
				.getValue("relation")));
	}

	/**
	 * Creates and fills Synset temporary object, recently parsed form file.
	 * 
	 * @param attr
	 *            list of attributes..
	 */
	private void createSynsetRelation(final Attributes attr) {
		tempSynsetRelation = new SynsetRelationRaw();
		tempSynsetRelation.setChild(Integer.parseInt(attr.getValue("child")));
		String temp = attr.getValue("parent");
		if (temp.equals("")) {
			tempSynsetRelation.setParent(-1);
		} else {
			tempSynsetRelation.setParent(Integer.parseInt(temp));
		}
		tempSynsetRelation.setRelation(Integer.parseInt(attr
				.getValue("relation")));
		tempSynsetRelation.setChecked(Boolean.parseBoolean(attr
				.getValue("valid")));
	}

	@Override
	public final void endElement(final String uri, final String name,
			final String element) throws SAXException {
		if (element.equalsIgnoreCase("lexical-unit")) {
			wnUnits.add(tempUnit);
		}

		if (element.equalsIgnoreCase("unit-id")) {
			tempWnUnitSynsetRel.setWnUnitId(Integer.parseInt(tempValue));
			tempWnUnitSynsetRel.setSynsetId(tempSynset.getId());
			wnUnitSynsetRels.add(tempWnUnitSynsetRel);
		}

		if (element.equalsIgnoreCase("synset")) {
			synsetList.add(tempSynset);
		}

		if (element.equalsIgnoreCase("relationtypes")) {
			relationTypes.add(tempRelationType);
		}

		if (element.equalsIgnoreCase("lexicalrelations")) {
			lexicalRelations.add(tempLexicalRelation);
		}

		if (element.equalsIgnoreCase("synsetrelations")) {
			synsetRelations.add(tempSynsetRelation);
		}
	}

	@Override
	public final void characters(final char[] ac, final int offset,
			final int count) throws SAXException {
		tempValue = new String(ac, offset, count);
	}

	@Override
	public final void endDocument() throws SAXException {
		LOG.info("Parsing finished. Number of WordNet lexical units parsed: "
				+ wnUnits.size() + "\n");
		LOG.info("relationTypes size: " + relationTypes.size());
		dict.setWnUnits(new ArrayList<>(wnUnits));
		dict.setSynsetRaws(new ArrayList<>(synsetList));
		dict.setRelationTypes(new ArrayList<>(relationTypes));
		dict.setSynsetRelations(new ArrayList<>(synsetRelations));
		dict.setLexicalRelRaws(new ArrayList<>(lexicalRelations));
		dict.setEngUnitsIDs(new ArrayList<>(engUnitsIDs));
		dict.setWnUnitSynsetRels(wnUnitSynsetRels);
		watch.stop();

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

	/* <--------------- Private variables ------------------------> */

	private final Dictionary dict;

	private final Set<Integer> engUnitsIDs;

	private final Set<WnUnit> wnUnits;
	private final Set<SynsetRaw> synsetList;
	private final Set<RelationType> relationTypes;
	private final Set<LexicalRelRaw> lexicalRelations;
	private final Set<SynsetRelationRaw> synsetRelations;
	private final Set<WnUnitSynsetRel> wnUnitSynsetRels;
	private String xmlFile;
	private String tempValue;
	private WnUnit tempUnit;
	private SynsetRaw tempSynset;
	private RelationType tempRelationType;
	private LexicalRelRaw tempLexicalRelation;
	private SynsetRelationRaw tempSynsetRelation;
	private WnUnitSynsetRel tempWnUnitSynsetRel;
	private final String[] elementTypes;

}
