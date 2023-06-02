package com.game.engine.components;

import com.game.engine.GameObject;

public class RectangleGameObject extends GameObject {

    public RectangleGameObject(){
        addComponent(new ShapeRender());
    }

}
