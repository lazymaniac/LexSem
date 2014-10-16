package pl.semantyk.wordnetparser;

import java.util.ArrayList;

/**
 * This interface should be implemented by every class that is
 * saving output files.
 *
 * @author Sebastian Fabisz
 */
public interface ISegmentable {
    /**
     * Starts saving to separate files different types of
     * objects.
     */
    void printToSeparateFiles();

    /**
     * Set a paths to output files.
     *
     * @param outputs collection of output files paths.
     */
    void setOutputFiles(ArrayList<String> outputs);
}
