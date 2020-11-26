package entities;

public interface iTable {
    //Kiểm tra bàn trống hay không
    public boolean isBusy();

    //Tạo hóa đơn cho bàn
    public void addOrder();

    //Thanh toán hóa đơn
    public void issueOrder();

    //In hóa đơn
    public void printOrders();

}
