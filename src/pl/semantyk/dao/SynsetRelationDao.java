package pl.semantyk.dao;

import pl.semantyk.domain.SynsetRelation;
import pl.semantyk.exceptions.SystemException;
import pl.semantyk.parse.entities.SynsetRelationRaw;

import java.util.List;
import java.util.Set;

public interface SynsetRelationDao {

    public SynsetRelation findById(Integer id) throws SystemException;

    public List<SynsetRelation> findAll() throws SystemException;

    void persistNative(SynsetRelationRaw entity) throws SystemException;

    void persistAllNative(Set<SynsetRelationRaw> entities) throws SystemException;

    public void remove(SynsetRelation entity) throws SystemException;

    public void removeAll(List<SynsetRelation> entities) throws SystemException;

}
