import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Merchant extends NPC implements Serializable {

    private ArrayList<Item> inventoryNPC;
    private static final long serialVersionUID = 46L;

    public Merchant () {
        this.inventoryNPC = new ArrayList<Item>();
    }

    public Merchant (String description, ArrayList<Item> inventoryNPC, String talk1, String talk2, boolean isPresent) {
        super(description, talk1, talk2, isPresent);
        this.inventoryNPC = inventoryNPC;
    }

    @Override
    public void interact(Player player) {
        System.out.println(getTalk1());
        for (int i=0; i < inventoryNPC.size(); i++) {
            System.out.println("(" + i + ")" + inventoryNPC.get(i).inspect() + ". price = " + inventoryNPC.get(i).getPrice());
        }
        System.out.println("You have " + player.getMoney() + " ducats.");

        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();

        if (input > -1 && input < 3) {
            if (player.getMoney() >= inventoryNPC.get(input).getPrice()) {
                player.getInventory().add(inventoryNPC.get(input));
                player.setMoney(player.getMoney() - inventoryNPC.get(input).getPrice());
                System.out.println(getTalk2());
            } else {
                System.out.println("Not enough ducats.");
            }
        }
    }

}
