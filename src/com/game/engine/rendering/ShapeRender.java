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
    public void start() {
        super.start();
        Debug.log(transform.getScale());

    }

    @Override
    public void render(Graphics2D g) {

        Color colorBuffet = g.getColor();
        g.setColor(color);
        //Debug.log(getShape().getBounds());
        g.fill(getPolygon());
        //g.drawRect((int) getShapeGlobal().get(2).getX(), (int) getShapeGlobal().get(2).getY(), 100,100);
        g.setColor(colorBuffet);
    }
}
