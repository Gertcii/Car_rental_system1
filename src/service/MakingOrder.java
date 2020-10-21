package service;

import daoImpl.CarsDaoImpl;
import daoImpl.OrdersDaoImpl;
import entity.Cars;
import entity.Drivers;
import entity.Orders;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class MakingOrder {
    //оформление заказа и внесение его в бд


    public LocalDate setDate(int year, int month, int day){
        LocalDate date =  LocalDate.of(year, month, day);
        return date;
    }
    public Cars getCarById(int id) throws SQLException {
        CarsDaoImpl cdi = new CarsDaoImpl();
        Cars car = cdi.getBiId(id);
        return car;
    }

    public Orders fillOrderForm(Cars car, Drivers drivers, String dateFrom, String dateTo) throws SQLException {

        String[] dateFromSplit = (dateFrom.split("-"));
        int[] dateFromInt = new int[3];
        String[] dateToSplit = (dateTo.split("-"));
        int[] dateToInt = new int[3];
        for (int i = 0; i < dateFromSplit.length; i++) {
            dateFromInt[i] = Integer.parseInt(dateFromSplit[i]);
        }
        for (int i = 0; i < dateToSplit.length; i++) {
            dateToInt[i] = Integer.parseInt(dateToSplit[i]);
        }
        LocalDate receipt = LocalDate.of(dateFromInt[0], dateFromInt[1], dateFromInt[2]);
        LocalDate returnDate = LocalDate.of(dateToInt[0], dateToInt[1], dateToInt[2]);
        OrdersDaoImpl odi = new OrdersDaoImpl();

        Orders order = new Orders();
        order.setIdOrderedCar(car.getId());
        order.setIdDriver(drivers.getId());
        order.setOrderedCar(car.getModel());
        order.setDriversSurname(drivers.getLastName());
        order.setDateOfReceipt(Date.valueOf(receipt));
        order.setReturnDate(Date.valueOf(returnDate));


        return order;

    }

    public void calcTotal(Orders order, Cars car) throws SQLException {

        OrdersDaoImpl odi = new OrdersDaoImpl();

        int duration = odi.duration(order);
        int total = duration * car.getCost();
        order.setTotalAmount(total);
    }

    public void pushToDB(Orders orders) throws SQLException {
        OrdersDaoImpl odi = new OrdersDaoImpl();
        odi.add(orders);
    }
    public void setIdFromDb(Orders orders) throws SQLException {
        OrdersDaoImpl odi = new OrdersDaoImpl();
        odi.setCurrentId(orders);
    }
}
