package entities;

import service.OrderService;

public class SmallTable extends Table {
    private Order order;

    public SmallTable(String name) {
        super("Bàn nhỏ: " + name);
    }

    @Override
    public boolean isBusy() {
        return order != null;
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public void addOrder() {
        System.out.println("Order " + this.getTableName());
        this.order = OrderService.newOrder();
    }

    @Override
    public void issueOrder() {
        order = null;
    }

    @Override
    public void printOrders() {
        System.out.println("Thông tin gọi món:  ");
        switch (order.getType()) {
            case BIG:
                System.out.print("Tô lớn:   ");
                break;
            case SMALL:
                System.out.print("Tô nhỏ:   ");
                break;
            case SPECIAL:
                System.out.print("Tô đặc biệt:  ");
                break;
        }
        for(MeatType meatType : order.getMeatTypes()) {
            System.out.print(meatType.getName()+"\t|");
        }
        System.out.println("");
        if(order.isTopping()) {
            System.out.print("Toppings: ");
            for (Topping topping : order.toppings) {
                System.out.print(topping.getName() + "\t|");
            }
            System.out.println("");
        }

        System.out.println("Tổng tiền: " + order.getPrice());
    }

}
