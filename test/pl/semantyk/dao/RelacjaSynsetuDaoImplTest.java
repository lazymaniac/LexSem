package pl.semantyk.dao;

import org.junit.Test;
import pl.semantyk.parse.entities.SynsetRelationRaw;

public class RelacjaSynsetuDaoImplTest {

    @Test
    public void testPersistNative() throws Exception {
        SynsetRelationRaw relacjaSynsetu = new SynsetRelationRaw();
        relacjaSynsetu.setChecked(true);
        relacjaSynsetu.setParent(1);
        relacjaSynsetu.setChild(2);
        relacjaSynsetu.setRelation(1);
        
    }
}
