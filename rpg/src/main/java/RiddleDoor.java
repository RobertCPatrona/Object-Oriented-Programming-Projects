import java.util.Scanner;

public class RiddleDoor extends Door{
    private String whisper1;
    private String whisper2;
    private String whisper3;

    public RiddleDoor(String description, int leadsTo, String whisper1, String whisper2, String whisper3) {
        super(description, leadsTo);
        this.whisper1 = whisper1;
        this.whisper2 = whisper2;
        this.whisper3 = whisper3;
    }
    @Override
    public void interact(Player player) {
            System.out.println(whisper1);
            Scanner scan = new Scanner(System.in);
            int input = scan.nextInt();

            if (input == 3) {
                player.setCurrentRoom(getLeadsTo());
                System.out.println(whisper2);
            } else {
                System.out.println(whisper3);
            }
    }
}

