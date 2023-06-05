package com.game.engine.components;

import com.game.engine.GameObject;
import com.game.engine.msc.Vector2;
import com.game.engine.rendering.ShapeRender;

import java.awt.*;

public class RectangleGameObject extends GameObject {

    public RectangleGameObject(){
        super(Vector2.zero);
        addComponent(new ShapeRender());
    }

    public RectangleGameObject(Color c){
        super(Vector2.zero);
        addComponent(new ShapeRender());
        getComponent(ShapeRender.class).setColor(c);
    }

    public RectangleGameObject(Vector2 position) {
        super(position);
    }
}
