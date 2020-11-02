import java.io.Serializable;
import java.util.ArrayList;

public class Room extends Inspectable implements Serializable {

    private ArrayList<Door> doors;
    private NPC npc;
    private static final long serialVersionUID = 43L;

    public Room() {
        this.doors = new ArrayList<Door>();
        this.npc = null;
    }

    public Room (ArrayList<Door> doors, String description, NPC npc) {
        super(description);
        this.doors = doors;
        this.npc = npc;
    }

    public ArrayList<Door> getDoors() {
        return doors;
    }

    public void setNpc(NPC npc) {
        this.npc = npc;
    }

    public NPC getNpc() {
        return npc;
    }
}
