package com.game.engine.collision;

import com.game.engine.GameEngine;
import com.game.engine.GameObject;
import com.game.engine.Input.Input;
import com.game.engine.Input.Keys;
import com.game.engine.components.Component;
import com.game.engine.components.Transform;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import com.game.engine.physics.Rigidbody;
import com.game.engine.rendering.Renderer;
import com.game.engine.rendering.ShapeRender;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Area;
import java.util.LinkedList;

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
