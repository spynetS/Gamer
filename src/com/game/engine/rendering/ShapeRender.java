package com.game.engine.rendering;

import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class ShapeRender extends Renderer{


    @Getter
    @Setter
    private Color color = Color.WHITE;

    public ShapeRender(Color white) {
        setColor(white);
    }

    public ShapeRender() {
        setShape(new Rect(new Vector2(10,10)));
        Debug.log(getShape());
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void render(Graphics2D g) {

        Color colorBuffet = g.getColor();
        g.setColor(color);
        //Debug.log(getShape().getBounds());
        g.fill(getPolygon());
        g.setColor(colorBuffet);
    }
}
