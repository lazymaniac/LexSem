package pl.semantyk.databaseutils;

import pl.semantyk.domain.annotation.Column;
import pl.semantyk.exceptions.SystemException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Use reflection to dynamically set parameters in PreparedStatement.
 *
 * @author Sebastian Fabisz
 */
public class StatementParameterSetter {

    public static void setAllParameters(PreparedStatement statement, Class clazz, Object source)
            throws IllegalAccessException, SQLException, SystemException, NoSuchMethodException, InvocationTargetException {
        int paramCounter = 1;

        for (Field field: clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                field.setAccessible(true);
                Class type = field.getType();

                if (type.equals(Integer.class) || type.equals(int.class)) {
                    int i = (Integer) field.get(source);
                    if (i > -1)
                        statement.setInt(paramCounter++, i);
                    else
                        statement.setNull(paramCounter++, Types.INTEGER);
                } else if (type.equals(Double.class)) {
                    statement.setDouble(paramCounter++, (Double) field.get(source));
                } else if (type.equals(String.class)) {
                    statement.setString(paramCounter++, (String) field.get(source));
                } else if (type.equals(Boolean.class)) {
                    statement.setBoolean(paramCounter++, (Boolean) field.get(source));
                } else if (type.equals(Long.class)) {
                    statement.setLong(paramCounter++, (Long) field.get(source));
                } else { // enum
                    Method method = type.getMethod("getValue",  null);
                    Object object = field.get(source);
                    Object value = method.invoke(object, null);
                    statement.setString(paramCounter++, (String) value);
                }
            }
        }
    }

    public static void setId(PreparedStatement statement, Integer id) throws SQLException {
        statement.setInt(1, id);
    }
}
