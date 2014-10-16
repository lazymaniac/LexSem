package pl.semantyk.dao;

import org.junit.Test;
import pl.semantyk.domain.RawWikiUnit;

import java.util.Collection;

import static pl.semantyk.utils.CommonUtils.print;

public class RawWikiUnitDaoImplTest {

    @Test
    public void testPersistNative() throws Exception {
        RawWikiUnit rawWikiUnit = new RawWikiUnit();

        rawWikiUnit.setText("test body");
        rawWikiUnit.setTitle("test title");

        RawWikiUnitDao dao = new RawWikiUnitDaoImpl();

        dao.persistNative(rawWikiUnit);

        Collection<RawWikiUnit> result = dao.findAll();

        for (RawWikiUnit entity: result) {
            print(entity.toString());
        }

    }
}
