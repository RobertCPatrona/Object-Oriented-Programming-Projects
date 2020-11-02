import java.io.Serializable;

abstract public class Inspectable implements Serializable {

    private String description;

    public String getDescription() {
        return description;
    }

    public Inspectable(String description) {
        this.description = description;

    }
    public Inspectable() {
        this.description = "Empty inspectable";
    }

    public String inspect() {
        return this.description;
    }

}
