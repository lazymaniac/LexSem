package pl.semantyk.dao;

import pl.semantyk.domain.RawWikiUnit;
import pl.semantyk.exceptions.SystemException;

import java.util.Collection;
import java.util.List;

public interface RawWikiUnitDao {

    public void persist(RawWikiUnit entity);

    void persistNative(RawWikiUnit entity);

    void persistAllNative(Collection<RawWikiUnit> entities);

    public RawWikiUnit findById(Integer id) throws SystemException;

    public List<RawWikiUnit> findAll() throws SystemException;

    public void remove(RawWikiUnit entity) throws SystemException;

    public void removeAll(List<RawWikiUnit> entities) throws SystemException;
}
