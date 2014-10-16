package pl.semantyk.test;

import org.junit.Test;

import pl.semantyk.utils.CommonUtils;
import pl.semantyk.utils.StopWatch;
import pl.semantyk.utils.WNMap;

/**
 * User: sebastian Date: 06.10.13 Time: 09:28
 */
public class WNMapTest {

	@Test
	public void performanceTest() {
		WNMap<Integer, Integer> test = new WNMap<>();

		final int limit = 30000;

		StopWatch putTest = new StopWatch(this.getClass(), "Put test " + limit
				+ " int");
		putTest.start();
		for (int i = 1; i < limit + 1; i++) {
			test.put(i, i);
		}
		putTest.stop();

		CommonUtils.print("isEmpty: " + test.isEmpty() + "\n");
		CommonUtils.print("size: " + test.size() + "\n");

		StopWatch containsKeyTest = new StopWatch(this.getClass(),
				"Contains test: " + limit + " keys");
		containsKeyTest.start();
		for (int i = 1; i < limit + 1; i++) {
			test.containsKey(i);
		}
		containsKeyTest.stop();

		StopWatch containsValuesTest = new StopWatch(this.getClass(),
				"Contains test: " + limit + " values");
		containsValuesTest.start();
		for (int i = 1; i < limit + 1; i++) {
			test.containsValue(i);
		}
		containsValuesTest.stop();

		StopWatch getLastTest = new StopWatch(this.getClass(),
				"Get last key and value");
		getLastTest.start();
		CommonUtils.print("Get ostatni value key: " + test.getVal(limit));
		CommonUtils.print("Get ostatni key value: " + test.getKey(limit));
		getLastTest.stop();

		StopWatch getValues = new StopWatch(this.getClass(), "Get test "
				+ limit + " values");
		getValues.start();
		for (int i = 1; i < limit + 1; i++) {
			test.getVal(i);
		}
		getValues.stop();

		StopWatch getKeys = new StopWatch(this.getClass(), "Get test " + limit
				+ " keys");
		getKeys.start();
		for (int i = 1; i < limit + 1; i++) {
			test.getKey(i);
		}
		getKeys.stop();

		StopWatch callTest = new StopWatch(this.getClass(), "remove test "
				+ limit + " calls");
		callTest.start();
		for (int i = 1; i < limit + 1; i++) {
			test.removeVal(i);
		}
		callTest.stop();
	}

}
