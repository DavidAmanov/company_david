package telran.view.test;
import telran.view.*;
public class NumbersDatesAppl {

    public static void main(String[] args) {
        inputOutput io = new StandartInputOutput();
        Menu menu = new Menu(new Item[] {NumbersMenu.getMenu(),
                DatesMenu.getMenu(),
                Item.exit()},
                "Numbers-Dates-Operations");
        menu.perform(io);
    }

}