package pl.semantyk.dao;

import org.junit.Test;
import pl.semantyk.domain.RelationType;
import pl.semantyk.exceptions.SystemException;

public class TypRelacjiDaoImplTest {

    @Test
    public void peristNativeTest() throws SystemException {
        RelationType entity = new RelationType();

        entity.setId(1);
        entity.setPosstr("posstr");
        entity.setDisplay("wyswietl");
        entity.setType("typ");
        entity.setAbbreviation("skrot");
        entity.setAutoOdwracanie(true);
        entity.setName("nazwa");
        entity.setDescription("opis");
        entity.setParent(2);

        CrudDao dao = DaoFactory.getDaoFor(RelationType.class);
        RelationType toRemove = (RelationType) dao.findById(1);
        if (toRemove != null)
            dao.remove(toRemove.getId());
        dao.persist(entity);
    }
}
