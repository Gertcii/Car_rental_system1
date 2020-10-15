package dao;

import entity.Cars;

import java.sql.SQLException;
import java.util.List;

public interface CarsDao extends Dao<Cars> {
    //дао+ интерфейс для работы таблицей Cars
    List<Cars> getByPrice(int price) throws SQLException;

    List<Cars> getByBrand(String brand) throws SQLException;

    List<Cars> getByBrandAndPrice(String brand, int price) throws SQLException;
}
