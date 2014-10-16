package pl.semantyk.dao;


import pl.semantyk.domain.NumeralVar;
import pl.semantyk.exceptions.SystemException;

import java.util.List;

public interface NumeralVarDao {

    public NumeralVar findById(Integer id) throws SystemException;

    public List<NumeralVar> findAll() throws SystemException;

    public void remove(NumeralVar entity) throws SystemException;

    public void removeAll(List<NumeralVar> entities) throws SystemException;

}
