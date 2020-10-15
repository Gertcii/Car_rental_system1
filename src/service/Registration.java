package service;

import daoImpl.DriversDaoImpl;
import entity.Drivers;
import print.PrintDrivers;

import java.sql.SQLException;


public class Registration {
    //регистрация пользователя и внесение его в бд

    public Drivers createDriver(String surname, String name, String passport, int experience){
        Drivers driver = new Drivers();

        driver.setLastName(surname);
        driver.setFirstName(name);
        driver.setPassportNumber(passport);
        driver.setDrivingExperience(experience);

        return driver;

    }
    public boolean isValidDriverData(Drivers driver) {
        DriversDaoImpl ddi = new DriversDaoImpl();

        boolean result = false;

        if (ddi.isValidName(driver.getLastName()) && ddi.isValidName(driver.getFirstName()) &&
                ddi.isValidPassport(driver.getPassportNumber()) && ddi.isValidExperience(driver.getDrivingExperience())) {
            result = true;
        }
        else if(!ddi.isValidName(driver.getLastName()) || !ddi.isValidName(driver.getFirstName())){

            System.out.println("Should contain only Latin characters.");
            System.out.println("Can be 3-20 characters long.");
            result = false;
        }
        else if (! ddi.isValidPassport(driver.getPassportNumber())){
            System.out.println("Check the format of the passport number.");
            result = false;
        }
        else if (!ddi.isValidExperience(driver.getDrivingExperience())){
            System.out.println("You do not have enough driving experience");
            result = false;
        }
        return result;

    }

    public void formIsFilled(Drivers driver) throws SQLException {
        DriversDaoImpl ddi = new DriversDaoImpl();

        ddi.add(driver);
        //ddi.setIdFroDB(driver);

    }
    public void updateId(Drivers driver) throws SQLException {
        DriversDaoImpl ddi = new DriversDaoImpl();

        ddi.setIdFroDB(driver);
    }
}
