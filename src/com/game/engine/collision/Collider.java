package com.game.engine.collision;

import com.game.engine.GameEngine;
import com.game.engine.GameObject;
import com.game.engine.Input.Input;
import com.game.engine.Input.Keys;
import com.game.engine.components.Component;
import com.game.engine.components.Transform;
import com.game.engine.msc.Vector2;
import com.game.engine.physics.Rigidbody;
import com.game.engine.rendering.Renderer;
import com.game.engine.rendering.ShapeRender;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Area;
import java.util.LinkedList;

public class Collider extends ShapeRender {

    public boolean collides(Collider c) {
        /*
        for (Vector2 vertex : shapeGlobal) {
            Point p = new Point((int) vertex.getX(), (int) vertex.getY());
            if (c.getShape().contains(p)) {
                try {
                    //parent.setPosition(parent.getPosition().subtract(new Vector2(0,1)));
                } catch (Exception e) {
                }
                return true;
            }
        }
        for (Vector2 vertex : c.shapeGlobal) {
            Point p = new Point((int) vertex.getX(), (int) vertex.getY());
            if (getShape().contains(p)) {
                try {
                    //parent.setPosition(parent.getPosition().subtract(new Vector2(0,1)));
                } catch (Exception e) {
                }
                return true;
            }
        }*/

        Area areaA = new Area(getShape());
        areaA.intersect(new Area(c.getShape()));
        return !areaA.isEmpty();

    }
    @Getter
    @Setter
    Transform prevTransform = new Transform(null);
    @Override
    public void update() {
        super.update();
        if(false){

            for(GameObject gameObject : GameEngine.getSelectedScene().getGameObjects()){
                if(gameObject != transform.getGameObject()){
                    Collider c1 = this.getComponent(Collider.class);
                    Collider c2 = gameObject.getComponent(Collider.class);

                    if(c1.collides(c2) && c1.isStarted() && c2.isStarted()){

                        Rigidbody rigid1 = c1.getComponent(Rigidbody.class);
                        Rigidbody rigid2 = c2.getComponent(Rigidbody.class);

                        if(rigid1 != null && rigid2 != null){
                            //rigid1.resolveCollision(rigid2);
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

        prevTransform = transform;
    }
}
