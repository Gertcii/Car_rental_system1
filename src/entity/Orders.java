package entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Orders {
    //сущность order
    private int id;
    private int idOrderedCar;
    private int idDriver;
    private  String driversSurname;
    private String orderedCar;
    private LocalDate dateOfReceipt;
    private LocalDate returnDate;
    private int totalAmount;

    public int getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(int idDriver) {
        this.idDriver = idDriver;
    }

    public int getIdOrderedCar() {
        return idOrderedCar;
    }

    public void setIdOrderedCar(int idOrderedCar) {
        this.idOrderedCar = idOrderedCar;
    }

    public String getDriversSurname() {
        return driversSurname;
    }

    public void setDriversSurname(String driversSurname) {
        this.driversSurname = driversSurname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderedCar() {
        return orderedCar;
    }

    public void setOrderedCar(String orderedCar) {
        this.orderedCar = orderedCar;
    }

    public Date getDateOfReceipt(){
        Date date = Date.valueOf(dateOfReceipt);
        return date; }

    public void setDateOfReceipt(Date dateOfReceipt) { this.dateOfReceipt = dateOfReceipt.toLocalDate();}

    public Date getReturnDate() {

        Date date = Date.valueOf(returnDate);
        return date;}

    public void setReturnDate(Date returnDate) {this.returnDate = returnDate.toLocalDate();}

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders order = (Orders ) o;
        return id == order.id &&
                idOrderedCar == order.idOrderedCar &&
                idDriver == order.idDriver &&
                Objects.equals(driversSurname, order.driversSurname) &&
                Objects.equals(dateOfReceipt, order.dateOfReceipt) &&
                Objects.equals(returnDate, order.returnDate) &&
                totalAmount == order.totalAmount &&
                Objects.equals(orderedCar, order.orderedCar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idOrderedCar, idDriver, driversSurname,   orderedCar, dateOfReceipt, returnDate, totalAmount);
    }

    @Override
    public String toString(){
        return ("Order number is: " + id + ". Your car is \"" + orderedCar + "\".  " + " Ordered from " + dateOfReceipt.toString() +
                " to " + returnDate.toString() + ". Total amount: " + totalAmount);
    }
}
