package service;

import entities.MeatType;
import entities.Order;
import entities.Topping;

import java.util.Scanner;

public class OrderService {

    public static Scanner sc = new Scanner(System.in);

    public OrderService() { }
    public static Topping chooseTopping() {
        System.out.println("Chọn topping:\n" +
                "|1. Quẩy\t|2. Hột gà\t|3. Bánh phở thêm\t|4. Tiết\t|5. Tiết hột gà\t|6. Thịt thêm\t|0. Tiếp tục|");
        int choice = sc.nextInt();
         switch (choice) {
                case 1:
                    return Topping.Quay;
                case 2:
                    return Topping.HotGa;
                case 3:
                    return Topping.BanhPho;
                case 4:
                    return Topping.Tiet;
                case 5:
                    return Topping.TietHotGa;
                case 6:
                    return Topping.Thit;
                default:
                    return null;
        }
    }
    public static MeatType chooseMeatType() {
        System.out.println("Chọn món thịt: ");
        System.out.println("|1. Tái\t|2. Nạm\t|3. Gầu\t|4. Gân\t|5. Bò Vò Viên\t|0. Tiếp tục|");
        int choice2 = sc.nextInt();
        switch (choice2) {
            case 1:
                return MeatType.Tai;
            case 2:
                return MeatType.Nam;
            case 3:
                return MeatType.Gau;
            case 4:
                return MeatType.Gan;
            case 5:
                return MeatType.BoVoVien;
            default:
                return null;
        }
    }

    public static Order newOrder() {
        Order order = new Order();
        System.out.println("Chọn loại tô");
        System.out.println("|1. Nhỏ\t|2. To\t|3. Đặc biêt|");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                order.setType(Order.DishType.SMALL);
                break;
            case 2:
                order.setType(Order.DishType.BIG);
                break;
            case 3:
                order.setType(Order.DishType.SPECIAL);
                break;
        }

        int maximumMeatType = choice + 1;

        System.out.println("Chọn số lượng món thịt");
        int quantity;
        do {
            quantity = sc.nextInt();
            if (quantity < 1 || quantity > maximumMeatType) System.out.println("Vui lòng chọn đúng số lượng món thịt");
            else break;
        } while (true);

        int i = 0;
        while (i < quantity) {
            MeatType meatType = chooseMeatType();
            if(meatType == null) break;
            order.addMeat(meatType);
            i++;
        }
        do {
            Topping topping = chooseTopping();
            if (topping == null) break;
            order.addTopping(topping);
        } while (true);

//        System.out.println("Order thành công, số tiền : " + order.getPrice());
        return order;


    }

}
