package com.game.engine.collision;

import com.game.engine.GameObject;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import com.game.engine.physics.Rigidbody;
import com.game.engine.rendering.ShapeRender;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class CollisionDetector {



    public void checkCollision(ArrayList<GameObject> gameObjects) {
        LinkedList<Collider> colliders = new LinkedList<>();

        for (GameObject g1 : gameObjects){
            for (GameObject g2 : gameObjects){
                Collider c1 = g1.getComponent(Collider.class);
                Collider c2 = g2.getComponent(Collider.class);
                if(g1 != g2 && c1 != null && c2!=null && !colliders.contains(c1) && !colliders.contains(c2) ){
                    colliders.add(c1);
                    colliders.add(c2);

                    if(c1.collides(c2)){

                        Rigidbody rigid1 = c1.getComponent(Rigidbody.class);
                        Rigidbody rigid2 = c2.getComponent(Rigidbody.class);

                        if(rigid1 != null && rigid2 != null){
                            rigid1.resolveCollision(rigid2);
                        }
                        moveCollider(c1,c2);
                        //rigid1.setVelocity(Vector2.zero);
                        Debug.log(rigid1);

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

    public void moveCollider(Collider c1, Collider c2){
        int i = 0;


        while (c1.collides(c2)){
            c1.transform.setPosition(c1.transform.getPosition().subtract(new Vector2(0,1)));
            c1.update();
            i++;
        }

    }

}
