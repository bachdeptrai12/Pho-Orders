package entities;

public class Topping {
    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Topping(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public static Topping Quay = new Topping("Quẩy", 5e3);
    public static Topping HotGa = new Topping("Hột gà", 5e3);
    public static Topping BanhPho = new Topping("Bánh phở", 1e4);
    public static Topping Tiet = new Topping("Tiết", 1e4);
    public static Topping TietHotGa = new Topping("Tiết hột gà", 15e3);
    public static Topping Thit = new Topping("Thịt thêm", 25e3);

}
