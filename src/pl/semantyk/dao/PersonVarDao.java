package pl.semantyk.dao;


import pl.semantyk.domain.PersonVar;
import pl.semantyk.exceptions.SystemException;

import java.util.List;

public interface PersonVarDao {

    public PersonVar findById(Integer id) throws SystemException;

    public List<PersonVar> findAll() throws SystemException;

    public void remove(PersonVar entity) throws SystemException;

    public void removeAll(List<PersonVar> entities) throws SystemException;
}
