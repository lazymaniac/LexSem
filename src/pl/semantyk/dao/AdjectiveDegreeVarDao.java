package pl.semantyk.dao;


import pl.semantyk.domain.AdjectiveDegreeVar;
import pl.semantyk.exceptions.SystemException;

import java.util.List;

public interface AdjectiveDegreeVarDao {

    public AdjectiveDegreeVar findById(Integer id) throws SystemException;

    public List<AdjectiveDegreeVar> findAll() throws SystemException;

    public void remove(AdjectiveDegreeVar entity) throws SystemException;

    public void removeAll(List<AdjectiveDegreeVar> entities) throws SystemException;

}
