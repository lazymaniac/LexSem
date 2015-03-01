package pl.semantyk.wikiparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pl.semantyk.domain.WikiUnit;

public class AntonymsParserTest extends AbstractWikiParserTest {

	private WikiRawParser objectUnderTest;

	private List<String> defaultSet;

	private List<String> emptySet;

	private final String[] data = new String[] {
            "{{antonimy}}",
			": (1.1) [[dom]]",
            ": (1.2) [[mieszkanie]]" };

	private final String[] empty = new String[] { "{{antonimy}}" };

	@Before
	public void setUp() {
		objectUnderTest = new AntonymsParser();
		generateInputData();
	}

	private void generateInputData() {
		defaultSet = new ArrayList<>(Arrays.asList(data));
		emptySet = new ArrayList<>(Arrays.asList(empty));
	}

	@Test
	public void parseTest() {
		WikiUnit testUnit = createTestUnit();
		objectUnderTest.parse(testUnit, defaultSet);

		Assert.assertTrue(testUnit.getPartsOfSpeech().get(0).getImportances()
				.get(0).getAntonyms().get(0).getAntonym().equals("kot"));
		Assert.assertTrue(testUnit.getPartsOfSpeech().get(0).getImportances()
				.get(1).getAntonyms().get(0).getAntonym().equals("suka"));

		for (int i = 0; i < testUnit.getPartsOfSpeech().size(); i++) {
			for (int j = 0; j < testUnit.getPartsOfSpeech().get(i)
					.getImportances().size(); j++) {
				if (i != 0 && (j != 0 || j != 1)) {
					Assert.assertTrue(testUnit.getPartsOfSpeech().get(i)
							.getImportances().get(j).getAntonyms().isEmpty());
				}
			}
		}
	}

	@Test
	public void parseEmptyTest() {
		WikiUnit testUnit = createTestUnit();
		objectUnderTest.parse(testUnit, emptySet);

		for (int i = 0; i < testUnit.getPartsOfSpeech().size(); i++) {
			for (int j = 0; j < testUnit.getPartsOfSpeech().get(i)
					.getImportances().size(); j++) {
				Assert.assertTrue(testUnit.getPartsOfSpeech().get(i)
						.getImportances().get(j).getAntonyms().isEmpty());
			}
		}
	}

	@Test
	public void perseBrokenTest() {
		WikiUnit testUnit = createTestUnit();
		objectUnderTest.parse(testUnit, emptySet);

		for (int i = 0; i < testUnit.getPartsOfSpeech().size(); i++) {
			for (int j = 0; j < testUnit.getPartsOfSpeech().get(i)
					.getImportances().size(); j++) {
				Assert.assertTrue(testUnit.getPartsOfSpeech().get(i)
						.getImportances().get(j).getAntonyms().isEmpty());
			}
		}
	}
}
