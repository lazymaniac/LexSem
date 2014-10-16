package pl.semantyk.wikiparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import pl.semantyk.domain.WikiUnit;

public class CognatesParserTest extends AbstractWikiParserTest {

	private CognatesParser objUnderTest;
	
	private List<String> testData = new ArrayList<>(Arrays.asList("{{pokrewne}}",
							": {{czas}} [[odczytać]], [[rozczytywać się]], [[wczytać się]], [[wyczytać]], [[zaczytać]], [[zaczytywać się]]",
							": {{rzecz}} [[czytanie]] {{n}}, [[czytelnia]] {{f}}, [[czytelnik]] {{m}}, [[czytelnictwo]] {{n}}, [[czytanka]] {{f}}, [[czytadło]] {{n}}, [[czytelność]]",
							": {{przym}} [[czytelny]]",
							": {{przysł}} [[czytelnie]]"));

	private final String RESULT_POS1 = "czas";
	private final String RESULT_POS2 = "rzecz";
	
	private final String RESULT_COG1 = "[[odczytać]]";
	private final String RESULT_COG2 = "[[czytanie]]";
	
	
	@Before
	public void setUp() {
		objUnderTest = new CognatesParser();
	}
	
	@Test
	public void parseTest() {
		WikiUnit testUnit = createTestUnit();
		objUnderTest.parse(testUnit, testData);
		System.out.println(testUnit);
		
		assertThat(testUnit.getImportance(0, 0).getCognates().get(0).getCognate(), is(RESULT_COG1));
		assertThat(testUnit.getImportance(0, 0).getCognates().get(0).getPartOfSpeech(), is(RESULT_POS1));
		assertThat(testUnit.getImportance(0, 0).getCognates().get(6).getCognate(), is(RESULT_COG2));
		assertThat(testUnit.getImportance(0, 0).getCognates().get(6).getPartOfSpeech(), is(RESULT_POS2));
	}

}