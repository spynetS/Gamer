package com.game.engine.rendering;

import com.game.engine.msc.Debug;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class SpriteRenderer extends Renderer{

    @Setter
    @Getter
    private LinkedList<Sprite> sprites = new LinkedList<>();

    @Getter
    @Setter
    private float timeOnEachSprite = 100;

    private int spriteIndex = 0;
    private float timeOnLastSprite = 0;

    public Sprite getCurrentSprite(){


        return sprites.get(spriteIndex);
    }
    public void addSprite(Sprite sprite){
        sprites.add(sprite);
    }

    @Override
    public void updateMillisecond() {
        super.updateMillisecond();
        Debug.log("mili");
        if(timeOnLastSprite > timeOnEachSprite) {
            if(spriteIndex+1 == sprites.size()) spriteIndex = 0;
            else spriteIndex ++;

            timeOnLastSprite = -1;
        }
        timeOnLastSprite ++;
    }

    @Override
    public void render(Graphics2D g) {
        if(getCurrentSprite()!=null)
            g.drawImage(getCurrentSprite().getImage(), (int) getPos().getX(), (int) getPos().getY(), (int) transform.getGlobalScale().getX(), (int) transform.getGlobalScale().getY(), null);
        g.drawRect((int) getPos().getX(), (int) getPos().getY(), (int) transform.getGlobalScale().getX(), (int) transform.getGlobalScale().getY());
    }
}
