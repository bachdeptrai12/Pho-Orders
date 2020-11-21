package entities;

import java.util.Date;
import java.util.List;

public abstract class Table implements iTable {
    String tableName;

    public Table(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
}
