package telran.view.test;

import telran.view.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static  telran.view.Item.*;


public class DatesMenu {
    public static Item getMenu(){
        Menu menu = new Menu(getItems(), "Dates operations");
        return menu;
    }
    private static Item[] getItems(){
        Item[] items = {
                of("Date after given days", io -> dateAfter(io)),
                of("Date before given days", io -> dateBefore(io)),
                of("Number days between two days", io-> daysBetweenDays(io)),
                exit()
        };
        return items;
    }

    private static void daysBetweenDays(inputOutput io) {
        LocalDate[] days = getDate(io);
        LocalDate[] days2 = getDate(io);
        io.writeLine(days2[0].until(days[0], ChronoUnit.DAYS));
    }

    private static void dateBefore(inputOutput io) {
        LocalDate[] days = getDate(io);
        int num = getInt(io);
        io.writeLine(days[0].minusDays(num));
    }

    private static void dateAfter(inputOutput io) {
        //TODO
        LocalDate[] days = getDate(io);
        int num = getInt(io);
        io.writeLine(days[0].plusDays(num));
    }

    private static int getInt(inputOutput io) {
        int number = io.readInt("Enter number", "Wrong number");
        return number;
    }

    private static LocalDate[] getDate(inputOutput io) {
        LocalDate date1 = io.readDate("Enter date", "Wrong date");
        return new LocalDate[]{date1};

    }
}
