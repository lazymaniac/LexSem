package pl.semantyk.databaseutils;

import pl.semantyk.domain.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Generates Id for provided class.
 * @author Sebastian Fabisz
 */
public class IdGenerator {

    private static Map<Class, Integer> counters = new HashMap<>();

    static {
        counters.put(AdjectiveDegreeVar.class, 1);
        counters.put(AdjectiveVar.class, 1);
        counters.put(AdverbVar.class, 1);
        counters.put(Antonym.class, 1);
        counters.put(CasesVar.class, 1);
        counters.put(Cognate.class, 1);
        counters.put(Collocation.class, 1);
        counters.put(Example.class, 1);
        counters.put(Importance.class, 1);
        counters.put(LexicalRel.class, 1);
        counters.put(NounVar.class, 1);
        counters.put(NumeralVar.class, 1);
        counters.put(Pair.class, 1);
        counters.put(PartOfSpeech.class, 1);
        counters.put(PersonVar.class, 1);
        counters.put(Phraseology.class, 1);
        counters.put(PronounVar.class, 1);
        counters.put(RawWikiUnit.class, 1);
        counters.put(RelationType.class, 1);
        counters.put(Synonym.class, 1);
        counters.put(Synset.class, 1);
        counters.put(SynsetRelation.class, 1);
        counters.put(VerbVar.class, 1);
        counters.put(WikiUnit.class, 1);
        counters.put(WnUnit.class, 1);
        counters.put(WnUnitSynsetRel.class, 1);
    }

    public static Integer getId(Class clazz) {
        Integer id = counters.get(clazz);
        counters.put(clazz, id + 1);
        return id;
    }

}
