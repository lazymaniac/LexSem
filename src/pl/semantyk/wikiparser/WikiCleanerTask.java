/**
 *
 */
package pl.semantyk.wikiparser;

import pl.semantyk.domain.RawWikiUnit;

import java.util.LinkedHashSet;
import java.util.concurrent.RecursiveTask;

/**
 * @author sebastian
 */
public class WikiCleanerTask extends RecursiveTask<LinkedHashSet<RawWikiUnit>> {

    private static final long serialVersionUID = 9019220311991871481L;

    /**
     * Domyślny konstruktor.
     */
    public WikiCleanerTask() {

    }

    @Override
    protected final LinkedHashSet<RawWikiUnit> compute() {

        return null;
    }

}
