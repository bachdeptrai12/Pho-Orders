package entities;

public class MeatType {
    String name;

    public MeatType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static MeatType Tai = new MeatType("Tái");
    public static MeatType Nam = new MeatType("Nạm");
    public static MeatType Gau = new MeatType("Gầu");
    public static MeatType Gan = new MeatType("Gân");
    public static MeatType BoVoVien = new MeatType("Bò Vò Viên");

}
