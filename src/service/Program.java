package service;

import entities.BigTable;
import entities.Order;
import entities.SmallTable;
import entities.Table;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Program {
    List<SmallTable> smallTables;
    List<BigTable> bigTables;
    List<Order> orders;
    List<Order> totalBill;

    public Program() {
        this.smallTables = new ArrayList<>();
        this.bigTables = new ArrayList<>();
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
                totalBill.add(table.getOrders());
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
                totalBill.add(smtable.getOrder());
                smtable.issueOrder();
                flag = 1;
            }
        }
        if(flag == 0) System.out.println("Bàn chưa có khách.");
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Program program = new Program();
        program.generateTable();
//        program.getCurrentStatistic();
        while (true) {
            System.out.println("--------------------------");
            System.out.println("1. Thống kê trạng thái bàn");
            System.out.println("2. Tạo hóa đơn mới");
            System.out.println("3. Thanh toán hóa đơn theo mã bàn.");
            System.out.println("0. Thoát");
            System.out.print("          Lựa chọn (0 - 3): ");
            int choice;
            choice = sc.nextInt();
            sc.nextLine();
            if (choice == 0) break;
            switch (choice) {
                case 1:
                    program.getCurrentStatistic();
                    break;
                case 2:
                    int choice3;
                    System.out.println("---------------");
                    System.out.println("1. Khảch đi 1 mình.");
                    System.out.println("2. Khách đi theo nhóm");
                    System.out.println("0. Thoát.");
                    System.out.print("          Lựa chọn (0 - 2): ");
                    choice3 = sc.nextInt();
                    sc.nextLine();
                    if (choice3 == 0) break;
                    switch (choice3) {
                        case 1:
                            int flag1 = 0;
                            for (int i = 0; i < program.smallTables.size(); i++)
                                if (!program.smallTables.get(i).isBusy()) {
                                    program.createOrder(program.smallTables.get(i));
                                    flag1 = 1;
                                    break;
                                }
                            if (flag1 == 0)
                                System.out.println("Hiện đã hết bàn nhỏ.");
                            else System.out.println("Tạo hóa đơn thành công.");
                            break;
                        case 2:
                            int flag2 = 0;
                            for (int i = 0; i < program.bigTables.size(); i++)
                                if (!program.bigTables.get(i).isBusy()) {
                                    program.createOrder(program.bigTables.get(i));
                                    flag2 = 1;
                                    break;
                                }
                            if (flag2 == 0)
                                System.out.println("Hiện đã hết bàn lớn.");
                            else System.out.println("Tạo hóa đơn thành công.");
                            break;
                        default:
                            System.out.println("Lựa chọn không hợp lệ");
                            break;
                    }
                    break;
                case 3:
                    int choice2;
                    while(true) {
                        System.out.println("-----------------------");
                        System.out.println("1. Thanh toán bàn nhỏ.");
                        System.out.println("2. Thanh toán bàn lớn.");
                        System.out.println("0. Thoát");
                        System.out.print("        Lựa chọn (0 - 2): ");
                        choice2 = sc.nextInt();
                        sc.nextLine();
                        if (choice2 == 0) break;
                        switch (choice2) {
                            case 1:
                                program.payOrderSmallTable();
                                break;
                            case 2:
                                program.payOrderBigTable();
                                break;
                            default:
                                System.out.print("Lựa chọn không hợp lệ.");
                                break;
                        }
                    }
                    break;

                    default:
                    System.out.println("Lựa chọn không hợp lệ.");
                    break;
            }
        }
    }
}
