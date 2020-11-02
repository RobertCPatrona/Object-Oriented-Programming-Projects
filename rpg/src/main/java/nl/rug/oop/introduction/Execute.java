import java.util.ArrayList;
import java.util.Scanner;

public class Execute {

    private World world = new World();

    private Scanner scan = new Scanner(System.in);

    private ArrayList<Item> inventory = new ArrayList<Item>();
    private Player player = new Player(0, 10, inventory, world.getRooms().get(0), 10);
    private Saving saveObj = new Saving(this);
    private boolean alive = true;

    public Execute () {
        System.out.println("Welcome traveler. To continue your journey, you must find a way to escape the castle.");
        while (player.getCurrentRoom() != 6) {

            if (player.getHp() <= 0) {
                System.out.println("You have died. Try again.");
                alive = false;
                break;
            }

            System.out.println( "What do you want to do?\n" +
                                "(0) Look around\n" +
                                "(1) Look for a way out\n" +
                                "(2) Look for company\n" +
                                "(3) Check inventory and health.\n" +
                                "(4) QuickSave\n" +
                                "(5) QuickLoad\n" +
                                "(6) Save\n" +
                                "(7) Load");
            int input = scan.nextInt();

            if (input < -1 || input > 7) {
                System.out.println("Wrong input.");
                continue;
            }
            if (input == 0) {
                System.out.println("You see: " + world.getRooms().get(player.getCurrentRoom()).inspect());
            }
            if (input == 1) {
                System.out.println("You look around for doors. You see: ");
                for (int i = 0; i < world.getRooms().get(player.getCurrentRoom()).getDoors().size(); i++) {
                    System.out.println("(" + i + ")" + world.getRooms().get(player.getCurrentRoom()).getDoors().get(i).inspect());
                }

                System.out.println("Which door do you take? (-1: stay here)");

                int input2 = scan.nextInt();

                if (input2 == -1) {
                    continue;
                }

                if (input2 > -1 && input2 < world.getRooms().get(player.getCurrentRoom()).getDoors().size()) {
                    world.getRooms().get(player.getCurrentRoom()).getDoors().get(input2).interact(player);
                    if (player.getCurrentRoom() != 5) {
                        player.setRoom(world.getRooms().get(player.getCurrentRoom()));
                    }
                }
            }
            if (input == 2) {
                System.out.println("You look if there's someone here.");

                if (world.getRooms().get(player.getCurrentRoom()).getNpc() == null) {
                    System.out.println("Nobody is in this room.");
                    continue;
                }

                if (player.getRoom().getNpc().isPresent() == false) {
                    System.out.println("Nobody is in this room.");
                    continue;
                }

                System.out.println("(0) " + world.getRooms().get(player.getCurrentRoom()).getNpc().inspect());
                System.out.println("Interact? (-1: do nothing)");

                int input3 = scan.nextInt();

                if (input3 == -1) {
                    continue;
                }

                if (input3 == 0) {
                    world.getRooms().get(player.getCurrentRoom()).getNpc().interact(player);
                }
            }
            if (input == 3) {
                player.checkInventory(player);
                continue;
            }
            if (input == 4) {
                this.saveObj.quickSave();
                System.out.println("You have quicksaved.");
            }
            if (input == 5) {
                this.saveObj.quickLoad();
                System.out.println("You have quickloaded.");
            }
            if (input == 6) {
                System.out.println("File name?");
                String name = scan.nextLine();
                String name2 = scan.nextLine();
                String address = "./Savegames/" + name2 + ".ser";
                this.saveObj.save(address);
                System.out.printf("Serialized data is saved in " + address + "\n");
            }
            if (input == 7) {
                System.out.println("Which file? (-1: none)");
                this.saveObj.listFiles();
                int whichFile = scan.nextInt();
                this.saveObj.load("./Savegames/" + saveObj.getPaths()[whichFile]);
                System.out.println("You have loaded.");
            }
        }
        if (this.alive) {
            System.out.println("Congratulations. You escaped the castle.\n");
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player newPlayer) {
        this.player = newPlayer;
    }

    public World getWorld() {
        return this.world;
    }

    public void setWorld(World newWorld) {
        this.world = newWorld;
    }
}
