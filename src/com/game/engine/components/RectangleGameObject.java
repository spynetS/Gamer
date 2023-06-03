package com.game.engine.components;

import com.game.engine.GameObject;
import com.game.engine.rendering.ShapeRender;

import java.awt.*;

public class RectangleGameObject extends GameObject {

    public RectangleGameObject(){
        addComponent(new ShapeRender());
    }

    public RectangleGameObject(Color c){
        addComponent(new ShapeRender());
        getComponent(ShapeRender.class).setColor(c);
    }

}
