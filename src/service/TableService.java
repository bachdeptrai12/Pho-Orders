package service;

import entities.*;

import java.io.IOException;
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

    //Tạo bàn
    public void generateTable() {
        for (int i = 0; i < 30; i++) smallTables.add(new SmallTable("STT " + i));
        for (int i = 0; i < 20; i++) bigTables.add(new BigTable("STT " + (i + 30)));
    }

    //Kiểm tra các bàn đang có khách
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
    //Kiểm tra các bàn đang trống
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
    //Kiểm tra tổng tình trạng các bàn hiện tại
    public void getCurrentStatistic() throws IOException {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("-------------------------");
            System.out.println("1. Các bàn đang có khách.");
            System.out.println("2. Các bàn đang trống.");
            System.out.println("0. Thoát");
            System.out.print("        Lựa chọn(0 - 2): ");
            int choice = 0;
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Lựa chọn không hợp lệ.");
            }
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

    //Tạo hóa đơn cho bàn nhỏ và bàn lớn
    public void createOrder(BigTable table) {
        if (table.isBusy()) {
            System.out.println("Bàn này đang có khách.");
        }
        System.out.print("Nhập mã hóa đơn: ");
        table.setBillID(new Scanner(System.in).nextLine());
        table.addOrder();

    }
    public void createOrder(SmallTable table) {
        if (table.isBusy()) {
            System.out.println("Bàn này đang có khách.");
        }
        System.out.print("Nhập mã hóa đơn: ");
        table.setBillID(new Scanner(System.in).nextLine());
        table.addOrder();
    }


    //Thanh toán hóa đơn bàn lớn
    public void payOrderBigTable() {
        Scanner sc = new Scanner(System.in);
        int xCode;
        System.out.print("Nhập mã bàn lớn: ");
        do {
            xCode = sc.nextInt();
            if(xCode < 10 || xCode > 49)
                System.out.print("Mã bàn không hợp lệ. Nhập lại: ");
        } while (xCode < 10 || xCode > 49);

        String tableName = "Bàn lớn: STT " + String.valueOf(xCode);
        int flag = 0;
        for(BigTable table : bigTables) {
            if(table.isBusy() && tableName.equals(table.getTableName())) {

                table.printOrders();
                for(Order xOrder : table.getOrders()) {
                    xOrder.setBillID(table.getBillID());
                    orderList.add(xOrder);
                }
                table.issueOrder();
                flag = 1;
            }
        }
        if(flag == 0)
            System.out.println("Bàn chưa có khách");
        else {

        }
    }

    //Thanh toán hóa đơn bàn nhỏ
    public void payOrderSmallTable() {
        Scanner sc = new Scanner(System.in);
        int xCode;
        System.out.print("Nhập mã bàn nhỏ: ");
        do {
            xCode = sc.nextInt();
            if(xCode < 0 || xCode > 10)
                System.out.print("Mã bàn không hợp lệ. Nhập lại: ");
        } while (xCode < 0 || xCode > 10);
        String tableName = "Bàn nhỏ: STT " + String.valueOf(xCode);
        int flag = 0;
        for(SmallTable smtable : smallTables) {
            if(smtable.isBusy() && tableName.equals(smtable.getTableName())) {

                smtable.printOrders();
                Order xorder = smtable.getOrder();
                xorder.setBillID(smtable.getBillID());
                orderList.add(xorder);
                smtable.issueOrder();
                flag = 1;
            }
        }
        if(flag == 0) System.out.println("Bàn chưa có khách.");
    }

    //In toàn bộ hóa đơn đã xuất trong ngày
    public void printOrderList() {

        for(int i = 0; i < orderList.size(); i++) {
            if (i == 0 || (orderList.get(i).getBillID() != orderList.get(i-1).getBillID())) {
                System.out.println("Thông tin hóa đơn số " + (i + 1));
                System.out.println("Mã hóa đơn: " + orderList.get(i).getBillID());
            }
            System.out.println("");
            //Thông tin về tô trong hóa đơn
            switch (orderList.get(i).getType()) {
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

            //Thông tin về thịt trong hóa đơn
            for(MeatType meatType : orderList.get(i).getMeatTypes()) {
                System.out.print(meatType.getName()+" | ");
            }
            //Thông tin về top ping
            System.out.print("\nToppings\n");
            for(Topping toppings : orderList.get(i).getToppings()) {
                System.out.print(toppings.getName()+ "\t" +toppings.getPrice() + "\n");
            }

            System.out.println("\nTổng tiền: "+orderList.get(i).getPrice());
        }
    }
}
