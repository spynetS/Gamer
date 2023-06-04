package com.game.engine.rendering;

import com.game.engine.msc.Debug;
import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

public class Sprite {

    @Setter
    @Getter
    private String src = "";

    @Getter
    @Setter
    private BufferedImage image = null;


    public Sprite(String src) {
        super();
        this.src = src;
        loadSprite(src);
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
