package pl.semantyk.database;

import pl.semantyk.dao.WnUnitDao;
import pl.semantyk.dao.WnUnitDaoImpl;
import pl.semantyk.exceptions.SystemException;
import pl.semantyk.utils.StopWatch;

import org.apache.log4j.Logger;

public class DatabaseMerger {
	
	private static final Logger LOG = Logger.getLogger(DatabaseMerger.class);

	private WnUnitDao wnUnitDao = new WnUnitDaoImpl();
	
    public DatabaseMerger() {
    }
    
    public boolean merge() {
    	StopWatch watch = new StopWatch(WnUnitDao.class,
				"merging WordNet units with Wiki units...");
		watch.start();
		try {
			wnUnitDao.mergeJednWnWithJednWiki();
		} catch (SystemException e) {
			LOG.debug(e);
			watch.stop();
			return false;
		}
		watch.stop();
    	
    	return true;
    }
}
