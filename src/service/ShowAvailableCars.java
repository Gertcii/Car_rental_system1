package service;

import daoImpl.CarsDaoImpl;
import print.PrintCars;

import java.sql.SQLException;

public class ShowAvailableCars {
    //создание списка машин из бд и вывод его в консоль

    public void showCars() throws SQLException {
        CarsDaoImpl cdi = new CarsDaoImpl();
        PrintCars pc = new PrintCars();
        pc.printCar(cdi.getAll());

    }
    public void showCars(String brand) throws SQLException {
        CarsDaoImpl cdi = new CarsDaoImpl();
        PrintCars pc = new PrintCars();
        pc.printCar(cdi.getByBrand(brand));

    }
    public void showCars(int price) throws SQLException {
        CarsDaoImpl cdi = new CarsDaoImpl();
        PrintCars pc = new PrintCars();
        pc.printCar(cdi.getByPrice(price));

    }
    public void showCars(String brand, String price) throws SQLException {
        CarsDaoImpl cdi = new CarsDaoImpl();
        PrintCars pc = new PrintCars();
        int priceInt = Integer.parseInt(price);
        pc.printCar(cdi.getByBrandAndPrice(brand, priceInt));
    }

    public boolean noParameters(String brand, String price){
        return (brand == null || brand.equals("")) && (price == null || price.equals(""));
    }
    public boolean onlyBrand(String brand, String price) {
        return (brand != null || !brand.equals("")) && (price == null || price.equals(""));
    }
    public boolean onlyPrice(String brand, String price) {
        return (brand == null || brand.equals("")) && (price != null || !price.equals(""));
    }
    public boolean brandAndPrice(String brand, String price) {
        return (brand != null || !brand.equals("")) && (price != null || !price.equals(""));
    }
}
