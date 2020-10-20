package tests;


import com.sun.prism.shader.AlphaOne_Color_AlphaTest_Loader;
import daoImpl.CarsDaoImpl;
import daoImpl.DriversDaoImpl;
import daoImpl.OrdersDaoImpl;
import entity.Cars;
import entity.Drivers;
import entity.Orders;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.MakingOrder;
import service.Registration;
import service.ShowAvailableCars;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MakingOrderTest {
    MakingOrder mo = new MakingOrder();
    Registration reg = new Registration();
    //DriversDaoImpl ddi = new DriversDaoImpl();
    //CarsDaoImpl cdi = new CarsDaoImpl();


    @Test
    public void setDateTest(){
        LocalDate date1 = LocalDate.of(2020, 10, 10);
        LocalDate date2 = mo.setDate(2020, 10, 10);
        Assert.assertEquals(date1, date2);
    }
    @Test
    public void getCarByIdTest() throws SQLException {
        CarsDaoImpl cdi = new CarsDaoImpl();

        Cars car = new Cars();

        car.setBrand("BMV");
        car.setModel("X1");
        car.setPassengers(5);
        car.setDoors(5);
        car.setCost(2500);
        car.setTransmission("authomatic");

        cdi.add(car);


        Cars car1 = mo.getCarById(car.getId());

        Assert.assertEquals(car.equals(car1), true);


    }
    @Test
    public void fillOrderFormTest() throws SQLException {

        CarsDaoImpl cdi = new CarsDaoImpl();
        Cars car = new Cars();

        car.setBrand("BMV");
        car.setModel("X1");
        car.setPassengers(5);
        car.setDoors(5);
        car.setCost(2500);
        car.setTransmission("authomatic");

        cdi.add(car);



        DriversDaoImpl ddi = new DriversDaoImpl();
        Drivers driver = new Drivers();

        driver.setLastName("Petrov");
        driver.setFirstName("Petr");
        driver.setPassportNumber("9999 123456");
        driver.setDrivingExperience(5);
        reg.formIsFilled(driver);
        reg.updateId(driver);

        LocalDate dateOfReceipt = LocalDate.of(2020, 10, 10);
        LocalDate returnDate = LocalDate.of(2020, 10, 12);

        Orders order = new Orders();
        order.setIdDriver(driver.getId());
        order.setIdOrderedCar(car.getId());
        order.setOrderedCar(car.getModel());
        order.setDriversSurname(driver.getLastName());
        order.setDateOfReceipt(Date.valueOf(dateOfReceipt));
        order.setReturnDate(Date.valueOf(returnDate));

        Orders order1 = mo.fillOrderForm(car, driver, "2020-10-10", "2020-10-12");

        Assert.assertEquals(order, order1);

    }
    @Test
    public void calcTotalTest() throws SQLException {
        CarsDaoImpl cdi = new CarsDaoImpl();
        Cars car = new Cars();

        car.setBrand("BMV");
        car.setModel("X1");
        car.setPassengers(5);
        car.setDoors(5);
        car.setCost(2500);
        car.setTransmission("authomatic");

        cdi.add(car);

        DriversDaoImpl ddi = new DriversDaoImpl();
        Drivers driver = new Drivers();

        driver.setLastName("Petrov");
        driver.setFirstName("Petr");
        driver.setPassportNumber("9999 123456");
        driver.setDrivingExperience(5);

        reg.formIsFilled(driver);
        reg.updateId(driver);

        LocalDate dateOfReceipt = LocalDate.of(2020, 10, 10);
        LocalDate returnDate = LocalDate.of(2020, 10, 12);

        Orders order = new Orders();
        order.setIdDriver(driver.getId());
        order.setIdOrderedCar(car.getId());
        order.setOrderedCar(car.getModel());
        order.setDriversSurname(driver.getLastName());
        order.setDateOfReceipt(Date.valueOf(dateOfReceipt));
        order.setReturnDate(Date.valueOf(returnDate));

         mo.calcTotal(order, car);

         int totalMO = order.getTotalAmount();

         int total = car.getCost() * 2;

         Assert.assertEquals(totalMO, total);
    }
    @Test
    public void pushToDBAndSetIdFromDBTest() throws SQLException {
        LocalDate dateOfReceipt = LocalDate.of(2020, 10, 10);
        LocalDate returnDate = LocalDate.of(2020, 10, 12);

        Orders order = new Orders();
        order.setIdDriver(50);
        order.setIdOrderedCar(1);
        order.setDriversSurname("Petrov");
        order.setOrderedCar("VESTA");
        order.setDateOfReceipt(Date.valueOf(dateOfReceipt));
        order.setReturnDate(Date.valueOf(returnDate));
        order.setTotalAmount(2400);
        mo.pushToDB(order);
        mo.setIdFromDb(order);

        OrdersDaoImpl odi = new OrdersDaoImpl();
        List<Orders> ordersList = odi.getAll();

        Assert.assertEquals(ordersList.contains(order), true);

    }



}
