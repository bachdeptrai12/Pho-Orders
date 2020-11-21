package entities;

public interface iTable {
    public boolean isBusy();

    public void addOrder();

    public void issueOrder();

    public void printOrders();
}
