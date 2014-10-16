package pl.semantyk.dao;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import static pl.semantyk.database.EntityManagerProvider.getEm;

/**
 * Klasa abstrakcyjna. Inicjalizuje EntityManager oraz zawiera metody do obsługi tranzakcji.
 */
public abstract class AbstractDao {

	private static final Logger LOG = Logger.getLogger(AbstractDao.class);
    protected EntityManager em;

    /**
     * Konstruktor pobiera referencje do EntityManager z klasy EntityManagerProvider.
     */
    public AbstractDao() {
        em = getEm();
    }

    /**
     * Otwiera tranzakcje.
     */
    protected void openTransaction() {
        em.getTransaction().begin();
        LOG.debug("Transaction open.");
    }

    /**
     * Zamyka tranzakcje.
     */
    protected void closeTransaction() {
        em.getTransaction().commit();
        LOG.debug("Transaction closed.");
    }

    public abstract void closeEm();
    
}
