package daoImpl;

import bl.DataBaseConnectionFactory;
import dao.DriversDao;
import entity.Drivers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DriversDaoImpl extends DataBaseConnectionFactory implements DriversDao{
    //реализация методов DriversDao
    private Connection connection = createConnection();

    @Override
    public void add(Drivers drivers) throws SQLException {

        PreparedStatement ps = null;
        String sql = "INSERT INTO drivers (surname, name, passport, experience) " +
                "VALUES (?, ?, ?, ?)";


        try{
            ps = connection.prepareStatement(sql);
            ps.setString(1, drivers.getLastName());
            ps.setString(2, drivers.getFirstName());
            ps.setString(3, drivers.getPassportNumber());
            ps.setInt(4, drivers.getDrivingExperience());

            ps.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if(ps != null) {ps.close();}

            if (connection != null) {connection.close();}
        }

    }

    @Override
    public void setIdFroDB(Drivers driver) throws SQLException{
        Statement st = null;
        String sqlId = "SELECT id_drivers FROM drivers WHERE passport = '" + driver.getPassportNumber() + "'";
        try{
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sqlId);

            while(rs.next()){
                driver.setId(rs.getInt("id_drivers"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {

            if (st != null) {st.close();}

            if (connection != null) {connection.close();}
        }

    }

    @Override
    public List<Drivers> getAll() throws SQLException {
        List<Drivers> driversList = new ArrayList<>();
        String sql = "SELECT * FROM drivers";

        Statement st = null;

        try{
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                Drivers driver = new Drivers();
                driver.setId(rs.getInt("id_drivers"));
                driver.setLastName(rs.getString("surname"));
                driver.setPassportNumber(rs.getString("passport"));
                driver.setDrivingExperience(rs.getInt("experience"));

                driversList.add(driver);
            }
        }catch (SQLException e){
            e.printStackTrace();
        } finally {

            if (st != null) { st.close();}

            if (connection != null) { connection.close();}
        }
        return driversList;
    }

    @Override
    public Drivers getBiId(int id) throws SQLException {
        PreparedStatement ps = null;
        String sql = "SELECT * FROM drivers WHERE id_drivers = ?";

        Drivers driver = new Drivers();
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            driver.setId(rs.getInt("id_drivers"));
            driver.setLastName(rs.getString("surname"));
            driver.setFirstName(rs.getString("name"));
            driver.setPassportNumber(rs.getString("passport"));
            driver.setDrivingExperience(rs.getInt("experience"));

            ps.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (ps != null) { ps.close();}
            if (connection != null) {connection.close();}

        } if(driver == null){
            System.out.println("There are no drivers with that id");
        }
        return driver;
    }


    @Override
    public List<Drivers> getBySurname(String lastName) throws SQLException{
        List<Drivers> driversList = new ArrayList<>();

        String sql = "SELECT * FROM drivers WHERE surname = " + lastName;

        Statement st = null;

        try{
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                Drivers driver = new Drivers();
                driver.setId(rs.getInt("id_car"));
                driver.setLastName(rs.getString("surname"));
                driver.setFirstName(rs.getString("name"));
                driver.setPassportNumber(rs.getString("passport"));
                driver.setDrivingExperience(rs.getInt("experience"));

                driversList.add(driver);

            }

        }catch (SQLException e){
            e.printStackTrace();
        } finally {

            if (st != null) { st.close();}

            if (connection != null) { connection.close();}
        }
        if (driversList.size() == 0 ){
            System.out.println("There are no drivers with that Surname");
        }

        return driversList;
    }

    @Override
    public Drivers getByPassportNumber(String passportNumber) throws SQLException{
        Drivers driver = new Drivers();

        String sql = "SELECT * FROM drivers WHERE surname = " + passportNumber;

        Statement st = null;

        try{
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){

                driver.setId(rs.getInt("id_car"));
                driver.setLastName(rs.getString("surname"));
                driver.setFirstName(rs.getString("name"));
                driver.setPassportNumber(rs.getString("passport"));
                driver.setDrivingExperience(rs.getInt("experience"));


            }

        }catch (SQLException e){
            e.printStackTrace();
        } finally {

            if (st != null) { st.close();}

            if (connection != null) { connection.close();}
        }
        if (driver == null){
            System.out.println("There are no drivers with that passport number");
        }

        return driver;
    }

    @Override
    public void update(Drivers drivers) throws SQLException {
        PreparedStatement ps = null;
        String sql = "UPDATE drivers SET surname = ?, name = ?, passport = ?, experience = ?" +
                "WHERE id_drivers = " + drivers.getId();
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, drivers.getLastName());
            ps.setString(2, drivers.getFirstName());
            ps.setString(3, drivers.getPassportNumber());
            ps.setInt(4, drivers.getDrivingExperience());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }

        }
    }


    @Override
    public void remove (Drivers drivers) throws SQLException {
        PreparedStatement ps = null;

        String sql = "DELETE FROM drivers WHERE id_drivers = ?";

        try{
            ps = connection.prepareStatement(sql);

            ps.setInt(1, drivers.getId());

            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();

        } finally {

            if (ps != null) { ps.close(); }

            if (connection != null) { connection.close();}
        }
    }
    @Override
    public boolean isValidName(String name){
        if (name == null || name.equals("")){

            return false;
        }
        String namePattern = "^[A-ZА-ЯЁa-zа-яё]{3,20}$";
        Pattern pt = Pattern.compile(namePattern);
        Matcher mt = pt.matcher(name);
        return mt.matches();
    }

    @Override
    public boolean isValidPassport(String passport){
        if (passport == null || passport.equals("")){
            System.out.println("Shouldn't be empty");
            return false;
        }
        String passportPattern = "^[0-9]{4} [0-9]{6}$";
        Pattern pt = Pattern.compile(passportPattern);
        Matcher mt = pt.matcher(passport);
        return mt.matches();
    }

    @Override
    public boolean isValidExperience(int experience){
        if (experience <= 1){
            return false;
        }
        else
            return true;
    }


}
