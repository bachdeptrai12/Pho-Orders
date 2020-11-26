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
        System.out.println("Thông tin gọi món " + getBillID());
        switch (order.getType()) {
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
        for(MeatType meatType : order.getMeatTypes()) {
            System.out.print(meatType.getName()+" | ");
        }
        System.out.println("");
        if(order.isTopping()) {
            System.out.print("Toppings\n");
            for (Topping topping : order.toppings) {
                System.out.print(topping.getName() + " - " + topping.getPrice() + "\n");
            }
            System.out.println("");
        }

        System.out.println("Tổng tiền: " + order.getPrice());
    }

}
