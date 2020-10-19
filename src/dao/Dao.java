package dao;


import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
    //общий дао интерфейс
    void add(T t) throws SQLException;

    List<T> getAll() throws SQLException;

    T getBiId(int id) throws SQLException;

    void update(T t) throws SQLException;

    void remove (T t) throws SQLException;

}
