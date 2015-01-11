package pl.semantyk.dao;

/**
 * Skeleton for all Dao classes.
 */
public abstract class AbstractDao<T> {

    protected final Class<T> clazz;

    /**
     * Constructor initializes connection using ConnectionProvider.
     */
    public AbstractDao(Class<T> clazz) {
        this.clazz = clazz;
    }

}
