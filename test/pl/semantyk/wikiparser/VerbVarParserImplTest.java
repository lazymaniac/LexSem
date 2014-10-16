package pl.semantyk.wikiparser;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pl.semantyk.domain.VerbVar;
import pl.semantyk.domain.WikiUnit;

public class VerbVarParserImplTest extends AbstractWikiParserTest {

	private VarietyFactory objectUnderTest;

	private List<String> simpleForm;
	private List<String> normalForm;
	private List<String> broken;
	private List<String> empty;

	private final String[] normalFormData = new String[] { "{{odmiana}}",
			": (1) {{odmiana-czasownik-polski", "| dokonany=nie",
			"| koniugacja=IV", "| robić=dołować", "| robię=dołuję",
			"| robi=dołuje", "| robią=dołują", "| robiłem=dołowałem",
			"| robił=dołował", "| robiła=dołowała", "| robili=dołowali",
			"| robiono=dołowano", "| rób=dołuj", "| robiąc=dołując",
			"| robiony=dołowany", "| robieni=dołowani", "| robienie=dołowanie",
			"}}" };

	private final String[] simpleFormData = new String[] { "{{odmiana}}",
			": (1.1) umier|ać, {{KoniugacjaPL|I}}; {{dk}} [[umrzeć]]" };

	private final String[] emptyData = new String[] { "{{odmiana}}" };

	private final String[] brokenData = new String[] { "{{odmiana}}",
			": (1.1-2) {{odmiana-rzeczownik-polski", "|Mianownik lp = pies",
			"|Mianownik lm = psy/{{przest}}, {{gwara}} psi",
			"|Dopełniacz lp = psa", "|Dopełniacz lm = psów",
			"|Celownik lp = psu", "|Celownik lm = psom", "|Biernik lp = psa",
			"|Biernik lm = psy", "|Narzędnik lp = psem",
			"|Narzędnik lm = psami", "|Miejscownik lp = psie",
			"|Miejscownik lm = psach", "|Wołacz lp = psie",
			"|Wołacz lm = psy/{{przest}}, {{gwara}} psi", "}}",
			": (1.3) {{odmiana-rzeczownik-polski", "|Mianownik lp = pies",
			"|Mianownik lm = psy", "|Dopełniacz lp = psa",
			"|Dopełniacz lm = psów", "|Celownik lp = psu",
			"|Celownik lm = psom", "|Biernik lp = psa",
			"|Biernik lm = psy / psów", "|Narzędnik lp = psem",
			"|Narzędnik lm = psami", "|Miejscownik lp = psie",
			"|Miejscownik lm = psach", "|Wołacz lp = psie", "|Wołacz lm = psy",
			"}}" };

	@Before
	public void setUp() {
		objectUnderTest = new VarietyFactory();
		simpleForm = new ArrayList<>(Arrays.asList(simpleFormData));
		normalForm = new ArrayList<>(Arrays.asList(normalFormData));
		broken = new ArrayList<>(Arrays.asList(brokenData));
		empty = new ArrayList<>(Arrays.asList(emptyData));
	}

	@Test
	public void parseSimpleFormTest() {
		WikiUnit testUnit = createTestUnit();
		objectUnderTest.parse(testUnit, simpleForm);

		VerbVar czasOdm = testUnit.getPartsOfSpeech().get(0).getImportances()
				.get(0).getVerbVars().get(0);
		assertTrue(czasOdm.getGerund().equals("umieranie"));
		assertTrue(czasOdm.getAdverbialParticiplePrior().equals("umierawszy"));
		assertTrue(czasOdm.getAdverbialParticipleContemporary().equals(
				"umierając"));
		assertTrue(czasOdm.getImpersonalFormPast().equals("umierano"));
		assertTrue(czasOdm.getInfinitive().equals("umierać"));
		System.out.println(testUnit);
	}

	@Test
	public void parseNormalFormTest() {
		WikiUnit testUnit = createTestUnit();
		objectUnderTest.parse(testUnit, normalForm);

		System.out.println(testUnit);
	}

	@Test
	public void parseBrokenTest() {
		WikiUnit testUnit = createTestUnit();
		objectUnderTest.parse(testUnit, broken);

		assertTrue(testUnit.getPartsOfSpeech().get(0).getImportances().get(0)
				.getVerbVars().isEmpty());
		System.out.println(testUnit);
	}

	@Test
	public void parseEmptyTest() {
		WikiUnit testUnit = createTestUnit();
		objectUnderTest.parse(testUnit, empty);

		assertTrue(testUnit.getPartsOfSpeech().get(0).getImportances().get(0)
				.getVerbVars().isEmpty());
		System.out.println(testUnit);
	}
}
