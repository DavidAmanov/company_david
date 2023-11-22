package telran.view;

public class Menu implements Item{
    private static final String SYMBOL = "*";
    private static final int N_SYMBOL = 30;
    private Item [] items;
    private String name;

    public Menu(Item[] items, String name) {
        this.items = items;
        this.name = name;
    }

    @Override
    public String displayName() {
        // TODO return name
        return name;
    }

    @Override
    public void perform(inputOutput io) {
        //TODO
       Item item = null;
       do {
           displayTitle(io);
           displayItems(io);
           int nItem = io.readInt("select item", "wrong item number", 1, items.length);
           item = items[nItem - 1];
           try{
               item.perform(io);
           } catch (Exception e){
               io.writeLine(e.getMessage());
           }
       } while(!item.isExit());

    }
    private void displayTitle(inputOutput io){
        io.writeLine(SYMBOL.repeat(N_SYMBOL));
        io.writeLine(String.format("%s%s%s", SYMBOL, " ".repeat(30/4), name));
    }
    private void displayItems(inputOutput io){
        for(int i =0; i< items.length; i++){
            io.writeLine(String.format("%d. %s", i+1, items[i].displayName()));
        }
    }
    @Override
    public boolean isExit() {
        //TODO return false
        return false;
    }
}
