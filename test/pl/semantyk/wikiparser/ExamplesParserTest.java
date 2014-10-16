package pl.semantyk.wikiparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Test;

import pl.semantyk.domain.WikiUnit;

public class ExamplesParserTest extends AbstractWikiParserTest {

	private ExamplesParser objUnderTest;
	
	private List<String> testData = new ArrayList<>(Arrays.asList("{{przykłady}}",
			": (1.1) ''[[hrabia|Hrabia]] [[mieszkać|mieszkał]] [[w]] [[zamek|zamku]].''",
			": (1.2) ''[[zamek|Zamek]] [[w]] [[drzwi]]ach [[zaciąć się|się zaciął]].''"));
	
	private final String RESULT_0_0 = "''[[hrabia|Hrabia]] [[mieszkać|mieszkał]] [[w]] [[zamek|zamku]].''";
	private final String RESULT_0_1 = "''[[zamek|Zamek]] [[w]] [[drzwi]]ach [[zaciąć się|się zaciął]].''";
	
	@Before
	public void setUp() {
		objUnderTest = new ExamplesParser();
	}
	
	@Test
	public void parseTest() throws Exception {
		WikiUnit testUnit = createTestUnit();
		objUnderTest.parse(testUnit, testData);
		
		assertThat(testUnit.getImportance(0, 0).getExamples().get(0).getExample(), is(RESULT_0_0));
		assertThat(testUnit.getImportance(0, 1).getExamples().get(0).getExample(), is(RESULT_0_1));
	}
}
