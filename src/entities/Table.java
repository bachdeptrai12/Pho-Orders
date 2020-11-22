package entities;

import service.Employee;

import java.util.Date;
import java.util.List;

public abstract class Table implements iTable {
    String tableName;
    Employee cashier;
    Employee waiter;

    public Table(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public Employee getCashier() {
        return cashier;
    }

    public void setCashier(Employee cashier) {
        this.cashier = cashier;
    }

    public Employee getWaiter() {
        return waiter;
    }

    public void setWaiter(Employee waiter) {
        this.waiter = waiter;
    }
}
