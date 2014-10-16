package pl.semantyk.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NumerationTest {

    @Test
    public void numerationTest() {
        String example[] = {
                ": (1.1) test",
                ": (1.2-3) test",
                ": (1.2,2-4) test",
                ": (1.1,2-3) test",
                ": (11.234,223-33) test"
        };

        for (String line: example) {
            String numeracja;

            if (line.startsWith(":")) {
                // WYTNIJ NUMERACJĘ.
                int beg = line.indexOf("(") + 1;
                int end = line.indexOf(")");

                numeracja = line.substring(beg, end);

                // PODZIEL NUMERACJE NA LICZBY I ZNAKI PUNKCYJNE.
                String chars[] = numeracja.split("");

                List<String> splited = new ArrayList<>();

                String number = "";

                for (String letter: chars) {
                    if (letter.matches("[0-9]+")) {
                        number += letter;
                    }

                    if (letter.matches("[\\.,-]+")) {
                        splited.add(number);
                        splited.add(letter);
                        number = "";
                    }

                }

                splited.add(number);


                // UTÓRZ OBIEKTY NUMERACJI.
                for (String aSplited : splited) {
                    if (aSplited.equals(".")) {

                    }
                }
            }
        }
    }
}
