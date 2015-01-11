package pl.semantyk.database;

import pl.semantyk.dao.DatabaseMergerDao;
import pl.semantyk.exceptions.SystemException;
import pl.semantyk.utils.StopWatch;

import org.apache.log4j.Logger;

public class DatabaseMerger {
	
	private static final Logger LOG = Logger.getLogger(DatabaseMerger.class);

	private DatabaseMergerDao merger = new DatabaseMergerDao();
	
    public DatabaseMerger() {
    }
    
    public boolean merge() {
    	StopWatch watch = new StopWatch(DatabaseMergerDao.class,
				"merging WordNet units with Wiki units...");
		watch.start();
		try {
			merger.mergeWnWithWikt();
		} catch (SystemException e) {
			LOG.debug(e);
			watch.stop();
			return false;
		}
		watch.stop();
    	
    	return true;
    }
}
