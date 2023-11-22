package telran.view.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.view.*;
record Point(int x, int y) {

}
class InputOutputTest {
    inputOutput io = new StandartInputOutput();
    @Test
    @Disabled
    void readPointTest() {
        Point point = io.readObject("enter coordinates of point; usage <x>#<y>",
                "Wrong coordinates", str -> {
                    String [] xy = str.split("#");
                    if (xy.length != 2) {
                        throw new RuntimeException("incorrect format of input");
                    }
                    int x = Integer.parseInt(xy[0]);
                    int y = Integer.parseInt(xy[1]);
                    return new Point(x, y);
                });
        io.writeLine(point.x() + point.y() );
    }
    @Test
    @Disabled
    void readObjectInteger() {
        Integer number =
                io.readObject("Enter number in range [100-200]",
                        "Wrong number", str -> {
                            int num = Integer.parseInt(str);
                            if(num < 100 || num > 200) {
                                throw new RuntimeException("must be in the range [100-200]");
                            }
                            return num;
                        });
        io.writeLine(number / 2);
    }
    @Test
    void readEmailTest() {
        String email = io.readEmail("Enter email address",
                "Wrong email");
        io.writeLine(email);

    }

}