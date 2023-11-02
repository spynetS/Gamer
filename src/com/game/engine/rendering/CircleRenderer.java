package com.game.engine.rendering;

import java.awt.*;

public class CircleRenderer extends Renderer{

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        g.setColor(Color.white);
        g.fillOval(0, (int) transform.getGlobalPosition().getY()*100, (int) transform.getScale().getX()*100, (int) transform.getScale().getY()*100);
    }
}
