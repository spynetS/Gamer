package com.game.engine.collision;

import com.game.engine.rendering.ShapeRender;

import java.awt.*;
import java.awt.geom.Area;

public class Collider extends ShapeRender {

    public boolean collides(Collider c) {

        Area areaA = new Area(getShape());
        areaA.intersect(new Area(c.getShape()));
        return !areaA.isEmpty();
    }

    @Override
    public void render(Graphics2D g) {

        Color colorBuffet = g.getColor();
        g.setColor(Color.green);
        g.draw(getShape());
        g.setColor(colorBuffet);

    }
}
