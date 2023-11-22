package telran.view.test;

import telran.view.*;
import static telran.view.Item.*;
public class NumbersMenu {
    public static Item getMenu(){
        Menu menu = new Menu(getItems(), "Arithmetic operations");
        return menu;
    }

    private static Item[] getItems() {
        return AricthmeticSimpleCalculatorAppl.getItems();
    }
}
