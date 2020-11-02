import java.util.Scanner;

public class DamageDoor extends Door implements Interactable {

    private String whisper1;
    private String whisper2;

    public DamageDoor(String description, int leadsTo, String whisper1, String whisper2) {
        super(description, leadsTo);
        this.whisper1 = whisper1;
        this.whisper2 = whisper2;
    }

    @Override
    public void interact(Player player) {
        System.out.println(whisper1);
        System.out.println("(0)Enter door.\n(1)Leave");
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();

        if (input==0) {
            player.setCurrentRoom(getLeadsTo());
            player.setHp(player.getHp() - 5);
            System.out.println("You received 5 damage.");
            System.out.println("Current health: " + player.getHp() + " hp.");
        }
        if (input == 1) {
            System.out.println(whisper2);
        }
    }
}
