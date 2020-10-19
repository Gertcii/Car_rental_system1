package daoImpl;


import bl.DataBaseConnectionFactory;

import dao.CarsDao;
import dao.Dao;
import entity.Cars;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarsDaoImpl extends DataBaseConnectionFactory implements CarsDao {
    //реализация методов CarsDao
    private Connection connection = createConnection();

    @Override
    public void add(Cars car) throws SQLException{
        PreparedStatement ps = null;
        String sql = "INSERT INTO cars (brand, model, doors, passengers, cost, transmission)" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try{
            ps = connection.prepareStatement(sql);
            ps.setString(1, car.getBrand());
            ps.setString(2, car.getModel());
            ps.setInt(3, car.getDoors());
            ps.setInt(4, car.getPassengers());
            ps.setInt(5, car.getCost());
            ps.setString(6, car.getTransmission());

            ps.executeUpdate();
        } catch (SQLException e){
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
    public Cars getBiId(int id) throws SQLException{
        Statement st = null;
        String sql = "SELECT * FROM cars WHERE id_car = " + id;

        Cars car = new Cars();
        try {
            st = connection.createStatement();


            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){

            car.setId(rs.getInt("id_car"));
            car.setBrand(rs.getString("brand"));
            car.setModel(rs.getString("model"));
            car.setDoors(rs.getInt("doors"));
            car.setPassengers(rs.getInt("passengers"));
            car.setCost(rs.getInt("cost"));
            car.setTransmission(rs.getString("transmission"));
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (st != null) { st.close();}
            if (connection != null) {connection.close();}

        }
        return car;

    }


    @Override
    public List<Cars> getAll() throws SQLException {
        List<Cars> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars";

        Statement st = null;
        try{
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                Cars car = new Cars();
                car.setId(rs.getInt("id_car"));
                car.setBrand(rs.getString("brand"));
                car.setModel(rs.getString("model"));
                car.setDoors(rs.getInt("doors"));
                car.setPassengers(rs.getInt("passengers"));
                car.setCost(rs.getInt("cost"));
                car.setTransmission(rs.getString("transmission"));

                cars.add(car);

            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {

            if (st != null) { st.close();}

            if (connection != null) { connection.close();}
        }
        return cars;
    }

    @Override
    public List<Cars> getByPrice(int price) throws SQLException {
        List<Cars> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars WHERE cost <= " + price + " ORDER BY cost";

        Statement st = null;
        try{
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                Cars car = new Cars();
                car.setId(rs.getInt("id_car"));
                car.setBrand(rs.getString("brand"));
                car.setModel(rs.getString("model"));
                car.setDoors(rs.getInt("doors"));
                car.setPassengers(rs.getInt("passengers"));
                car.setCost(rs.getInt("cost"));
                car.setTransmission(rs.getString("transmission"));

                cars.add(car);

            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {

            if (st != null) { st.close();}

            if (connection != null) { connection.close();}
        }
        if (cars.size() == 0){
            System.out.println("No cars found");
            return null;
        } else {
            return cars;
        }

    }

    @Override
    public List<Cars> getByBrand(String brand) throws SQLException {
        List<Cars> cars = new ArrayList<>();
        brand = brand.toUpperCase();
        brand = brand.trim();
        String sql = "SELECT * FROM cars WHERE brand LIKE '" + brand + "'";

        Statement st = null;
        try{
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                Cars car = new Cars();
                car.setId(rs.getInt("id_car"));
                car.setBrand(rs.getString("brand"));
                car.setModel(rs.getString("model"));
                car.setDoors(rs.getInt("doors"));
                car.setPassengers(rs.getInt("passengers"));
                car.setCost(rs.getInt("cost"));
                car.setTransmission(rs.getString("transmission"));

                cars.add(car);

            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {

            if (st != null) {st.close();}

            if (connection != null) { connection.close();}
        }

        if (cars.size() == 0){
            System.out.println("No cars found");
            return null;
        } else {
            return cars;
        }
    }

    @Override
    public List<Cars> getByBrandAndPrice(String brand, int price) throws SQLException {
        List<Cars> cars = new ArrayList<>();
        brand = brand.toUpperCase();
        brand = brand.trim();
        String sql = "SELECT * FROM cars WHERE brand LIKE '" + brand + "' AND cost <= " + price;

        Statement st = null;
        try{
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                Cars car = new Cars();
                car.setId(rs.getInt("id_car"));
                car.setBrand(rs.getString("brand"));
                car.setModel(rs.getString("model"));
                car.setDoors(rs.getInt("doors"));
                car.setPassengers(rs.getInt("passengers"));
                car.setCost(rs.getInt("cost"));
                car.setTransmission(rs.getString("transmission"));

                cars.add(car);

            }
        } catch (SQLException e){
            System.out.println("No cars found");

        } finally {

            if (st != null) { st.close(); }

            if (connection != null) { connection.close();}
        }
        if (cars.size() == 0){
            System.out.println("No cars found");
            return null;
        } else {
            return cars;
        }
    }
    @Override
    public void update(Cars car) throws SQLException{
        PreparedStatement ps = null;
        String sql = "UPDATE cars SET brand = ?, model = ?, doors = ?, passengers = ?, cost = ?, transmission = ? " +
                "WHERE id_car = ?";
        try{
            ps = connection.prepareStatement(sql);

            ps.setString(1, car.getBrand());
            ps.setString(2, car.getModel());
            ps.setInt(3, car.getDoors());
            ps.setInt(4, car.getPassengers());
            ps.setInt(5, car.getCost());
            ps.setString(6, car.getTransmission());

            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();

        } finally {

            if (ps != null) { ps.close(); }

            if (connection != null) { connection.close();}
        }
    }

    @Override
    public void remove (Cars car) throws SQLException{
        PreparedStatement ps = null;

        String sql = "DELETE FROM cars WHERE id_car = ?";

        try{
            ps = connection.prepareStatement(sql);

            ps.setInt(1, car.getId());

            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();

        } finally {

            if (ps != null) { ps.close(); }

            if (connection != null) { connection.close();}
        }

    }

}
