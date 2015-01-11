package pl.semantyk.databaseutils;

import pl.semantyk.domain.annotation.Column;
import pl.semantyk.exceptions.SystemException;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Maps ResultSet to result object.
 *
 * @author Sebastian Fabisz
 */
public class ResultMapper {

    public static Object getResult(Class clazz, ResultSet resultSet) throws IllegalAccessException, InstantiationException, SQLException, SystemException {
        Field[] fields = clazz.getDeclaredFields();

        Object result = clazz.newInstance();

        while (resultSet.next()) {
            for (Field field: fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    field.setAccessible(true);
                    Column column = field.getAnnotation(Column.class);
                    Class type = field.getType();
                    Object value;
                    if (type.equals(Integer.class)) {
                       value = resultSet.getInt(column.name());
                    } else if (type.equals(Double.class)) {
                        value = resultSet.getDouble(column.name());
                    } else if (type.equals(String.class)) {
                        value = resultSet.getString(column.name());
                    } else if (type.equals(Boolean.class)) {
                        value = resultSet.getBoolean(column.name());
                    } else if (type.equals(Long.class)) {
                        value = resultSet.getLong(column.name());
                    } else {
                        throw new SystemException("Unsupported type in ParameterSetter." , null, null);
                    }
                    field.set(result, value);
                }
            }
        }

        return result;
    }

    public static List<Object> getResultList(Class clazz, ResultSet resultSet) throws SQLException, IllegalAccessException, InstantiationException, SystemException {
        List<Object> resultList = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();

        while (resultSet.next()) {
            Object obj = clazz.newInstance();

            for (Field field: fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    field.setAccessible(true);
                    Column column = field.getAnnotation(Column.class);
                    Class type = field.getType();
                    Object value;
                    if (type.equals(Integer.class)) {
                        value = resultSet.getInt(column.name());
                    } else if (type.equals(Double.class)) {
                        value = resultSet.getDouble(column.name());
                    } else if (type.equals(String.class)) {
                        value = resultSet.getString(column.name());
                    } else if (type.equals(Boolean.class)) {
                        value = resultSet.getBoolean(column.name());
                    } else if (type.equals(Long.class)) {
                        value = resultSet.getLong(column.name());
                    } else {
                        throw new SystemException("Unsupported type in ParameterSetter." , null, null);
                    }
                    field.set(obj, value);
                }
            }
            resultList.add(obj);
        }
        return resultList;
    }


}
