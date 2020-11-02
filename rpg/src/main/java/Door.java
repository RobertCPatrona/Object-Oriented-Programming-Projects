import java.io.Serializable;

public class Door extends Inspectable implements Interactable, Serializable {

    private int leadsTo;
    private static final long serialVersionUID = 44L;

    public Door (String description, int leadsTo) {
        super(description);
        this.leadsTo = leadsTo;
    }

    public int getLeadsTo() {
        return leadsTo;
    }

    @Override
    public void interact(Player player) {
        player.setCurrentRoom(leadsTo);
    }


}
