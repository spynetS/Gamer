package com.game.engine.components;

import com.game.engine.GameObject;
import com.game.engine.rendering.ShapeRender;

public class RectangleGameObject extends GameObject {

    public RectangleGameObject(){
        addComponent(new ShapeRender());
    }

}
