package pl.semantyk.dao;

import pl.semantyk.domain.WnUnit;
import pl.semantyk.exceptions.SystemException;

import java.util.List;
import java.util.Set;

public interface WnUnitDao {

    WnUnit findById(Integer id) throws SystemException;

    List<WnUnit> findAll() throws SystemException;

    void persist(WnUnit entity) throws SystemException;

    void persistNative(WnUnit entity) throws SystemException;

    void persisttAllNative(Set<WnUnit> entity) throws SystemException;

    void peristAll(List<WnUnit> entities) throws SystemException;

    void mergeJednWnWithJednWiki() throws SystemException;

    void remove(WnUnit entity) throws SystemException;

    void removeAll(List<WnUnit> entities) throws SystemException;

    void removeAll() throws SystemException;
}
