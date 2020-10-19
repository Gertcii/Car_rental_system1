package dao;

import entity.Orders;

import java.sql.SQLException;
import java.time.LocalDate;

public interface OrdersDao extends Dao<Orders> {
    //дао + интерфейс для работы с таблицей order_list
    void setCurrentId(Orders orders) throws SQLException;

    void setModelById(Orders orders) throws SQLException;

    void setSurnameById(Orders orders) throws SQLException;

    int duration(Orders orders) throws SQLException;
}
