package pl.semantyk.dao;

import java.util.Set;

import pl.semantyk.domain.WnUnitSynsetRel;
import pl.semantyk.exceptions.SystemException;

public interface WnUnitSynsetRelDao {

	void persistNative(WnUnitSynsetRel entity) throws SystemException;

	void perisistAllNative(Set<WnUnitSynsetRel> entity) throws SystemException;

}
