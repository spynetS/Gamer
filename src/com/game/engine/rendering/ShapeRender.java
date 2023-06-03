package com.game.engine.rendering;

import com.game.engine.msc.Debug;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class ShapeRender extends Renderer{


    @Getter
    @Setter
    private Color color = Color.darkGray;

    @Override
    public void start() {
        super.start();
        setShape(new Rect(transform.getScale()));
    }

    @Override
    public void render(Graphics2D g) {

        if(transform.getParent() != null){
            Rectangle r = new Rectangle((int) getPos().getX(), (int) getPos().getY(), (int) transform.getGlobalScale().getX(), (int) transform.getGlobalScale().getY());
            //g.fill(r);
        }
        Color colorBuffet = g.getColor();

        g.setColor(color);
        g.fill(getShape());

        g.setColor(colorBuffet);
    }
}
