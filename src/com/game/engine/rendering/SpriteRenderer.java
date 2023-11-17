package com.game.engine.rendering;

import com.game.engine.GameEngine;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class SpriteRenderer extends Renderer{

    @Setter @Getter private LinkedList<LinkedList<Sprite>> animations = new LinkedList<>();

    @Getter @Setter private boolean isInverted = false;

    /**
 * This chooses how long a sprite is displayed before next sprite is displayed
     */
    @Getter @Setter private double timeOnEachSprite = 100;

    private int spriteIndex = 0;
    private double timeOnLastSprite = 0;

    @Getter @Setter private int animationIndex = 0;


    public Sprite getCurrentSprite(){

        return animations.get(animationIndex).get(spriteIndex);
    }
    public void addAnimation(LinkedList<Sprite> sprites){
        animations.add(sprites);
    }
    public void addSprite(Sprite sprite){
        addSprite(sprite, animationIndex);
    }
    public void addSprite(Sprite sprite, int animationIndex){
        if(animations.size() == 0)animations.add(new LinkedList<>());
        animations.get(animationIndex).add(sprite);
    }
    public void onAnimationDone(){
    }
    @Override
    public void update() {
        super.update();
        LinkedList<Sprite> sprites = animations.get(animationIndex);
        if(timeOnLastSprite > timeOnEachSprite) {
            // if the sprite index is greater then the amount of sprites
            // the animation is done else we set next sprite in the animation
            if(spriteIndex+1 == sprites.size()) {
                spriteIndex = 0;
                onAnimationDone();
            }
            else spriteIndex ++;

            timeOnLastSprite = -1;
        }
        timeOnLastSprite += (GameEngine.deltaTime*1000);
    }

    @Override
    public void render(Graphics2D g) {
        Sprite currentSprite = getCurrentSprite();
        if(currentSprite!=null){
            AffineTransform backup = g.getTransform();
            AffineTransform trans = new AffineTransform();
            Vector2 pivot = new Vector2();

            trans.rotate( Math.toRadians(transform.getRotation()), transform.getPosition().getX() + pivot.getX(), transform.getPosition().getY() + pivot.getY()); // the points to rotate around (the center in my example, your left side for your problem)
            g.transform( trans );

            if(!isInverted())
                g.drawImage(currentSprite.getImage(), (int) getPos().getX(), (int) getPos().getY(), (int) transform.getGlobalScale().getX(), (int) transform.getGlobalScale().getY(), null);
            else
                g.drawImage(currentSprite.getImage(), (int) (getPos().getX()+transform.getGlobalScale().getX()), (int) getPos().getY(), -(int) transform.getGlobalScale().getX(), (int) transform.getGlobalScale().getY(), null);

            g.setTransform( backup ); // restore previous transform


        }
        g.drawRect((int) getPos().getX(), (int) getPos().getY(), (int) transform.getGlobalScale().getX(), (int) transform.getGlobalScale().getY());
    }
}
