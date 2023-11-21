package telran.view.test;
import telran.view.*;
import static telran.view.Item.*;

public class AricthmeticSimpleCalculatorAppl {
    public static void main(String[] args) {
        inputOutput io = new StandartInputOutput();
        Item [] items = getItems();
        Menu menu = new Menu (items, "Simple Calculator");
        menu.perform(io);
    }

    private static Item[] getItems() {
        Item[] items = {
                of("Add numbers", io -> addNumbers(io)), 
                of("Subtract numbers", io->subtractNUmbers(io)),
                of("Multiply numbers", io-> multiplyNumbers(io)),
                of("Devide numbers", io->devideNumbers(io)),
                exit()
        };
        return items;
    }

    private static void devideNumbers(inputOutput io) {
        double[] numbers = getNumbers(io);
        io.writeLine(numbers[0] / numbers[1]);
    }

    private static double[] getNumbers(inputOutput io) {
        double number1 = io.readDouble("Enter first number", "Wrong number");
        double number2 = io.readDouble("Enter second number", "Wrong number");
        return new double[]{number1, number2};
    }

    private static void multiplyNumbers(inputOutput io) {
        double[] numbers = getNumbers(io);
        io.writeLine(numbers[0] * numbers[1]);
    }

    private static void subtractNUmbers(inputOutput io) {
        double[] numbers = getNumbers(io);
        io.writeLine(numbers[0] + numbers[1]);
        
    }

    private static void addNumbers(inputOutput io) {
        double[] numbers = getNumbers(io);
        io.writeLine(numbers[0] + numbers[1]);

        
    }
}
