package pl.semantyk.dao;

import pl.semantyk.domain.Synonym;
import pl.semantyk.exceptions.SystemException;

import java.util.List;

public interface SynonimDao {

    public Synonym findById(Integer id) throws SystemException;

    public List<Synonym> findAll() throws SystemException;

    public void remove(Synonym entity) throws SystemException;

    public void removeAll(List<Synonym> entities) throws SystemException;

}
