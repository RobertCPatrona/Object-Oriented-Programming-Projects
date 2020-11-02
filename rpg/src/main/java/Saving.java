import java.io.*;
import java.io.File;
import java.io.FilenameFilter;

public class Saving implements FilenameFilter {

    private Execute execute;
    private String defaultSave = "./src/main/java/Savegames/quicksave.ser";
    private String defaultLoad = "./src/main/java/Savegames/quicksave.ser";
    private String str;
    private File f;
    private String[] paths;

    public Saving (Execute execute) {
        this.execute = execute;
    }

    public void quickSave () {
        save(defaultSave);
    }

    public void save (String savePath) {

        try {
            FileOutputStream fileOut = new FileOutputStream(savePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(execute.getPlayer());
            out.writeObject(execute.getWorld());
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void quickLoad() {
        load(defaultLoad);
    }

    public void load (String loadPath) {
        try {
            FileInputStream fileIn = new FileInputStream(loadPath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Player newPlayer = (Player) in.readObject();
            World newWorld = (World) in.readObject();
            this.execute.setPlayer(newPlayer);
            this.execute.setWorld(newWorld);
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Player not found.");
            c.printStackTrace();
            return;
        }
    }

    public Saving(String ext) {
        str = "."+ext;
    }

    public void listFiles() {

        f = new File("./src/main/java/Savegames");

        try {
            FilenameFilter filter = new Saving("ser");
            paths = f.list(filter);
            int i = 0;
            for(String path:paths) {
                System.out.println("(" + i + ") " + path);
                i++;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String[] getPaths() {
        return paths;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(str);
    }
}
