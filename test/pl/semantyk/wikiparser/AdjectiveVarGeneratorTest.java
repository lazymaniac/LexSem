package pl.semantyk.wikiparser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pl.semantyk.domain.AdjectiveVar;

public class AdjectiveVarGeneratorTest {

	private AdjectiveVarGenerator objUnderTest;

	private final String goodData = "ładny";

	private final String goodData2 = "brzydki";

	@Before
	public void setUp() {
		objUnderTest = new AdjectiveVarGenerator();
	}

	@Test
	public void genAdjVarTest() throws Exception {
		AdjectiveVar result = objUnderTest.genAdjVar(goodData, goodData, goodData);

		Assert.assertTrue(result.getDegree().get(0).getCasesVar().get(0).getWolacz().equals(goodData));

		System.out.println(result);
	}

	@Test
	public void genAdjVarTest2() throws Exception {
		AdjectiveVar result = objUnderTest.genAdjVar(goodData2, goodData2, goodData2);

		Assert.assertTrue(result.getDegree().get(0).getCasesVar().get(0).getWolacz().equals(goodData2));

		System.out.println(result);
	}
}
