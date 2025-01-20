package com.game.engine.test;

import com.game.engine.GameEngine;
import com.game.engine.GameObject;
import com.game.engine.Input.Input;
import com.game.engine.Input.InputComponent;
import com.game.engine.Input.Keys;
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
import com.game.engine.rendering.SpriteRenderer;

import java.awt.*;
import java.util.LinkedList;

public class Main {

    public static void main (String[] args){

        GameEngine gameEngine = new GameEngine();
        gameEngine.setTitle("Example");

        Debug.showWhere = true;
        Scene scene = new Scene();
        scene.setBackground(new Color(40, 125, 255));

        GameObject player = new GameObject();

        LinkedList<Rectangle> boxes = new LinkedList<>();
        for(int i = 0; i < 6; i ++){
            boxes.add(new Rectangle(48*i,0,48, 48));
        }

        //player.transform.setScale(new Vector2(200,200));

        SpriteRenderer renderer = new SpriteRenderer();

        LinkedList<Sprite> animations = Sprite.getSprites("/sprites/characters/player.png", boxes);
        renderer.addAnimation(animations);

        PlayerMovement playerMovement = new PlayerMovement();
        playerMovement.transform = player.transform;

        player.addComponent(playerMovement);
        player.addComponent(new Rigidbody(false));
        player.addComponent(renderer);
        player.addComponent(new Collider());

        scene.add(player);

        GameEngine.setSelectedScene(scene);
        gameEngine.start();
    }
}
