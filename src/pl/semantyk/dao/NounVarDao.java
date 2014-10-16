package pl.semantyk.dao;

import pl.semantyk.domain.NounVar;
import pl.semantyk.exceptions.SystemException;

import java.util.List;

public interface NounVarDao {

    public NounVar findById(Integer id) throws SystemException;

    public List<NounVar> findAll() throws SystemException;

    public void remove(NounVar entity) throws SystemException;

    public void removeAll(List<NounVar> entities) throws SystemException;

}
