package pl.semantyk.wikiparser;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pl.semantyk.domain.NounVar;
import pl.semantyk.domain.WikiUnit;

public class NounVarParserTest extends AbstractWikiParserTest {

	private NounVarParserImpl<NounVar> objUnderTest;
	
	private List<String> longDeclination = new ArrayList<String>(Arrays.asList(
			"{{odmiana}}",
			": (1.1) {{odmiana-rzeczownik-polski",
			"|Mianownik lp = zamek",
			"|Dopełniacz lp = zamku",
			"|Celownik lp = zamkowi",
			"|Biernik lp = zamek",
			"|Narzędnik lp = zamkiem",
			"|Miejscownik lp = zamku",
			"|Wołacz lp = zamku",
			"|Mianownik lm = zamki",
			"|Dopełniacz lm = zamków",
			"|Celownik lm = zamkom",
			"|Biernik lm = zamki",
			"|Narzędnik lm = zamkami",
			"|Miejscownik lm = zamkach",
			"|Wołacz lm = zamki",
			"}}"
		));
	
	@Before
	public void setUp() {
		objUnderTest = new NounVarParserImpl<>();
	}
	
	@Test
	public void parseSimpleTest() throws Exception {
		WikiUnit testUnit = createTestUnit();
		NounVar nounVar = objUnderTest.parse(testUnit, longDeclination);
		
		assertThat(nounVar.getCasesVar().get(0).getMianownik(), is("zamek"));
		assertThat(nounVar.getCasesVar().get(0).getDopelniacz(), is("zamku"));
		assertThat(nounVar.getCasesVar().get(0).getCelownik(), is("zamkowi"));
		assertThat(nounVar.getCasesVar().get(0).getBiernik(), is("zamek"));
		assertThat(nounVar.getCasesVar().get(0).getNarzednik(), is("zamkiem"));
		assertThat(nounVar.getCasesVar().get(0).getMiejscownik(), is("zamku"));
		assertThat(nounVar.getCasesVar().get(0).getWolacz(), is("zamku"));
		
		assertThat(nounVar.getCasesVar().get(1).getMianownik(), is("zamki"));
		assertThat(nounVar.getCasesVar().get(1).getDopelniacz(), is("zamków"));
		assertThat(nounVar.getCasesVar().get(1).getCelownik(), is("zamkom"));
		assertThat(nounVar.getCasesVar().get(1).getBiernik(), is("zamki"));
		assertThat(nounVar.getCasesVar().get(1).getNarzednik(), is("zamkami"));
		assertThat(nounVar.getCasesVar().get(1).getMiejscownik(), is("zamkach"));
		assertThat(nounVar.getCasesVar().get(1).getWolacz(), is("zamki"));
	}
}
