package pl.semantyk.wikiparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Test;

import pl.semantyk.domain.WikiUnit;

public class CollocationParserTest extends AbstractWikiParserTest {

	private CollocationsParser objUnderTest;
	
	private List<String> testData = new ArrayList<>(Arrays.asList("{{kolokacje}}", 
			": (1.1) czytać [[książka|książkę]] • czytać [[gazeta|gazetę]] • czytać [[wiersz]] • czytać [[na głos]]",
			": (1.2) czytać [[mapa|mapę]] • czytać [[nuta|nuty]]"));
	
	private final String RESULT_0_0_0 = "czytać [[książka|książkę]]";
	
	private final String RESULT_0_0_1 = "czytać [[gazeta|gazetę]]";
	
	@Before
	public void setUp() {
		objUnderTest = new CollocationsParser();
	}
	
	@Test
	public void perseTest() throws Exception {
		WikiUnit testUnit = createTestUnit();
		objUnderTest.parse(testUnit, testData);
		
		System.out.println(testUnit);
		assertThat(testUnit.getImportance(0, 0).getCollocations().get(0).getCollocation(), is(RESULT_0_0_0));
		assertThat(testUnit.getImportance(0, 0).getCollocations().get(1).getCollocation(), is(RESULT_0_0_1));
	}
	
}
