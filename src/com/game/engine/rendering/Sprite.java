package com.game.engine.rendering;

import com.game.engine.msc.Debug;
import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.LinkedList;

public class Sprite {

    @Setter @Getter private String src = "";

    @Getter @Setter private BufferedImage image = null;
    

    public Sprite(String src) {
        super();
        this.src = src;
        loadSprite(src);
    }

    public Sprite() {
    }

    public Sprite(BufferedImage image) {
        this.image = image;
    }

    public static LinkedList<Sprite> getSprites(String src){
        LinkedList<Sprite> sprites = new LinkedList<>();
        File folder = new File(src);

        // Verify that the folder exists and is a directory
        if (folder.exists() && folder.isDirectory()) {
            // Get a list of all files in the folder
            File[] files = folder.listFiles();

            // Iterate through the files and print their names
            for (File file : files) {
                if (file.isFile()) {
                    sprites.add(new Sprite("/tiles/"+file.getName()));
                }
            }
        } else {
            System.out.println("Invalid folder path");
        }
        return sprites;
    }

    public static LinkedList<Sprite> getSprites(String src, LinkedList<Rectangle> boxes){

        BufferedImage sprite = null;
        try {
            InputStream in = new Sprite().getClass().getResourceAsStream(src);
            sprite = ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
        }


        LinkedList<Sprite> animation = new LinkedList<>();
        for(Rectangle r : boxes){
            BufferedImage animationSprite = (sprite.getSubimage((int) (r.getX()), (int) (r.getY()), (int) r.getWidth(), (int) r.getHeight()));

            animation.add(new Sprite(animationSprite));
        }
        return animation;
    }

    public void loadSprite(String src){
        Debug.startCount();
        BufferedImage sprite = null;
        try {
            InputStream in = this.getClass().getResourceAsStream(src);
            sprite = ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.image = sprite;
    }



}
