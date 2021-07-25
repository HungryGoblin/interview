import lombok.Data;
import lombok.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Data
abstract class AbstractRepositoryRaw<T> {
    private final DBConnection dbConnection = new DBConnection();

    protected Class<T> entityClass = null;
    private Field[] entityFields = null;
    private String insertTemplate;

    private String getInsertTemplate() {
        String template = String.format("insert into %s ($FIELDS$) values ($VALUES$)", entityClass.getTypeName());
        String fieldsList = "";
        String valuesList = "";
        for (int i = 0; i < entityFields.length; i++) {
            valuesList = String.format("%s,%s", valuesList, "?");
            fieldsList = String.format("%s,%s", fieldsList, entityFields[i].getName());
        }
        template = template
                .replace("$FIELDS$", fieldsList.replaceAll("^,", ""))
                .replace("$VALUES$", valuesList.replaceAll("^,", ""));
        return template;
    }

    private T mapResultSetToObject(ResultSet resultSet) {
        T object = null;
        try {
            object = entityClass.getDeclaredConstructor().newInstance();
            for (Field field : entityClass.getDeclaredFields()) {
                field.setAccessible(true);
                field.set(object, resultSet.getObject(field.getName()));
            }
        } catch (SQLException | IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException e) {
            throw new ClassCastException(String.format(
                    "Can't map object %s: %s", entityClass.getTypeName(), e.getMessage()));
        }
        return object;
    }

    public List<T> findAll() throws SQLException, ClassNotFoundException {
        List<T> list = new ArrayList<>();
        try (Connection connection = dbConnection.get()) {
            ResultSet resultSet = connection.createStatement().executeQuery(
                    String.format("select * from %s", entityClass.getTypeName()));
            if (resultSet.isBeforeFirst()) {
                while (true) {
                    if (!resultSet.next()) break;
                    list.add(mapResultSetToObject(resultSet));
                }
            }
        }
        return list;
    }

    public T findById(Long id) throws SQLException, ClassNotFoundException {
        T object = null;
        try (Connection connection = dbConnection.get()) {
            ResultSet resultSet = connection.createStatement().executeQuery(
                    String.format("select * from %s where id = %d", entityClass.getTypeName(), id));
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                object = mapResultSetToObject(resultSet);
            }
        }
        return object;
    }

    public void deleteById(Long id) throws SQLException, ClassNotFoundException {
        boolean result;
        try (Connection connection = dbConnection.get()) {
            connection.createStatement().executeUpdate(
                    String.format("delete from %s where id = %d", entityClass.getTypeName(), id));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException(String.format("Delete %s.id = %d failed: %s",
                    entityClass.getTypeName(), id, e.getMessage()));
        }
    }

    public void insert(@NonNull T entity) throws SQLException, ClassNotFoundException {
        try (Connection connection = dbConnection.get()) {
            PreparedStatement preparedStatement = connection.prepareStatement(insertTemplate);
            for (int i = 1; i <= entityFields.length; i++) {
                Field field = entity.getClass().getDeclaredField(entityFields[i - 1].getName());
                field.setAccessible(true);
                preparedStatement.setObject(i, field.get(entity));
            }
            preparedStatement.execute();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            throw new SQLException(String.format("Insert %s failed: %s", entityClass, e.getMessage()));
        }
    }

    public AbstractRepositoryRaw() {
        Type type = this.getClass().getGenericSuperclass();
        this.entityClass = ((Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0]);
        this.entityFields = entityClass.getDeclaredFields();
        this.insertTemplate = getInsertTemplate();
    }
}
