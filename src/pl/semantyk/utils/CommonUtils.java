package pl.semantyk.utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Klasa pomocnicza. Zawiera metody ogólnego użytku.
 */
public final class CommonUtils {

	private static PrintWriter output;
	private static final String LOG_FILE = "log.txt";

	static {
		try {
			output = new PrintWriter(LOG_FILE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private CommonUtils() {
	}

	/**
	 * Przeciążona metoda z klasy <code>System.out</code>.
	 * 
	 * @param message
	 *            treść komunikatu wysłanego na konsole.
	 */
	public static void print(final String message) {
		System.out.println(message);
		printToFile(message);
	}

	public static void printf(final String message, Object... args) {
		System.out.printf(message, args);
	}

	public static void printToFile(final String message) {
		output.println(message);
	}

	public static void closeOutput() {
		output.close();
	}

	/**
	 * Przypisuje zmiennej timeStart aktualny czas systemowy w milisekundach.
	 */
	@Deprecated
	public static void performanceTestStart() {
		timeStart = System.currentTimeMillis();
	}

	/**
	 * Wyświetla czas jaki upłynął od rozpoczęcia pomiaru czasu. Wymaga
	 * wcześniejszego uruchomienia funkcji <code>performanceTestStart()</code>
	 */
	@Deprecated
	public static void performanceTestStop() {
		if (timeStart == -1) {
			print("Uruchom najpierw funkcję : performanceTestStart()");
			return;
		}

		timeEnd = System.currentTimeMillis();

		final double second = 1000.0;
		print("Time : " + (timeEnd - timeStart) / second + "s\n");
		timeEnd = -1;
		timeStart = -1;
	}

	/**
	 * Generuje tablice kluczy do WNMap.
	 * 
	 * @param size
	 *            rozmiar generowanej tablicy.
	 * @return wypełniona tablica.
	 */
	public static ArrayList<Integer> generateKeys(final int size) {
		ArrayList<Integer> list = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			list.add(i);
		}

		return list;
	}

	/**
	 * Zmienna wykorzystywana przez <code>performanceTestStart()</code>
	 * Przechowuje czas początku testu.
	 */
	private static long timeStart = 0;

	/**
	 * Zmienna wykorzystywana przez <code>performanceTestStop()</code>
	 * Przechowuje czas końca testu.
	 */
	private static long timeEnd = 0;
}
