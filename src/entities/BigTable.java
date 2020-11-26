package entities;

import service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BigTable extends Table {

    private List<Order> orders = new ArrayList<>();

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
        System.out.print("Số lượng người đặt ");
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
        System.out.println("Thông tin hóa đơn " + getBillID());
        for(Order xOrders : orders) {
            switch (xOrders.getType()) {
                case BIG:
                    System.out.print("Tô lớn - 45000\n");
                    break;
                case SMALL:
                    System.out.print("Tô nhỏ - 60000\n");
                    break;
                case SPECIAL:
                    System.out.print("Tô đặc biệt - 75000\n");
                    break;
            }
            for(MeatType meatType : xOrders.meatTypes) {
                System.out.print(meatType.getName()+" |");
            }
            System.out.println("");
            if(xOrders.isTopping()) {
                System.out.print("Toppings\n");
                for (Topping topping : xOrders.toppings) {
                    System.out.print(topping.getName() + " | " + topping.getPrice() + "\n");
                }
                System.out.println("");
            }

            totalPrice += xOrders.getPrice();
        }
        System.out.println("");
        System.out.println("Tổng tiền: "+ totalPrice);
    }

}
