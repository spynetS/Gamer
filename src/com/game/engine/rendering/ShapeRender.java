package com.game.engine.rendering;

import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import com.game.engine.rendering.shapes.Rect;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.LinkedList;

public class ShapeRender extends Renderer{


    @Getter
    @Setter
    private Color color = Color.WHITE;

    public ShapeRender(Color white) {
        setColor(white);
    }

    public ShapeRender(LinkedList<Vector2> shape) {
        setShape(shape);
        //Debug.log(getShape());
    }
    public ShapeRender() {
        setShape(new Rect(new Vector2(100,100)));
        //Debug.log(getShape());
    }

    @Override
    public void render(Graphics2D g) {

        Color colorBuffet = g.getColor();
        g.setColor(color);
        //Debug.log(getShape().getBounds());
        LinkedList<Vector2> points = getShapeGlobal();
        for(Vector2 p : points) {
            g.drawOval((int)(p.getX()),(int)(p.getY()),10,10);
        }
        g.draw(getPolygon());

        g.scale(1,-1);
        g.drawString(transform.getPosition().multiply(100).toString(),(int)(transform.getPosition().getX()*100),(int)(transform.getPosition().getY()*100));
        g.scale(1,-1);

        //g.fill(getPolygon());
        //g.drawRect((int) getShapeGlobal().get(2).getX(), (int) getShapeGlobal().get(2).getY(), 100,100);
        g.setColor(colorBuffet);
    }
}
