package pl.semantyk.dao;

import org.junit.Assert;
import org.junit.Test;
import pl.semantyk.domain.Synonym;
import pl.semantyk.domain.Synset;

import static pl.semantyk.dao.IdGenerator.*;

public class IdGeneratorTest {

    @Test
    public void testGetId() throws Exception {
        Assert.assertEquals(getId(Synset.class), new Integer(1));
        Assert.assertEquals(getId(Synset.class), new Integer(2));
        Assert.assertEquals(getId(Synset.class), new Integer(3));
        Assert.assertEquals(getId(Synonym.class), new Integer(1));
    }
}