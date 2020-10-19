package print;


import entity.Orders;

public class PrintOrder {
    //вывод в консоль информации о заказе
    public void print(Orders order){
        System.out.println(order.toString());
    }
}
