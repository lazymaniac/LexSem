package pl.semantyk.dao;

import pl.semantyk.domain.Importance;
import pl.semantyk.exceptions.SystemException;

import java.util.List;

public interface ImportanceDao {

    public Importance findById(Integer id) throws SystemException;

    public List<Importance> findAll() throws SystemException;

    public void remove(Importance entity) throws SystemException;

    public void removeAll(List<Importance> entities) throws SystemException;

}
