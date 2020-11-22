package com.company;

import service.TableService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        TableService tableService = new TableService();
        tableService.generateTable();
        tableService.genarateEmp();

        while (true) {
            System.out.println("--------------------------");
            System.out.println("1. Thống kê trạng thái bàn.");
            System.out.println("2. Tạo hóa đơn mới.");
            System.out.println("3. Thanh toán hóa đơn theo mã bàn.");
            System.out.println("4. Hiển thị toàn bộ hóa đơn trong ngày.");
            System.out.println("0. Thoát");
            System.out.print("          Lựa chọn (0 - 3): ");
            int menuChoice;
            menuChoice = sc.nextInt();
            sc.nextLine();
            if (menuChoice == 0) break;
            switch (menuChoice) {
                case 1:
                    tableService.getCurrentStatistic();
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
                            for (int i = 0; i < tableService.smallTables.size(); i++)
                                if (!tableService.smallTables.get(i).isBusy()) {
                                    tableService.createOrder(tableService.smallTables.get(i));
                                    flag1 = 1;
                                    break;
                                }
                            if (flag1 == 0)
                                System.out.println("Hiện đã hết bàn nhỏ.");
                            else System.out.println("Tạo hóa đơn thành công.");
                            break;
                        case 2:
                            int flag2 = 0;
                            for (int i = 0; i < tableService.bigTables.size(); i++)
                                if (!tableService.bigTables.get(i).isBusy()) {
                                    tableService.createOrder(tableService.bigTables.get(i));
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
                        System.out.println("0. Thoát.");
                        System.out.print("        Lựa chọn (0 - 2): ");
                        choice2 = sc.nextInt();
                        sc.nextLine();
                        if (choice2 == 0) break;
                        switch (choice2) {
                            case 1:
                                tableService.payOrderSmallTable();
                                break;
                            case 2:
                                tableService.payOrderBigTable();
                                break;
                            default:
                                System.out.print("Lựa chọn không hợp lệ.");
                                break;
                        }
                    }
                    break;
                case 4:
                    tableService.printOrderList();
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
                    break;
            }
        }
    }
}
