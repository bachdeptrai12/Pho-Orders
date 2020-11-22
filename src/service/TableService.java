package service;

import entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TableService {
    public List<SmallTable> smallTables;
    public List<BigTable> bigTables;
    public List<Order> orderList;

    public TableService() {
        this.smallTables = new ArrayList<>();
        this.bigTables = new ArrayList<>();
        this.orderList = new ArrayList<>();
    }


    public void generateTable() {
        for (int i = 0; i < 3; i++) smallTables.add(new SmallTable("STT " + i));
        for (int i = 0; i < 3; i++) bigTables.add(new BigTable("STT " + (i + 3)));
    }


    public void getCurrentBusyTable() {
            int flag = 0;
        for(SmallTable smallTable : smallTables) {
            if(smallTable.isBusy()) {
                System.out.println(smallTable.getTableName() + " đang có khách");
                flag = 1;
            }
        }
        for(BigTable table : bigTables) {
            if(table.isBusy()) {
                System.out.println(table.getTableName() + " đang có khách");
                flag = 1;
            }
        }
        if (flag == 0)
            System.out.println("Tất cả các bàn đều đang trống");
    }
    public void getCurrentFreeTable() {
        int flag = 0;
        for(SmallTable smallTable : smallTables) {
            if(!smallTable.isBusy()) {
                System.out.println(smallTable.getTableName() + " đang trống");
                flag = 1;
            }
        }
        for(BigTable table : bigTables) {
            if(!table.isBusy()) {
                System.out.println(table.getTableName() + " đang trống");
                flag = 1;
            }
        }
        if(flag == 0)
            System.out.println("Tất các các bàn đang có khách.");
    }
    public void getCurrentStatistic() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("-------------------------");
            System.out.println("1. Các bàn đang có khách.");
            System.out.println("2. Các bàn đang trống.");
            System.out.println("0. Thoát");
            System.out.print("        Lựa chọn(0 - 2): ");
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 0) break;
            switch (choice) {
                case 1:
                    getCurrentBusyTable();
                    break;
                case 2:
                    getCurrentFreeTable();
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }

    }

    public void createOrder(BigTable table) {
        if (table.isBusy()) {
            System.out.println("Bàn này đang có khách.");
        }
        table.addOrder();
    }

    public void createOrder(SmallTable table) {
        if (table.isBusy())
            System.out.println("Bàn này đang có khách.");
        table.addOrder();
    }

    public void payOrderBigTable() {
        Scanner sc = new Scanner(System.in);
        int xCode;
        System.out.print("Nhập mã bàn lớn: ");
        do {
            xCode = sc.nextInt();
            if(xCode < 3 || xCode > 5)
                System.out.print("Mã bàn không hợp lệ. Nhập lại: ");
        } while (xCode < 3 || xCode > 5);

        String tableName = "Bàn lớn: STT " + String.valueOf(xCode);
        int flag = 0;
        for(BigTable table : bigTables) {
            if(table.isBusy() && tableName.equals(table.getTableName())) {
                table.printOrders();
                for(Order xOrder : table.getOrders())
                    orderList.add(xOrder);
                table.issueOrder();
                flag = 1;
            }
        }
        if(flag == 0)
            System.out.println("Bàn chưa có khách");
    }

    public void payOrderSmallTable() {
        Scanner sc = new Scanner(System.in);
        int xCode;
        System.out.print("Nhập mã bàn nhỏ: ");
        do {
            xCode = sc.nextInt();
            if(xCode < 0 || xCode > 3)
                System.out.print("Mã bàn không hợp lệ. Nhập lại: ");
        } while (xCode < 0 || xCode > 3);
        String tableName = "Bàn nhỏ: STT " + String.valueOf(xCode);
        int flag = 0;
        for(SmallTable smtable : smallTables) {
            if(smtable.isBusy() && tableName.equals(smtable.getTableName())) {
                smtable.printOrders();
                orderList.add(smtable.getOrder());
                smtable.issueOrder();
                flag = 1;
            }
        }
        if(flag == 0) System.out.println("Bàn chưa có khách.");
    }
    public void printOrderList() {
        int i = 1;
        for(Order order : orderList) {
            System.out.println("Thông tin hóa đơn "+(i++));
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
            System.out.print("Toppings: ");
            for(Topping toppings : order.getToppings()) {
                System.out.print(toppings.getName()+"\t|");
            }
            System.out.println("");
        }
    }
}
