package entities;

import service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BigTable extends Table {

    List<Order> orders = new ArrayList<>();

    public BigTable(String name) {
        super("Bàn lớn: " + name);
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public boolean isBusy() {
        return !orders.isEmpty();
    }

    @Override
    public void addOrder() {
        System.out.println("Order " + this.getTableName());
        System.out.println("Số lượng người đặt");
        Scanner sc = new Scanner(System.in);
        int total = sc.nextInt();
        for (int i = 0; i < total; i++) {
            this.orders.add(OrderService.newOrder());
        }
    }

    @Override
    public void issueOrder() {
        orders.removeAll(orders);
    }

    @Override
    public void printOrders() {
        int totalPrice = 0;
        System.out.println("Thông tin hóa đơn");
        for(Order xOrders : orders) {
            switch (xOrders.getType()) {
                case BIG:
                    System.out.print("Tô lớn: ");
                    break;
                case SMALL:
                    System.out.print("Tô nhỏ: ");
                    break;
                case SPECIAL:
                    System.out.print("Tô đặc biệt: ");
                    break;
            }
            for(MeatType meatType : xOrders.meatTypes) {
                System.out.print(meatType.getName()+"\t|");
            }
            System.out.println("");
            if(xOrders.isTopping()) {
                System.out.print("Toppings: ");
                for (Topping topping : xOrders.toppings) {
                    System.out.print(topping.getName() + "\t|");
                }
                System.out.println("");
            }

            totalPrice += xOrders.getPrice();
        }
        System.out.println("");
        System.out.println("Tổng tiền: "+ totalPrice);
    }
}
