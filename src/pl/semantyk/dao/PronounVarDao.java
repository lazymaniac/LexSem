package pl.semantyk.dao;

import pl.semantyk.domain.PronounVar;
import pl.semantyk.exceptions.SystemException;

import java.util.List;

public interface PronounVarDao {

    public PronounVar findById(Integer id) throws SystemException;

    public List<PronounVar> findAll() throws SystemException;

    public void remove(PronounVar entity) throws SystemException;

    public void removeAll(List<PronounVar> entities) throws SystemException;

}
