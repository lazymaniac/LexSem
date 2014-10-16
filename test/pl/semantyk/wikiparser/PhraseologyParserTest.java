package pl.semantyk.wikiparser;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pl.semantyk.domain.WikiUnit;

public class PhraseologyParserTest extends AbstractWikiParserTest {

	private PhraseologyParser objUnderTest;
	
	private List<String> multilineData = new ArrayList<>(Arrays.asList(
			"{{frazeologia}}",
			": [[zima za pasem]] • [[zima zaskoczyła drogowców]] • [[zima w mieście]]"
			));
	
	private List<String> onelineData = new ArrayList<>(Arrays.asList(
			"{{frazeologia}} [[zima za pasem]] • [[zima zaskoczyła drogowców]] • [[zima w mieście]]"
			));
	
	private final String RESULT1 = "zima za pasem";
	private final String RESULT2 = "zima zaskoczyła drogowców";
	private final String RESULT3 = "zima w mieście";
	
	@Before
	public void setUp() {
		objUnderTest = new PhraseologyParser();
	}
	
	@Test
	public void parseMultilineTest() throws Exception {
		WikiUnit testUnit = createTestUnit();
		objUnderTest.parse(testUnit, multilineData);
		
		assertThat(testUnit.getImportance(0, 0).getPhraseology().get(0).getPhraseology(), is(RESULT1));
		assertThat(testUnit.getImportance(0, 0).getPhraseology().get(1).getPhraseology(), is(RESULT2));
		assertThat(testUnit.getImportance(0, 0).getPhraseology().get(2).getPhraseology(), is(RESULT3));
	}
	
	@Test
	public void parseOnelineTest() throws Exception {
		WikiUnit testUnit = createTestUnit();
		objUnderTest.parse(testUnit, onelineData);
		
		assertThat(testUnit.getImportance(0, 0).getPhraseology().get(0).getPhraseology(), is(RESULT1));
		assertThat(testUnit.getImportance(0, 0).getPhraseology().get(1).getPhraseology(), is(RESULT2));
		assertThat(testUnit.getImportance(0, 0).getPhraseology().get(2).getPhraseology(), is(RESULT3));
	}
}
