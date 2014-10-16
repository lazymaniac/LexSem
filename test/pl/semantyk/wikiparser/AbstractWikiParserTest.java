package pl.semantyk.wikiparser;

import pl.semantyk.domain.PartOfSpeech;
import pl.semantyk.domain.WikiUnit;
import pl.semantyk.domain.Importance;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AbstractWikiParserTest {


    protected WikiUnit createTestUnit() {
        Random random = new Random(System.currentTimeMillis());
        WikiUnit temp = new WikiUnit();
        temp.setId(random.nextInt());
        temp.setName("nazwa");
        temp.setTopic("temat");
        List<PartOfSpeech> czescMowies = createCzesciMowy(temp);
        temp.setPartsOfSpeech(czescMowies);
        return temp;
    }

    private List<PartOfSpeech> createCzesciMowy(WikiUnit jw) {
        List<PartOfSpeech> result = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            PartOfSpeech cm1 = new PartOfSpeech();
            cm1.setId(i);
            cm1.setPartOfSpeech("czasownik");
            cm1.setWikiUnit(jw);
            cm1.setImportances(createZnaczenie(i));
            result.add(cm1);
        }

        return result;
    }

    private List<Importance> createZnaczenie(int i) {
        List<Importance> result = new ArrayList<>();

        for (int j = 0; j < 3; j++) {
            Importance znaczenie = new Importance();
            znaczenie.setId(i);
            result.add(znaczenie);
        }

        return result;
    }
}
