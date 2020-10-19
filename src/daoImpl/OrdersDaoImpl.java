package daoImpl;

import bl.DataBaseConnectionFactory;
import dao.OrdersDao;
import entity.Cars;
import entity.Orders;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdersDaoImpl extends DataBaseConnectionFactory implements OrdersDao {

    Connection connection = createConnection();

    @Override
    public void add(Orders orders) throws SQLException {
        PreparedStatement ps = null;
        String sql = "INSERT INTO order_list (id_driver, id_car, driver, model, date_of_receipt, return_date, total_amount)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try{
            ps = connection.prepareStatement(sql);

            ps.setInt(1, orders.getIdDriver());
            ps.setInt(2, orders.getIdOrderedCar());
            ps.setString(3, orders.getDriversSurname());
            ps.setString(4,orders.getOrderedCar());
            ps.setDate(5, orders.getDateOfReceipt());
            ps.setDate(6, orders.getReturnDate());
            ps.setInt(7, orders.getTotalAmount());

            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(ps != null){
                ps.close();
            }
            if (connection != null){
                connection.close();
            }
        }
    }

    @Override
    public List<Orders> getAll() throws SQLException {

        List<Orders> ordersList = new ArrayList<>();

        Statement st = null;
        String sql = "SELECT * FROM order_list";
        try{
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){

                Orders orders = new Orders();
                orders.setIdDriver(rs.getInt("id_driver"));
                orders.setIdOrderedCar(rs.getInt("id_car"));
                orders.setDriversSurname(rs.getString("driver"));
                orders.setOrderedCar(rs.getString("model"));
                orders.setDateOfReceipt(rs.getDate("date_of_receipt"));
                orders.setReturnDate(rs.getDate("return_date"));
                orders.setTotalAmount(rs.getInt("total_amount"));

                ordersList.add(orders);
            }

        }catch (SQLException e){
            e.printStackTrace();
        } finally {

            if (st != null) { st.close();}

            if (connection != null) { connection.close();}
        }


        return ordersList;
    }

    @Override
    public Orders getBiId(int id) throws SQLException {
        PreparedStatement ps = null;
        String sql = "SELECT * FROM order_lis WHERE id_order = ?";

        Orders orders = new Orders();
        try{
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            orders.setIdDriver(rs.getInt("id_driver"));
            orders.setIdOrderedCar(rs.getInt("id_car"));
            orders.setDriversSurname(rs.getString("driver"));
            orders.setOrderedCar(rs.getString("model"));
            orders.setDateOfReceipt(rs.getDate("date_of_receipt"));
            orders.setReturnDate(rs.getDate("return_date"));
            orders.setTotalAmount(rs.getInt("total_amount"));

        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (ps != null) { ps.close();}
            if (connection != null) {connection.close();}

        }
        return orders;

    }

    @Override
    public void update(Orders orders) throws SQLException {
        PreparedStatement ps = null;
        String sql = "UPDATE order_list SET id_driver = ?, id_car = ?, date_of_receipt = ?, " +
                "return_date = ?, total_amount = ? WHERE id_order = " + orders.getId();
        try{
            ps = connection.prepareStatement(sql);

            ps.setInt(1, orders.getIdDriver());
            ps.setInt(2, orders.getIdOrderedCar());
            ps.setString(3, orders.getDriversSurname());
            ps.setString(4,orders.getOrderedCar());
            ps.setDate(5, orders.getDateOfReceipt());
            ps.setDate(6, orders.getReturnDate());
            ps.setInt(7, orders.getTotalAmount());

            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }

    @Override
    public void remove(Orders orders) throws SQLException {
        PreparedStatement ps = null;
        String sql = "DELETE FROM order_list WHERE id_order = ?";

        try{
            ps = connection.prepareStatement(sql);

            ps.setInt(1, orders.getId());

            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }

    @Override
    public void setCurrentId(Orders orders) throws SQLException{

        Statement st = null;
        String sql = "SELECT id_order FROM order_list WHERE id_driver = " + orders.getIdDriver() +
                " AND id_car = " + orders.getIdOrderedCar() +
                " AND date_of_receipt = '" + orders.getDateOfReceipt().toString() +"'";
        try{
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                orders.setId(rs.getInt("id_order"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (st != null) {
                st.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }
    @Override
    public void setModelById(Orders orders) throws SQLException{
        Statement st = null;
        String sql = "SELECT model FROM cars WHERE id_cars = " + orders.getIdOrderedCar();

        try{
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                orders.setOrderedCar(rs.getString("model"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (st != null) {
                st.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }


    @Override
    public void setSurnameById(Orders orders) throws SQLException{

        Statement st = null;
        String sql = "SELECT surame FROM drivers WHERE id_drivers = " + orders.getIdDriver();

        try{
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                orders.setDriversSurname(rs.getString("surname"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (st != null) {
                st.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }


    @Override
    public int duration(Orders orders) throws SQLException{

        String sql = "SELECT DATEDIFF ('" + orders.getReturnDate().toString() + "', '" + orders.getDateOfReceipt().toString() +
                "') " + "AS duration";
        Statement st = null;
        int duration = 0;
        try{
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                duration = (rs.getInt("duration") );
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (st != null) {
                st.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return duration;
    }



    //реализация методов OrderDao

}
