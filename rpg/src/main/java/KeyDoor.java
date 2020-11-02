import java.util.Scanner;

public class KeyDoor extends Door{

    private String whisper1;
    private String whisper2;
    private String whisper3;

    public KeyDoor(String description, int leadsTo, String whisper1, String whisper2, String whisper3) {
        super(description, leadsTo);
        this.whisper1 = whisper1;
        this.whisper2 = whisper2;
        this.whisper3 = whisper3;
    }

    @Override
    public void interact(Player player) {
        System.out.println(whisper1);
        System.out.println("(0)Give key.\n(1)Leave");
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();

        if (input==0) {
            if (player.getInventory().isEmpty()) {
                System.out.println(whisper3);
            }
            for (int i=0; i < player.getInventory().size(); i++) {
                if(player.getInventory().get(i).inspect().equals("Key")){
                    System.out.println(whisper2);
                    player.setCurrentRoom(getLeadsTo());
                } else {
                    System.out.println(whisper3);
                }
            }
        }
    }
}