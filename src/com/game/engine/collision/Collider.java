package com.game.engine.collision;

import com.game.engine.Input.Input;
import com.game.engine.Input.Keys;
import com.game.engine.components.Component;
import com.game.engine.msc.Vector2;
import com.game.engine.rendering.Renderer;
import com.game.engine.rendering.ShapeRender;

import java.awt.*;
import java.util.LinkedList;

public class Collider extends ShapeRender {

    public boolean collides(Collider c) {
        for (Vector2 vertex : shapeGlobal) {
            Point p = new Point((int) vertex.getX(), (int) vertex.getY());
            if (c.getShape().contains(p)) {
                try {
                    //parent.setPosition(parent.getPosition().subtract(new Vector2(0,1)));
                } catch (Exception e) {
                }
                return true;
            }
        }
        for (Vector2 vertex : c.shapeGlobal) {
            Point p = new Point((int) vertex.getX(), (int) vertex.getY());
            if (getShape().contains(p)) {
                try {
                    //parent.setPosition(parent.getPosition().subtract(new Vector2(0,1)));
                } catch (Exception e) {
                }
                return true;
            }
        }
        return false;
    }

}
