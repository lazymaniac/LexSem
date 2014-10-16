package pl.semantyk.dao;

import pl.semantyk.domain.LexicalRel;
import pl.semantyk.exceptions.SystemException;
import pl.semantyk.parse.entities.LexicalRelRaw;

import java.util.List;
import java.util.Set;

public interface LexicalRelDao {

    public LexicalRel findById(Integer id) throws SystemException;

    public List<LexicalRel> findAll() throws SystemException;

    void persistNative(LexicalRelRaw entity) throws SystemException;

    void persistAllNative(Set<LexicalRelRaw> entities) throws SystemException;

    public void remove(LexicalRel entity) throws SystemException;

    public void removeAll(List<LexicalRel> entities) throws SystemException;

}
