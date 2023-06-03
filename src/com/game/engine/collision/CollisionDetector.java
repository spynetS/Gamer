package com.game.engine.collision;

import com.game.engine.GameObject;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import com.game.engine.physics.Rigidbody;
import com.game.engine.rendering.ShapeRender;

import java.awt.*;
import java.util.ArrayList;

public class CollisionDetector {



    public void checkCollision(ArrayList<GameObject> gameObjects) {

        for (GameObject g1 : gameObjects){
            for (GameObject g2 : gameObjects){
                if(g1 != g2){
                    Collider c1 = g1.getComponent(Collider.class);
                    Collider c2 = g2.getComponent(Collider.class);

                    if(c1.collides(c2)){

                        Rigidbody rigid1 = c1.getComponent(Rigidbody.class);
                        Rigidbody rigid2 = c2.getComponent(Rigidbody.class);

                        if(rigid1 != null && rigid2 != null){
                            rigid1.resolveCollision(rigid2);
                        }

                        c1.getComponent(ShapeRender.class).setColor(Color.red);
                        c2.getComponent(ShapeRender.class).setColor(Color.red);
                    }
                    else{
                        c1.getComponent(ShapeRender.class).setColor(Color.darkGray);
                        c2.getComponent(ShapeRender.class).setColor(Color.darkGray);
                    }
                }
            }
        }

    }
}
