package com.game.engine.rendering;

import com.game.engine.msc.Debug;
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
    }

    @Override
    public void start() {
        super.start();
        setShape(new Rect(transform.getScale()));
    }

    @Override
    public void render(Graphics2D g) {

        Color colorBuffet = g.getColor();
        g.setColor(color);
        g.fill(getShape());
        g.setColor(colorBuffet);
    }
}
