package cardGame.view;

import java.util.EnumMap;

import java.io.File;
import java.io.IOException;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/**
 * For each card back, this class loads a texture. It does so statically to 
 * prevent duplicate loading of large images. It also does it eagerly 
 * (at startup) to prevent loading times from slowing the program down
 * at runtime.
 */
public class CardBackTextures {

    private static EnumMap<CardBack, BufferedImage> textures;
    
    /** 
     * This block initalizes the textures on launch. It is executed once when
     * the class is loaded into the JVM (when the program is started) meaning
     * that this process won't take time during execution.
     */
    static {
        textures = new EnumMap<CardBack, BufferedImage>(CardBack.class);
        for(CardBack back : CardBack.values()) {
            BufferedImage texture = null;
            String fileName = "target/classes/textures/" + back + ".png";
            try {
                File imgFile = new File(fileName);
                texture = ImageIO.read(imgFile);
            } catch (IOException ioe) {
                System.err.println("Could not load " + fileName); 
            }
            textures.put(back, texture);
        }
    }
    
    /**
     * Find a texture for a card back.
     */
    public static BufferedImage getTexture(CardBack back) {
        return textures.get(back);
    }

}
