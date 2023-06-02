package com.game.engine.rendering;

import java.awt.*;

public class ShapeRender extends Renderer{


    @Override
    public void start() {
        super.start();
        setShape(new Rect(transform.getScale()));
    }

    @Override
    public void render(Graphics2D g) {

        //g.fillRect((int) getPos().getX(), (int) getPos().getY(), (int) transform.getGlobalScale().getX(), (int) transform.getGlobalScale().getY());
        g.fill(getShape());
    }
}
