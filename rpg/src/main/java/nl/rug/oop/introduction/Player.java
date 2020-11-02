import java.io.Serializable;
import java.util.ArrayList;


public class Player implements Inventory, Serializable {

    private int currentRoom;
    private int money;
    private ArrayList<Item> inventory;
    private Room room;
    private int hp;
    private static final long serialVersionUID = 42L;

    public Player (int currentRoom, int money, ArrayList<Item> inventory, Room room, int hp) {
        this.currentRoom = currentRoom;
        this.money = money;
        this.inventory = inventory;
        this.room = room;
        this.hp = hp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getCurrentRoom() {
        return currentRoom;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setCurrentRoom(int currentRoom) {
        this.currentRoom = currentRoom;
    }

    @Override
    public void checkInventory(Player player) {
        System.out.println("Health: " + player.getHp() + " hp.");
        if (player.getInventory().isEmpty()) {
            System.out.println("Inventory is empty.");
        }
        for (int i = 0; i < player.getInventory().size(); i++) {
            System.out.println(player.getInventory().get(i).inspect());
        }
        System.out.println("You have: " + player.getMoney() + " ducats.");
    }
}
