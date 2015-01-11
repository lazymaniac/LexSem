package pl.semantyk.utils;

import pl.semantyk.enums.TimeUnit;

import java.util.Date;

import org.apache.log4j.Logger;

public class StopWatch {

	private static final Logger LOG = Logger.getLogger(StopWatch.class);
	
    /**
     * Start time.
     */
    private long start = -1L;

    /**
     * End time.
     */
    private long stop = -1L;

    /**
     * Process name.
     */
    private String processName = "default";

    /**
     * Time unit. Default milliseconds.
     */
    private TimeUnit timeUnit = TimeUnit.MILISECOND;

    /**
     * Caller class.
     */
    private Class<?> clazz;

    public StopWatch() {
    }

    public StopWatch(Class<?> clazz, String processName) {
        this.setClazz(clazz);
        this.processName = processName;
    }

    public StopWatch(Class<?> clazz, String processName, TimeUnit timeUnit) {
        this.setClazz(clazz);
        this.processName = processName;
        this.timeUnit = timeUnit;
    }

    public void start() {
        LOG.info(processName + " STARTED AT: " + new Date() + ".\n");
        this.start = System.currentTimeMillis();
    }

    public void stop() throws IllegalStateException {
        this.stop = System.currentTimeMillis();
        long result = stop - start;

        if (start == -1L) {
            throw new IllegalStateException("Run start() first.");
        }

        if (timeUnit.equals(TimeUnit.SECOND))
            result /= 1000;
        if (timeUnit.equals(TimeUnit.MINUTE))
            result /= 1000 * 60;

        LOG.info(processName + " FINISHED AT: " + new Date() + ", TOOK: " + result + " " + timeUnit.getValue() + ".\n");

        start = -1L;
        stop = -1L;
    }

    public long getDiffMs() {
        return System.currentTimeMillis() - start;
    }

    public double getDiffSec() {
        return (System.currentTimeMillis() - start) / 1000;
    }

    public void printDiffMs() {
        LOG.info(processName + " took: " + (System.currentTimeMillis() - start) + " ms.");
    }

    public void printDiffSec() {
        LOG.info(processName + " took: " + (System.currentTimeMillis() - start) / 1000 + " sec.");
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
}
