package pl.semantyk.dao;

import pl.semantyk.domain.Example;
import pl.semantyk.exceptions.SystemException;

import java.util.List;

public interface ExampleDao {

    public Example findById(Integer id) throws SystemException;

    public List<Example> findAll() throws SystemException;

    public void remove(Example entity) throws SystemException;

    public void removeAll(List<Example> entities) throws SystemException;

}
