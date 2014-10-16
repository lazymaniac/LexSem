package pl.semantyk.dao;

import pl.semantyk.domain.AdjectiveVar;
import pl.semantyk.exceptions.SystemException;

import java.util.List;

public interface AdjectiveVarDao {

    public AdjectiveVar findById(Integer id) throws SystemException;

    public List<AdjectiveVar> findAll() throws SystemException;

    public void remove(AdjectiveVar entity) throws SystemException;

    public void removeAll(List<AdjectiveVar> entities) throws SystemException;

}
