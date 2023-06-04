package com.game.engine.components;

import com.game.engine.GameObject;
import com.game.engine.rendering.ShapeRender;
import com.game.engine.rendering.Sprite;
import com.game.engine.rendering.SpriteRenderer;

public class SpriteObject extends GameObject {

    public SpriteObject(String src){
        SpriteRenderer renderer = new SpriteRenderer();
        renderer.getSprites().add(new Sprite(src));

        addComponent(renderer);
    }

}
