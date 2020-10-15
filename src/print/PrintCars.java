package print;

import entity.Cars;

import java.util.List;

public class PrintCars {
    //вывод в консоль списка машин
    public void printCar(List<Cars> carsList){
        for (Cars car:
             carsList) {
            System.out.println("____________________________________________________________");

            System.out.println(car.toString());
        }
    }
}
