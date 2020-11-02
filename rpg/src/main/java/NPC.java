
abstract public class NPC extends Inspectable implements Interactable {

    private String description;
    private String talk1;
    private String talk2;
    private boolean isPresent;

    public String getTalk1() {
        return talk1;
    }

    public void setTalk1(String talk1) {
        this.talk1 = talk1;
    }

    public String getTalk2() {
        return talk2;
    }

    public void setTalk2(String talk2) {
        this.talk2 = talk2;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    public NPC () {
        this.talk1 = "empty NPC";
        this.talk2 = "empty NPC";
        this.isPresent = true;
    }

    public NPC(String description, String talk1, String talk2, boolean isPresent) {
        super(description);
        this.talk1=talk1;
        this.talk2=talk2;
        this.isPresent = isPresent;
    }
}
