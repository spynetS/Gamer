package com.game.engine.components;

import com.game.engine.msc.Debug;

import java.awt.*;

public class ShapeRender extends Renderer{


    @Override
    public void render(Graphics2D g) {
        g.fillRect((int) getPos().getX(), (int) getPos().getY(), (int) transform.getGlobalScale().getX(), (int) transform.getGlobalScale().getY());
    }
}
