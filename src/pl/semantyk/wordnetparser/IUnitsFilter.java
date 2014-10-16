package pl.semantyk.wordnetparser;

import pl.semantyk.exceptions.SystemException;

/**
 * This interface should by implemented by every class
 * that is filtering any type of units and removes them.
 *
 * @author Sebastian Fabisz
 */
public interface IUnitsFilter {

    /**
     * Start removing units.
     */
    void clearUnits() throws SystemException;
}
