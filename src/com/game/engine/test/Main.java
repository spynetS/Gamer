package com.game.engine.test;

import com.game.engine.GameEngine;
import com.game.engine.GameObject;
import com.game.engine.Input.Input;
import com.game.engine.collision.Collider;
import com.game.engine.Scene;
import com.game.engine.components.*;
import com.game.engine.components.Component;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import com.game.engine.physics.Rigidbody;
import com.game.engine.rendering.Renderer;
import com.game.engine.rendering.ShapeRender;
import com.game.engine.rendering.Sprite;


import java.awt.*;

public class Main {

    public static void main (String[] args){

        GameEngine gameEngine = new GameEngine();
        gameEngine.setTitle("Example");


        Scene scene = new Scene();

        GameObject player = new GameObject(){
                @Override
                public void update(){
                    super.update();
                    //this.transform.translate(new Vector2(1,0));
                }
            };
        player.addComponent(new ShapeRender());
        player.addComponent(new PlayerMovement());
        player.addComponent(new Rigidbody(true));
        player.addComponent(new Collider());
        scene.add(player);


        RectangleGameObject floor = new RectangleGameObject();
        floor.getComponent(ShapeRender.class).setColor(Color.RED);
        floor.transform.setScale(new Vector2(500,100));
        floor.transform.setPosition(new Vector2(0,400));
        floor.addComponent(new Rigidbody(false));
        floor.addComponent(new Collider());
        scene.add(floor);


        GameEngine.setSelectedScene(scene);
        gameEngine.start();
    }
}
