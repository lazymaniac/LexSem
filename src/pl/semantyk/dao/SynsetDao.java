package pl.semantyk.dao;

import pl.semantyk.domain.Synset;
import pl.semantyk.exceptions.SystemException;
import pl.semantyk.parse.entities.SynsetRaw;

import java.util.List;
import java.util.Set;

public interface SynsetDao {

    public Synset findById(Integer id) throws SystemException;

    public List<Synset> findAll() throws SystemException;

    void persistNative(SynsetRaw entity) throws SystemException;

    void persistAllNative(Set<SynsetRaw> entities) throws SystemException;

    public void remove(Synset entity) throws SystemException;

    public void removeAll(List<Synset> entities) throws SystemException;

}
