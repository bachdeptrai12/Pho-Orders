package service;

public class Employee {
    public String ID;
    public String name;

    public Employee(String ID, String name) {
        this.name = name;
        this.ID = ID;
    }
    public String getName() {
        return name;
    }

}
