package pl.semantyk.dao;

import pl.semantyk.domain.CasesVar;
import pl.semantyk.exceptions.SystemException;

import java.util.List;

public interface CasesVarDao {

    public CasesVar findById(Integer id) throws SystemException;

    public List<CasesVar> findAll() throws SystemException;

    public void remove(CasesVar entity) throws SystemException;

    public void removeAll(List<CasesVar> entities) throws SystemException;

}
