package com.game.engine.collision;

import com.game.engine.GameObject;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import com.game.engine.physics.Rigidbody;
import com.game.engine.rendering.ShapeRender;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CollisionDetector {



    public void checkCollision(GameObject g1, ArrayList<GameObject> gameObjects) {
        LinkedList<Collider> colliders = new LinkedList<>();
        for (GameObject g2 : gameObjects) {
            Collider c1 = g1.getComponent(Collider.class);
            Collider c2 = g2.getComponent(Collider.class);
            if (g1 != g2 && c1 != null && c2 != null && c1.transform.getPosition().getDistance(c2.transform.getPosition()) < 300f){//&& !colliders.contains(c1) && !colliders.contains(c2)) {
                //colliders.add(c1);
                //colliders.add(c2);

                if (c1.collides(c2)) {
                    Rigidbody rigid1 = c1.getComponent(Rigidbody.class);
                    Rigidbody rigid2 = c2.getComponent(Rigidbody.class);
                    Debug.startCount();

                    c1.transform.getGameObject().onCollisionEnter(c2);
                    c2.transform.getGameObject().onCollisionEnter(c1);

                    if (rigid1 != null && rigid2 != null) {
                        rigid1.resolveCollision(rigid2);
                    }
                    moveCollider(c1, c2);
                }
            }
        }

    }

    public void moveCollider(Collider c1, Collider c2){
        int i = 0;
        Vector2 old   = c1.transform.getGlobalPosition();
        Vector2 direction = c1.transform.getPosition().lookAt(c2.transform.getPosition());

        if(!direction.containsZero()){
            while (c1.collides(c2)){
                c1.transform.setPosition(c1.transform.getPosition().subtract(direction));
                c1.update();
            }
            c1.transform.setPosition(c1.transform.getPosition().subtract(direction));
        }
        //c1.update();
    }

}
