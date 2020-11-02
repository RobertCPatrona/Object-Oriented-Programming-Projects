import java.util.HashSet;

//This class defines the Memory type used in main, which enables the program to remember an inputted line.
public class Memory {

    private HashSet<String> memory;

    public Memory() {
        memory = new HashSet<>();
    }

    public void remember(String said) {
        if (!memory.contains(said)) {
            memory.add(said);
        }
    }

    public boolean canRemember(String said) {
        return memory.contains(said);
    }

}