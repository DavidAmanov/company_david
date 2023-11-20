package telran.view;

import java.time.LocalDate;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public interface inputOutput {
    String readString(String promt);
    void write(String str);
    default void writeLine(Object obj){

        write(obj+"\n");
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

        return res;
    }
    default int readInt(String promt, String errorPromt){
        return readObject(promt, errorPromt, Integer::parseInt);
    }
    default int readInt(String promt, String errorPromt, int min, int max){
        return readObject(String.format("%s [%d-%d]", promt, min, max), errorPromt, str->{
            int num = Integer.parseInt(str);
            if(num < min || num > max)
                throw new RuntimeException(String.format("Must be in the range [%d-%d]", min, max));
            return num;
        });
    }
    default long readLong (String promt, String errorPromt){
        return readObject(promt, errorPromt, Long::parseLong);
    }
    default long readInt(String promt, String errorPromt, long min, long max){
        return readObject(String.format("%s [%d-%d]", promt, min, max), errorPromt, str->{
            long num = Long.parseLong(str);
            if(num < min || num > max)
                throw new RuntimeException(String.format("Must be in the range [%d-%d]", min, max));
            return num;
        });
    }
    default LocalDate readDate(String promt, String errorPromt){
        // 2023.11.20
        return readObject(promt, errorPromt, LocalDate::parse);
    }
    default LocalDate readDate(String promt, String errorrPromt, LocalDate from, LocalDate to){
        return readObject(promt, errorrPromt, str->{
           LocalDate date = LocalDate.parse(str);
           if(date.compareTo(from)<0 || date.compareTo(to)>0)
               throw new RuntimeException(String.format("Must be in the range [%s-%s]", from, to));
           return date;
        });
    }
    default String readPredicate(String promt, String errorPromt, Predicate<String> predicate){
        //TODO return string mathcing the given predicate
        return readObject(promt,errorPromt, str -> {
            boolean isValid = false;
            isValid = predicate.test(str);
            if(!isValid) {
                throw new RuntimeException("Неверный формат");
            }
            return str;
        });
    }
    default String readOption(String promt, String errorPromt, Set<String> options){
        //TODO return string included in the given options
        return readObject(promt, errorPromt, str-> {
            if (!options.contains(str))
                throw new RuntimeException("Этого слова нет в сете");
            return str;
        });
    }
    default String readEmail(String promt, String errorPromt){
        //TODO return string with a email adress
        return readObject(promt, errorPromt, str->{
            if(!str.contains("@") && !str.contains(".com"))
                throw new RuntimeException("Это не email");
            return str;
        });
    }
    default double readDouble(String promt, String errorPromt){
        return readObject(promt, errorPromt, Double::parseDouble);
    }
    default double readDouble(String promt, String errorPromt, double min, double max){
        return readObject(String.format("%s [%d-%d]", promt, min, max), errorPromt, str->{
            double num = Double.parseDouble(str);
            if(num < min || num > max)
                throw new RuntimeException(String.format("Must be in the range [%d-%d]", min, max));
            return num;
        });
    }
}
