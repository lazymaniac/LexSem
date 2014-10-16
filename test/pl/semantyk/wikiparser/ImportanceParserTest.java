package pl.semantyk.wikiparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import pl.semantyk.domain.WikiUnit;

public class ImportanceParserTest extends AbstractWikiParserTest {

	private ImportanceParser objUnderTest;
	
	private List<String> testData = new ArrayList<>(Arrays.asList("{{znaczenia}}",
			"''rzeczownik, rodzaj męski''",
			": (1.1) [[budynek]] [[warowny]]; {{wikipedia|zamek (architektura)}}",
			": (1.2) [[mechanizm]] [[zamykać|zamykający]] [[drzwi]], [[szuflada|szuflady]]; {{wikipedia|zamek (urządzenie)}}"));
	
	private final String RESULT_0_0 = "[[budynek]] [[warowny]]; {{zamek (architektura)}}";
	private final String RESULT_0_1 = "[[mechanizm]] [[zamykać|zamykający]] [[drzwi]], [[szuflada|szuflady]]; {{zamek (urządzenie)}}";
	
	@Before
	public void setUp() {
		objUnderTest = new ImportanceParser();
	}
	
	@Test
	public void parseTest() throws Exception {
		WikiUnit testUnit = createTestUnit();
		objUnderTest.parse(testUnit, testData);
		
		System.out.println(testUnit);
		assertThat(testUnit.getImportance(0, 0).getImportance(), is(RESULT_0_0));
		assertThat(testUnit.getImportance(0, 1).getImportance(), is(RESULT_0_1));
	}
}
