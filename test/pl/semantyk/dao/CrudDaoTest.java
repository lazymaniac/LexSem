package pl.semantyk.dao;

import org.junit.Test;
import pl.semantyk.domain.Synset;

import static org.junit.Assert.*;

public class CrudDaoTest {

    @Test
    public void testFindById() throws Exception {
        CrudDao synsetDao = DaoFactory.getDaoFor(Synset.class);
        Synset result = (Synset) synsetDao.findById(1);
        System.out.println(result);
    }

    @Test
    public void testFindAll() throws Exception {

    }

    @Test
    public void testRemove() throws Exception {

    }

    @Test
    public void testRemoveAll() throws Exception {

    }

    @Test
    public void testPersist() throws Exception {
        CrudDao synsetDao = DaoFactory.getDaoFor(Synset.class);
        Synset synset = new Synset();
        synset.setId(1);
        synset.setIsAbstract(false);
        synset.setSplited(1);

        synsetDao.persist(synset);

    }

    @Test
    public void testPersistAll() throws Exception {

    }

    @Test
    public void testPersistAll1() throws Exception {

    }
}