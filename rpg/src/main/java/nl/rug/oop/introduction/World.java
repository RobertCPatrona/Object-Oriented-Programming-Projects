import java.io.Serializable;
import java.util.ArrayList;

public class World implements Serializable {

    private ArrayList<Room> rooms = new ArrayList<Room>();

    public World () {

        ArrayList<Item> merchantInventory = new ArrayList<Item>();
        Item key = new Item("Key", 20);
        merchantInventory.add(key);
        Item lockpick = new Item("Lockpick", 5);
        merchantInventory.add(lockpick);
        Item wine = new Item("Wine", 5);
        merchantInventory.add(wine);
        String talk1 = "Hello Traveller. I have wares. What do you wish to buy?";
        String talk2 = "Thanks for the purchase. Be sure to come back.";
        Merchant merchant = new Merchant("A shady merchant with a long coat. He looks at you with curious eyes.", merchantInventory, talk1, talk2, true);

        String fireplaceDescription = "An old man is sitting on a chair by a fireplace. He does not pay much attention to your presence.";
        String talk1Fireplace = "I am thirsty. Bring me something to drink and I will give you the password to the eagle door.";
        String talk2Fireplace = "Thank you. The password for the Eagle door is 'potatoes'. I will go and enjoy my wine.\n The old man leaves.";
        MoneyNPC npcFireplace = new MoneyNPC(fireplaceDescription, talk1Fireplace, talk2Fireplace, wine, 0, true);

        String prisonDescription = "A man is locked in one of the prison cells.";
        String talk1Prison = "Quick! The guardian is not here. Free me. You will need a lockpick. I will pay you 20 ducats for your effort.";
        String talk2Prison = "Thank you for freeing me. Here is you reward.\n Received 20 ducats.\nThe prisoner left the room.";
        MoneyNPC npcPrison = new MoneyNPC(prisonDescription, talk1Prison, talk2Prison, lockpick, 20, true);


        String whisper1Password = "Tell me the password if you want to pass. If you don't know it, ask the old man.";
        String whisper2Password = "That is correct. You can pass.";
        String whisper3Password = "That is not correct.";

        String whisper1Riddle = "If you want to pass, you have to answer my riddle. On an island, there are 3 types of inhabitants.\n" +
                " (1) Knights always tell the truth,\n (2) Knaves never tell the truth,\n (3) Knormals sometimes tell the truth and sometimes lie\n" +
                "One inhabitant of the island says 'I'm no Knight'. What type of person is he?";
        String whisper2Riddle = "Correct, you can pass.";
        String whisper3Riddle = "Wrong answer.";

        String whisper1Key = "Greetings. If you wish to leave the castle, you need to unlock me with a golden key. The merchant has it, but it's not cheap.";
        String whisper2Key = "Very well. You may leave.";
        String whisper3Key = "Bring the key if you wish to leave.";

        String damageWhisper1 = "Stay away if you care about your safety.";
        String damageWhisper2 = "Good choice.";


        ArrayList<Door> doorsYellow = new ArrayList<Door>();
        doorsYellow.add(new Door("A square black door.", 1));
        doorsYellow.add(new Door("A tall white door.", 2));
        doorsYellow.add(new PasswordDoor("A large door with a black eagle.", 3, whisper1Password, whisper2Password, whisper3Password));
        doorsYellow.add(new RiddleDoor("A door with a face of a cat.", 4, whisper1Riddle, whisper2Riddle, whisper3Riddle));

        rooms.add(new Room(doorsYellow, "A large yellow room.", null));

        ArrayList<Door> doorsBoxes = new ArrayList<Door>();
        doorsBoxes.add(new Door("A square black door.", 0));

        rooms.add(new Room(doorsBoxes, "A small room with many boxes.", merchant));

        ArrayList<Door> doorsHallway = new ArrayList<Door>();
        doorsHallway.add(new Door("A tall white door.", 0));
        doorsHallway.add(new Door("A small door that leads into a tunnel", 1));
        doorsHallway.add(new KeyDoor("A steel door with a sign EXIT", 6, whisper1Key, whisper2Key, whisper3Key));

        rooms.add(new Room(doorsHallway, "A long corridor with paintings on the wall.", null));

        ArrayList<Door> doorsPrison = new ArrayList<Door>();
        doorsPrison.add(new Door("A large door with a black eagle.", 0));
        doorsPrison.add(new DamageDoor("A dark door with red markings.", 5, damageWhisper1, damageWhisper2));

        rooms.add(new Room(doorsPrison, "A dark and damp hallway with 3 cells. A dark door is located at the end of the hallway", npcPrison));

        ArrayList<Door> doorsFire = new ArrayList<Door>();
        doorsFire.add(new Door("A door with a face of a cat.", 0));

        rooms.add(new Room(doorsFire, "A cozy, well-lit room with a fireplace.", npcFireplace));

        ArrayList<Door> doorsDark = new ArrayList<Door>();
        doorsDark.add(new Door("A dark door with red markings.", 3));

        rooms.add(new Room(doorsDark, "A cold dark room. It's too dangerous to explore it.", null));

        rooms.add(new Room());
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

}
