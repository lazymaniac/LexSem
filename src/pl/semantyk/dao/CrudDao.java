package pl.semantyk.dao;

import pl.semantyk.database.ConnectionProvider;
import pl.semantyk.exceptions.SystemException;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static pl.semantyk.databaseutils.QueryGenerator.*;
import static pl.semantyk.databaseutils.ResultMapper.getResult;
import static pl.semantyk.databaseutils.ResultMapper.getResultList;
import static pl.semantyk.databaseutils.StatementParameterSetter.setAllParameters;
import static pl.semantyk.databaseutils.StatementParameterSetter.setId;
import static pl.semantyk.exceptions.CommonCode.ILLEGAL_ACCESS_EXCEPTION;
import static pl.semantyk.exceptions.CommonCode.INSTANTIATION_EXCEPTION;
import static pl.semantyk.exceptions.DataAccessCode.ILLEGAL_ARGUMENT;
import static pl.semantyk.exceptions.DataAccessCode.SQL_EXCEPTION;

/**
 * Simple implementation of AbstractDao. Provides CRUD operations for specified Entity class.
 *
 * @author Sebastian Fabisz
 */
public class CrudDao<T> extends AbstractDao {
    /**
     * Constructor initializes connection using ConnectionProvider.
     *
     * @param clazz Type of entity.
     */
    public CrudDao(Class clazz) {
        super(clazz);
    }

    public T findById(final Integer id) throws SystemException {
        T result;
        Connection connection = ConnectionProvider.getConnection();
        PreparedStatement statement = null;

        try {
            String sqlQuery = getFindByIdQueryForClass(clazz);
            statement = connection.prepareStatement(sqlQuery);
            setId(statement, id);

            ResultSet resultSet = statement.executeQuery();
            result = (T) getResult(clazz, resultSet);
        } catch (IllegalArgumentException | SQLException ex) {
            throw new SystemException("Illegal argument.", ex, ILLEGAL_ARGUMENT).set("id:", id);
        } catch (IllegalAccessException e) {
            throw new SystemException("Reflections - access exception", e, ILLEGAL_ACCESS_EXCEPTION);
        } catch (InstantiationException e) {
            throw new SystemException("Reflections - Instantiation exception", e, INSTANTIATION_EXCEPTION);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch(SQLException ex) {
                throw new SystemException("Problem during close.", ex, SQL_EXCEPTION);
            }
        }

        return result;
    }

    public List<T> findAll() throws SystemException {
        List<T> result;
        Connection connection = ConnectionProvider.getConnection();
        PreparedStatement statement = null;

        try {
            String query = getFindAllQueryForClass(clazz);
            statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            result = (List<T>) getResultList(clazz, resultSet);
        } catch (SQLException e) {
            throw new SystemException("Sql Exception", e, SQL_EXCEPTION);
        } catch (IllegalAccessException e) {
            throw new SystemException("Reflections - access exception", e, ILLEGAL_ACCESS_EXCEPTION);
        } catch (InstantiationException e) {
            throw new SystemException("Reflections - Instantiation exception", e, INSTANTIATION_EXCEPTION);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch(SQLException ex) {
                throw new SystemException("Problem during close.", ex, SQL_EXCEPTION);
            }
        }

        return result;
    }

    public void remove(Integer id) throws SystemException {
        Connection connection = ConnectionProvider.getConnection();
        PreparedStatement statement = null;

        try {
            String sqlQuery = getDeleteQueryForClass(clazz);
            statement = connection.prepareStatement(sqlQuery);
            setId(statement, id);
            statement.executeQuery();

        } catch (IllegalArgumentException | SQLException ex) {
            throw new SystemException("Illegal argument.", ex, ILLEGAL_ARGUMENT).set("id:", id);
        } catch (IllegalAccessException e) {
            throw new SystemException("Reflections - access exception", e, ILLEGAL_ACCESS_EXCEPTION);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch(SQLException ex) {
                throw new SystemException("Problem during close.", ex, SQL_EXCEPTION);
            }
        }
    }

    public void removeAll(List<Integer> ids) throws SystemException {
        for (Integer id: ids) {
            remove(id);
        }
    }

    public void persist(T entity) throws SystemException {
        Connection connection = ConnectionProvider.getConnection();
        PreparedStatement statement = null;

        try {
            String sqlQuery = getInsertQueryForClass(clazz);
            //System.out.println(sqlQuery);
            statement = connection.prepareStatement(sqlQuery);
            setAllParameters(statement, clazz, entity);

            statement.execute();
        } catch (SQLException ex) {
            throw new SystemException("SQL Exception", ex, SQL_EXCEPTION);
        } catch (IllegalAccessException e) {
            throw new SystemException("Illegal Access Exception", e, ILLEGAL_ACCESS_EXCEPTION);
        } catch (NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch(SQLException ex) {
                throw new SystemException("Problem during close.", ex, SQL_EXCEPTION);
            }
        }
    }

    public void persistAll(Collection<T> entities) throws SystemException {
        Connection connection = ConnectionProvider.getConnection();
        PreparedStatement statement = null;

        try {
            String sqlQuery = getInsertQueryForClass(clazz);
            statement = connection.prepareStatement(sqlQuery);
            for (T entity: entities) {
                setAllParameters(statement, clazz, entity);
                statement.execute();
            }
        } catch (SQLException ex) {
            System.out.println(ex + " \n " + entities);
            //throw new SystemException("SQL Exception", ex, SQL_EXCEPTION);
        } catch (IllegalAccessException e) {
            throw new SystemException("Illegal Access Exception", e, ILLEGAL_ACCESS_EXCEPTION);
        } catch (NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch(SQLException ex) {
                throw new SystemException("Problem during close.", ex, SQL_EXCEPTION);
            }
        }
    }


    public String getType() {
        return this.clazz.getSimpleName();
    }
}
