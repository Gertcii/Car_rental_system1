package print;

import entity.Drivers;

public class PrintDrivers {
    //вывод в консоль id водителя, чтобы оформить заказ
    public void printDriversId(Drivers driver){
        System.out.println("================");
        System.out.println("Your id is " + driver.getId());
        System.out.println("================");
    }
}
