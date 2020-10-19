package tests;

import com.sun.deploy.panel.JreTableModel;
import daoImpl.CarsDaoImpl;
import entity.Cars;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import service.ShowAvailableCars;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowCarsTest {
    ShowAvailableCars sac = new ShowAvailableCars();
    CarsDaoImpl cdi = new CarsDaoImpl();

    @Test
    public void showAllCarsTest() throws SQLException {
        Cars carBMV = new Cars();
        carBMV.setBrand("BMV");
        carBMV.setModel("X!");
        carBMV.setPassengers(5);
        carBMV.setDoors(5);
        carBMV.setCost(2500);
        carBMV.setTransmission("authomatic");
        cdi.add(carBMV);

        CarsDaoImpl cdi1 = new CarsDaoImpl();
        List<Cars> carsList = cdi1.getAll();

        Assert.assertEquals(carsList.contains(carBMV), true);

    }
    @Test
    public void showCarsByPriceTest() throws SQLException {
        Cars carHYU = new Cars();
        carHYU.setBrand("HYUNDAI");
        carHYU.setModel("CRETA");
        carHYU.setPassengers(5);
        carHYU.setDoors(5);
        carHYU.setCost(1600);
        carHYU.setTransmission("authomatic");
        cdi.add(carHYU);

        CarsDaoImpl cdi1 = new CarsDaoImpl();
        List<Cars> carsList = cdi1.getByPrice(1600);

        Assert.assertEquals(carsList.contains(carHYU), true);

    }
    @Test
    public void getByBrandAndPriceTest() throws SQLException {
        Cars carREN = new Cars();
        carREN.setBrand("RENAULT");
        carREN.setModel("DUSTER");
        carREN.setPassengers(5);
        carREN.setDoors(5);
        carREN.setCost(1700);
        carREN.setTransmission("authomatic");
        cdi.add(carREN);

        CarsDaoImpl cdi1 = new CarsDaoImpl();
        List<Cars> carsList = cdi1.getByBrandAndPrice("RENAULT", 1700);
        Assert.assertEquals(carsList.contains(carREN), true);

    }
    @Test
    public void getByBrandTest() throws SQLException {
        Cars carMERS = new Cars();
        carMERS.setBrand("MERCEDES-BENZ");
        carMERS.setModel("A200");
        carMERS.setPassengers(5);
        carMERS.setDoors(5);
        carMERS.setCost(2900);
        carMERS.setTransmission("authomatic");
        cdi.add(carMERS);

        CarsDaoImpl cdi1 = new CarsDaoImpl();
        List<Cars> carsList = cdi1.getByBrand("MERCEDES-BENZ");
        Assert.assertEquals(carsList.contains(carMERS), true);

    }
    @Test
    public void  noParametersTest(){
        Assert.assertEquals(true, sac.noParameters(null, null));
    }
    @Test
    public void  onlyBrandTest(){
        Assert.assertEquals(true, sac.onlyBrand("BRAND", null));
    }
    @Test
    public void  onlyPriceTest(){
        Assert.assertEquals(true, sac.onlyPrice(null, "1"));
    }
    @Test
    public void  brandAndPriceTest(){
        Assert.assertEquals(true, sac.brandAndPrice("BRAND", "1"));
    }


}
