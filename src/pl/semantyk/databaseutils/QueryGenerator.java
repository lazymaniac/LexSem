package pl.semantyk.databaseutils;

import pl.semantyk.domain.annotation.Column;
import pl.semantyk.domain.annotation.Id;
import pl.semantyk.domain.annotation.Table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Generates simple SQL queries for CRUD operations.
 *
 * @author Sebastian Fabisz
 */
public class QueryGenerator {

    public static String getInsertQueryForClass(Class clazz) {
        String resultQuery = INSERT_TEMPLATE;

        String tableReplaced = replaceTableTag(clazz, resultQuery);
        String columnsReplace = replaceColumnsTag(clazz, tableReplaced);

        return replaceValuesTag(clazz, columnsReplace);
    }

    public static String getFindByIdQueryForClass(Class clazz) throws IllegalAccessException {
        String resultQuery = FIND_BY_ID_TEMPLATE;

        String columnsReplaced = replaceColumnsTag(clazz, resultQuery);
        String tableReplaced = replaceTableTag(clazz, columnsReplaced);

        return replaceIdTag(clazz, tableReplaced);
    }

    public static String getFindAllQueryForClass(Class clazz) {
        String resultQuery = FIND_ALL_TEMPLATE;

        String columnsReplaced = replaceColumnsTag(clazz, resultQuery);

        return replaceTableTag(clazz, columnsReplaced);
    }

    public static String getDeleteQueryForClass(Class clazz) throws IllegalAccessException {
        String resultQuery = DELETE_TEMPLATE;

        String columnsReplaced = replaceColumnsTag(clazz, resultQuery);
        String tableReplaced = replaceTableTag(clazz, columnsReplaced);

        return replaceIdTag(clazz, tableReplaced);
    }

    public static String getDeleteAllQueryForClass(Class clazz) {
        String resultQuery = DELETE_ALL_TEMPLATE;

        String columnsReplaced = replaceColumnsTag(clazz, resultQuery);

        return replaceTableTag(clazz, columnsReplaced);
    }

    private static String replaceValuesTag(Class clazz, String resultQuery) {
        StringBuilder values = new StringBuilder();

        int fieldsWithColumnAnn = 0;
        for (Field field: clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class))
                fieldsWithColumnAnn++;
        }

        for (int i = 0; i < fieldsWithColumnAnn -1; i++)
            values.append("?, ");
        values.append("?");

        return resultQuery.replace(VALUES, values.toString());
    }

    private static String replaceTableTag(Class clazz, String resultQuery) {
        Table table = (Table) clazz.getAnnotation(Table.class);
        return resultQuery.replace(TABLE, "semantyk." + table.name());
    }

    private static String replaceIdTag(Class clazz, String resultQuery) {
        for (Field field: clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                Column idColumn = field.getAnnotation(Column.class);
                return resultQuery.replace(ID, idColumn.name());
            }
        }
        return "";
    }

    private static String replaceColumnsTag(Class clazz, String resultQuery) {
        Field[] fields = clazz.getDeclaredFields();

        StringBuilder columnsName = new StringBuilder();

        List<Field> annotatedFields = new ArrayList<>();

        for (Field field: fields) {
            if (field.isAnnotationPresent(Column.class)) {
                annotatedFields.add(field);
            }
        }

        for (int i = 0; i < annotatedFields.size() - 1; i++) {
            Column column = annotatedFields.get(i).getAnnotation(Column.class);
            columnsName.append(column.name()).append(", ");
        }
        columnsName.append(annotatedFields.get(annotatedFields.size()-1).getAnnotation(Column.class).name());

        return resultQuery.replace(COLUMNS, columnsName.toString());
    }

    // ============= PRIVATE VARS ============================
    private static final String INSERT_TEMPLATE = "INSERT INTO {table} ({columns}) VALUES ({values});";
    private static final String FIND_BY_ID_TEMPLATE = "SELECT {columns} FROM {table} WHERE {id} = ?";
    private static final String FIND_ALL_TEMPLATE = "SELECT {columns} FROM {table}";
    private static final String DELETE_TEMPLATE = "DELETE {columns} FROM {table} WHERE {id} = ?";
    private static final String DELETE_ALL_TEMPLATE = "DELETE {columns} FROM {table}";

    private static final String TABLE = "{table}";
    private static final String VALUES = "{values}";
    private static final String ID = "{id}";
    private static final String COLUMNS = "{columns}";
}
