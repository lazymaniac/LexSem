package pl.semantyk.dao;

/**
 * Returns Dao class for specified entity.
 * @author Sebastian Fabisz
 */
public class DaoFactory {

    public static CrudDao getDaoFor(Class clazz) {
        return new CrudDao(clazz);
    }

}
