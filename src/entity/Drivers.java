package entity;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Drivers {
    //сущность driver
    private int id;
    private String lastName;
    private String firstName;
    private String passportNumber;
    private int drivingExperience;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getDrivingExperience() {
        return drivingExperience;
    }

    public void setDrivingExperience(int drivingExperience) {
        this.drivingExperience = drivingExperience;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drivers drivers = (Drivers) o;
        return id == drivers.id &&
                drivingExperience == drivers.drivingExperience &&
                Objects.equals(lastName, drivers.lastName) &&
                Objects.equals(firstName, drivers.firstName) &&
                Objects.equals(passportNumber, drivers.passportNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, passportNumber, drivingExperience);
    }

    @Override
    public String toString(){
        return (id + " " + lastName + " " + firstName + " " +
                passportNumber + " " + drivingExperience);
    }

}
