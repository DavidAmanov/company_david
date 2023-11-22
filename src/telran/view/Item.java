package telran.view;

import java.util.function.Consumer;

public interface Item {
    String displayName();
    void perform(inputOutput io);
    boolean isExit();
    static Item of(String name, Consumer<inputOutput> action, boolean isExit){
        return new Item() {
            @Override
            public String displayName() {
                return name;
            }

            @Override
            public void perform(inputOutput io) {
                action.accept(io);

            }

            @Override
            public boolean isExit() {
                return isExit;
            }
        };
    }
    static Item of(String name, Consumer<inputOutput> action){
        return of(name, action, false);
    }
    static Item exit(){
        return of("Exit", io->{}, true);
    }
}
