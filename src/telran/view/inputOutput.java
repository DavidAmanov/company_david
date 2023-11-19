package telran.view;

import java.util.function.Function;

public interface inputOutput {
    String readString(String promt);
    void write(String str);
    default void writeLine(String str){
        write(str+"\n");
    }
    default <T> T readObject(String promt, String errorPromt, Function<String, T> mapper){
        boolean running = false;
        T res = null;
        do{
            try
            {
                String str = readString(promt);
                res  = mapper.apply(str);
            }
            catch(Exception e)
            {
                running = true;
                writeLine(errorPromt + ": " + e.getMessage());
            }
        }while(running);

        return null;
    }
}
