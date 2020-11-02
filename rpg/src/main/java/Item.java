import java.io.Serializable;

public class Item extends Inspectable implements Serializable {

    private int price;
    private static final long serialVersionUID = 45L;

    public Item (String description, int price) {
        super(description);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
