package tests;

import daoImpl.DriversDaoImpl;
import entity.Drivers;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import service.Registration;

import java.sql.SQLException;
import java.util.List;

public class RegistrationTest {
    Registration reg = new Registration();
    DriversDaoImpl ddi = new DriversDaoImpl();

    @Test
    public void testCreateDriver() {
        Drivers driver1 = new Drivers();
        driver1.setLastName("Ivanov");
        driver1.setFirstName("Ivan");
        driver1.setId(0);
        driver1.setPassportNumber("1111 111111");
        driver1.setDrivingExperience(5);

        Drivers driver2 = reg.createDriver("Ivanov", "Ivan", "1111 111111", 5);
        Assert.assertEquals(driver1, driver2);
    }

    @Test
    public void testValidData() {
        Drivers driver1 = new Drivers();
        driver1.setLastName("Ivanov");
        driver1.setFirstName("Ivan");
        driver1.setId(0);
        driver1.setPassportNumber("1111 111111");
        driver1.setDrivingExperience(5);
        Assert.assertEquals(true, reg.isValidDriverData(driver1));
    }
    @Test
    public void testFormIsFilledAndUpdateId() throws SQLException {
        Drivers driver1 = new Drivers();
        driver1.setLastName("Ivanov");
        driver1.setFirstName("Ivan");
        driver1.setId(0);
        driver1.setPassportNumber("1111 111111");
        driver1.setDrivingExperience(5);
        reg.formIsFilled(driver1);
        reg.updateId(driver1);
        System.out.println(driver1.toString());
        List<Drivers> driversList = ddi.getAll();
        System.out.println(driversList.get(driversList.size() -1));
        Assert.assertEquals(driversList.contains(driver1), true);
        Assert.assertEquals( driver1.getId() != 0, true);
    }
}
