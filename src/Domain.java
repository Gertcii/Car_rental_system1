import entity.Cars;
import entity.Drivers;
import entity.Orders;
import print.PrintDrivers;
import print.PrintOrder;
import service.MakingOrder;
import service.Registration;
import service.ShowAvailableCars;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Domain {

    public static void main(String[] args) throws SQLException, IOException {

        System.out.println("Please register to select and rent a car");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your surname");
        String surname = sc.nextLine();


        System.out.println("Enter your name");
        String name = sc.nextLine();


        System.out.println("Enter your passport number in format 1234 567890");
        String passport = sc.nextLine();


        System.out.println("Enter your driving experience");
        int experience = sc.nextInt();



        Registration reg = new Registration();

        Drivers driver = reg.createDriver(surname, name, passport, experience);


        if(reg.isValidDriverData(driver)){

            reg.formIsFilled(driver);

            reg.updateId(driver);

            PrintDrivers printId = new PrintDrivers();
            printId.printDriversId(driver);

            sc.nextLine();

            System.out.println("Choose car brand or press Enter");
            String carBrand = sc.nextLine();

            System.out.println("Set quot maximum price");
            String carPrice = sc.nextLine();

            ShowAvailableCars sac = new ShowAvailableCars();

            if(sac.noParameters(carBrand, carPrice)){
                sac.showCars();
            }
            else if (sac.onlyBrand(carBrand, carPrice)){
                sac.showCars(carBrand);
            }
            else if(sac.onlyPrice(carBrand, carPrice)){
                sac.showCars(carPrice);
            }
            else if (sac.brandAndPrice(carBrand, carPrice)){
                sac.showCars(carBrand, carPrice);
            }
            //оформление заказа

            System.out.println("Enter cars id");
            int carId = sc.nextInt();


            System.out.println("Enter date of receipt in format \"YYYY-MM-DD\"");
            String n = sc.nextLine();
            String dateFrom = sc.nextLine();


            System.out.println("Enter return date in format \"YYYY-MM-DD\"");
            String dateTo = sc.nextLine();
            MakingOrder mo = new MakingOrder();
            Cars car = mo.getCarById(carId);
            Orders order = mo.fillOrderForm(car, driver, dateFrom, dateTo);
            mo.calcTotal(order, car);
            mo.pushToDB(order);

            mo.setIdFromDb(order);

            PrintOrder po = new PrintOrder();
            po.print(order);

        }


        else{
            System.out.println("проверьте данные пользователя");

        }



    }
}
