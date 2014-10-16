package pl.semantyk.dao;

import java.util.List;

import pl.semantyk.domain.RelationType;
import pl.semantyk.exceptions.SystemException;

public interface RelationTypeDao {

	RelationType findById(Integer id) throws SystemException;

	List<RelationType> findAll() throws SystemException;

	void persist(RelationType entity) throws SystemException;

	void persistNative(RelationType entity) throws SystemException;

	void persistAllNative(List<RelationType> entity) throws SystemException;

	void peristAll(List<RelationType> entities) throws SystemException;

	void remove(RelationType entity) throws SystemException;

	void removeAll(List<RelationType> entities) throws SystemException;
}
