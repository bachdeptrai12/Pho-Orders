package entities;

public abstract class Table implements iTable {
    private String billID;
    private String tableName;

    public Table(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getBillID() {
        return billID;
    }
}
