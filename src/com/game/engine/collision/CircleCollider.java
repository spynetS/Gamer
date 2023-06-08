package com.game.engine.collision;

import com.game.engine.rendering.ShapeRender;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class CircleCollider extends Collider {

    @Override
    public boolean collides(Collider c) {
        Area areaA = new Area(getShape1());
        areaA.intersect(new Area(c.getShape()));
        return !areaA.isEmpty();
    }

    public Ellipse2D.Double getShape1() {
        Ellipse2D.Double d = new Ellipse2D.Double();
        d.x = getPos().getX();
        d.y = getPos().getY();
        d.width = transform.getScale().getX();
        d.height = transform.getScale().getY();
        return d;
    }

    @Override
    public void render(Graphics2D g) {

        Color colorBuffet = g.getColor();
        g.setColor(Color.green);
        g.draw(getShape1());
        g.setColor(colorBuffet);

    }
}
