package pl.semantyk.dao;

import org.junit.Test;
import pl.semantyk.domain.RawWikiUnit;

import java.util.Collection;

public class RawWikiUnitDaoImplTest {

    @Test
    public void testPersistNative() throws Exception {
        RawWikiUnit rawWikiUnit = new RawWikiUnit();

        rawWikiUnit.setText("test body");
        rawWikiUnit.setTitle("test title");

        CrudDao dao = DaoFactory.getDaoFor(RawWikiUnit.class);

        dao.persist(rawWikiUnit);

        Collection<RawWikiUnit> result = dao.findAll();

        for (RawWikiUnit entity: result) {
            System.out.println(entity.toString());
        }

    }
}
