package pl.semantyk.dao;

import pl.semantyk.domain.Collocation;
import pl.semantyk.exceptions.SystemException;

import java.util.List;

public interface CollocationDao {

    public Collocation findById(Integer id) throws SystemException;

    public List<Collocation> findAll() throws SystemException;

    public void remove(Collocation entity) throws SystemException;

    public void removeAll(List<Collocation> entities) throws SystemException;

}
