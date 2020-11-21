package entities;

import java.util.List;

public class Pho {

    public enum Type {
        SMALL, BIG, SPECIAL
    }

    private List<MeatType> meatTypes;

    private List<Topping> toppings;

}
