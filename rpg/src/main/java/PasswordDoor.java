import java.util.Scanner;

public class PasswordDoor extends Door{
    private String whisper1;
    private String whisper2;
    private String whisper3;
    private String password;

    public PasswordDoor(String description, int leadsTo, String whisper1, String whisper2, String whisper3) {
        super(description, leadsTo);
        this.whisper1 = whisper1;
        this.whisper2 = whisper2;
        this.whisper3 = whisper3;
        this.password = "potatoes";
    }
    @Override
    public void interact(Player player) {
            System.out.println(whisper1);
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();

            if (input.equals(password)) {
                System.out.println(whisper2);
                player.setCurrentRoom(getLeadsTo());
            } else {
                System.out.println(whisper3);
            }
    }

}