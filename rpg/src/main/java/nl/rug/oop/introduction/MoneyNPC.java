import java.io.Serializable;
import java.util.Scanner;


public class MoneyNPC extends NPC implements Serializable {

    private Item item;
    private int reward;
    private boolean gaveObject=false;


    public MoneyNPC(String description, String talk1, String talk2, Item item, int reward, boolean isPresent) {
        super(description, talk1, talk2, isPresent);
        this.item = item;
        this.reward = reward;
    }

    @Override
    public void interact(Player player) {
        System.out.println(getTalk1());
        System.out.println("(0) Give item.\n(1) Leave");
        Scanner scan = new Scanner(System.in);

        int input = scan.nextInt();

        if (input == 0) {

            for (int i=0; i < player.getInventory().size(); i++) {
                if (player.getInventory().get(i).inspect().equals(item.inspect())) {
                    gaveObject = true;
                    player.getInventory().remove(i);
                    break;
                }
            }
        }

        if (gaveObject) {
            //player.getRoom().setNpc(null);
            System.out.println(getTalk2());
            player.setMoney(player.getMoney() + reward);
            this.setPresent(false);
        } else {
            System.out.println("Come back with the item.");
        }
    }

}
