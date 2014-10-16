package pl.semantyk.wordnetparser;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import pl.semantyk.domain.RelationType;
import pl.semantyk.domain.WnUnit;
import pl.semantyk.main.Dictionary;
import pl.semantyk.parse.entities.LexicalRelRaw;
import pl.semantyk.parse.entities.SynsetRaw;
import pl.semantyk.parse.entities.SynsetRelationRaw;
import pl.semantyk.utils.StopWatch;

/**
 * Saves different types of data to separate files.
 */
public class WordnetXMLExporter implements ISegmentable, Runnable {

	private static final Logger LOG = Logger
			.getLogger(WordnetXMLExporter.class);

	/**
	 * Types of elements.
	 */
	private enum Element {
		/**
		 * lexical-unit.
		 */
		LU(0),
		/**
		 * synset.
		 */
		S(1),
		/**
		 * relationtypes.
		 */
		RT(2),
		/**
		 * lexicalrelations.
		 */
		LR(3),
		/**
		 * synsetrelations.
		 */
		SR(4);

		private Element(int index) {
			this.index = index;
		}

		public int index;
	}

	/**
	 * Initializes data.
	 * 
	 * @param aDict
	 *            reference to main dictionary.
	 */
	public WordnetXMLExporter(final Dictionary aDict) {
		this.dict = aDict;
		outputFiles = null;

		init();
	}

	@Override
	public void run() {
		printToSeparateFiles();
	}

	@Override
	public final void setOutputFiles(final ArrayList<String> outputs) {
		this.outputFiles = outputs;
	}

	/**
	 * Ivokes metohods that saves data to separate files.
	 */
	@Override
	public final void printToSeparateFiles() {
		if (outputFiles == null) {
			generateOutputFiles();
		}

		LOG.info("\n -------------------- Saving to XML files ----------------");
		StopWatch watch = new StopWatch(this.getClass(), "save in progress...");
		watch.start();
		printLexicalUnits();
		printSynsets();
		printRelationTypes();
		printLexicalRelations();
		printSynsetRelations();
		printAngIds();
		watch.stop();
	}

	/**
	 * Initializes private variables data from dictionary.
	 */
	private void init() {
		this.wnUnits = dict.getWnUnits();
		this.synsetList = dict.getSynsetRaws();
		this.relationTypes = dict.getRelationTypes();
		this.lexicalRelations = dict.getLexicalRelRaws();
		this.synsetRelations = dict.getSynsetRelations();
		this.engUnitsIDs = dict.getEngUnitsIDs();

		/*
		 * Number of types of element to parse.
		 */
		int elementsCount = 5;
		elementsName = new ArrayList<>(elementsCount);

		elementsName.add(Element.LU.index, "lexical-unit");
		elementsName.add(Element.S.index, "synset");
		elementsName.add(Element.RT.index, "relationtypes");
		elementsName.add(Element.LR.index, "lexicalrelations");
		elementsName.add(Element.SR.index, "synsetrelations");
	}

	/**
	 * Generate a list of paths to output files. If user set specified list of
	 * output files this method is ignored.
	 */
	private void generateOutputFiles() {
		outputFiles = new ArrayList<>();
		String absolutePath = System.getProperty("user.dir");
		for (String e : elementsName) {
			outputFiles.add(absolutePath + "/output/" + e + "_parsed.xml");
		}
	}

	// TODO wnUnitSynRel list.

	/**
	 * Saves to file lexical units.
	 */
	private void printLexicalUnits() {
		openOutputStream(outputFiles.get(Element.LU.index));

		LOG.info("Number of WN units : " + wnUnits.size());

		for (WnUnit l : wnUnits) {
			String temp;
			temp = "<lexical-unit id=\"" + l.getId() + "\" name=\""
					+ l.getName() + "\" pos=\"" + l.getPosition()
					+ "\" domain=\"" + l.getDomain() + "\" variant=\""
					+ l.getVariant() + "\"/>";
			outStream.print(temp + "\n");
		}
		LOG.info("WN units saved to file : "
				+ outputFiles.get(Element.LU.index) + "\n");
		outStream.close();
	}

	/**
	 * Print to file synsets.
	 */
	private void printSynsets() {
		openOutputStream(outputFiles.get(Element.S.index));

		LOG.info("Number of synsets : " + synsetList.size());

		for (SynsetRaw s : synsetList) {
			String temp;

			temp = "<synset id=\"" + s.getId() + "\" split=\"" + s.getSplit()
					+ "\" abstract=\"" + s.getAbstractsynstet() + "\">\n";
			temp += "</synset>\n";
			outStream.print(temp + "\n");
		}
		LOG.info("Synsets saved to file : " + outputFiles.get(Element.S.index)
				+ "\n");
		outStream.close();
	}

	/**
	 * Print to file types of relations.
	 */
	private void printRelationTypes() {
		openOutputStream(outputFiles.get(Element.RT.index));

		LOG.info("Number of relation types : " + relationTypes.size());

		for (RelationType r : relationTypes) {
			String temp;

			if (r.getName().contains("PWN")) {
				continue;
			}

			temp = "<relationtypes id=\"" + r.getId() + "\" type=\""
					+ r.getType() + "\" parent=\"" + r.getParent()
					+ "\" name=\"" + r.getName() + "\"\n\tdescription=\""
					+ r.getDescription() + "\"\n\tposstr=\"" + r.getPosstr()
					+ "\" display=\"" + r.getDisplay() + "\"\n\tshortcut=\""
					+ r.getAbbreviation() + "\" autoreverse=\""
					+ r.getAutoOdwracanie() + "\"/>\n";

			outStream.print(temp + "\n");
		}
		LOG.info("Relation types saved to : "
				+ outputFiles.get(Element.RT.index) + "\n");
		outStream.close();
	}

	/**
	 * Print lexical relations to file.
	 */
	private void printLexicalRelations() {
		openOutputStream(outputFiles.get(Element.LR.index));

		LOG.info("Number of lexical relations : " + lexicalRelations.size());
		for (LexicalRelRaw l : lexicalRelations) {
			String temp;

			temp = "<lexicalrealtions parent=\"" + l.getParent()
					+ "\" child=\"" + l.getChild() + "\" relation=\""
					+ l.getRelation() + "\"/>\n";

			outStream.print(temp);
		}
		LOG.info("Lexical relations saved to : "
				+ outputFiles.get(Element.LR.index) + "\n");
		outStream.close();
	}

	/**
	 * Print synset relations to file.
	 */
	private void printSynsetRelations() {
		openOutputStream(outputFiles.get(Element.SR.index));

		LOG.info("Number of synset relations : " + synsetRelations.size());
		for (SynsetRelationRaw s : synsetRelations) {
			String temp;

			temp = "<synsetrelations parent=\"" + s.getParent() + "\" child=\""
					+ s.getChild() + "\" relation=\"" + s.getRelation()
					+ "\" valid=\"" + s.getChecked() + "\"/>\n";

			outStream.print(temp);
		}
		LOG.info("Synset relations saved to : "
				+ outputFiles.get(Element.SR.index) + "\n");
		outStream.close();
	}

	/**
	 * Print to file english units IDs.
	 */
	private void printAngIds() {
		String absolutePath = System.getProperty("user.dir");
		openOutputStream(absolutePath + "/output/engUnitsIDs.txt");

		LOG.info("Number of deleted english units : " + engUnitsIDs.size());

		for (Integer s : engUnitsIDs) {
			outStream.println(s);
		}
		LOG.info("English units ids saved to : engUnitsIDs.txt\n");
		outStream.close();
	}

	/**
	 * Open output stream for specified file.
	 * 
	 * @param file
	 *            path to output file.
	 * @return initialized object of <code>PrintWriter</code> class.
	 */
	private PrintWriter openOutputStream(final String file) {
		try {
			outStream = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			LOG.info("Error while saving to file");
			e.printStackTrace();
		}

		return outStream;
	}

	/* <------------------------ Setters / Getters -----------------------< */

	/**
	 * @return the lexicalUnits
	 */
	public final List<WnUnit> getWnUnits() {
		return wnUnits;
	}

	/**
	 * @param wnUnits
	 *            the lexicalUnits to set
	 */
	public final void setWnUnits(List<WnUnit> wnUnits) {
		this.wnUnits = wnUnits;
	}

	/**
	 * @return the synsetList
	 */
	public final List<SynsetRaw> getSynsetListRaw() {
		return synsetList;
	}

	/**
	 * @param synsetList
	 *            the synsetList to set
	 */
	public final void setSynsetListRaw(List<SynsetRaw> synsetList) {
		this.synsetList = synsetList;
	}

	/**
	 * @return the lexicalRelations
	 */
	public final List<LexicalRelRaw> getLexicalRelationsRaw() {
		return lexicalRelations;
	}

	/**
	 * @param lexicalRelations
	 *            the lexicalRelations to set
	 */
	public final void setLexicalRelationsRaw(
			List<LexicalRelRaw> lexicalRelations) {
		this.lexicalRelations = lexicalRelations;
	}

	/**
	 * @return the synsetRelations
	 */
	public final List<SynsetRelationRaw> getSynsetRelationsRaw() {
		return synsetRelations;
	}

	/**
	 * @param synsetRelations
	 *            the synsetRelations to set
	 */
	public final void setSynsetRelationsRaw(
			List<SynsetRelationRaw> synsetRelations) {
		this.synsetRelations = synsetRelations;
	}

	/* <------------------------ Private variables -----------------------> */
	/**
	 * Collection of output files paths.
	 */
	private List<String> elementsName;
	/**
	 * Reference to main dictionary.
	 */
	private final Dictionary dict;

	/**
	 * Collection of lexical units.
	 */
	private List<WnUnit> wnUnits;
	/**
	 * Collection of synsets.
	 */
	private List<SynsetRaw> synsetList;
	/**
	 * Collection of relation types.
	 */
	private List<RelationType> relationTypes;
	/**
	 * Collection of lexical relations.
	 */
	private List<LexicalRelRaw> lexicalRelations;
	/**
	 * Collection of synset relations.
	 */
	private List<SynsetRelationRaw> synsetRelations;
	/**
	 * Collection of English lexical units IDs.
	 */
	private List<Integer> engUnitsIDs;

	/**
	 * Kolekcja nazw plików wyjściowych.
	 */
	private List<String> outputFiles;
	/**
	 * Domyślne wyjscie.
	 */
	private PrintWriter outStream;
}
