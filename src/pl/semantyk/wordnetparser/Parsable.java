/**
 *
 */
package pl.semantyk.wordnetparser;

/**
 * Interface should be implemented by every parsing class.
 *
 * @author Sebastian Fabisz
 */
public interface Parsable {
    /**
     * Method starts parsing document.
     */
    void parseDocument();

    /**
     * This method set a path to input database file.
     *
     * @param inputFile absolute path to database file.
     */
    void setInputFile(String inputFile);
}
