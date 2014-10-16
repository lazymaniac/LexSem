package pl.semantyk.dao;

import pl.semantyk.domain.AdverbVar;
import pl.semantyk.exceptions.SystemException;

import java.util.List;

public interface AdverbVarDao {

	public AdverbVar findById(Integer id) throws SystemException;

	public List<AdverbVar> findAll() throws SystemException;

	public void remove(AdverbVar entity) throws SystemException;

	public void removeAll(List<AdverbVar> entities) throws SystemException;

}
