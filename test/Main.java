import com.game.engine.GameEngine;
import com.game.engine.Input.Input;
import com.game.engine.Input.Keys;
import com.game.engine.Scene;
import com.game.engine.collision.Collider;
import com.game.engine.components.*;
import com.game.engine.components.Component;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import com.game.engine.physics2d.components.Rigidbody2D;
import com.game.engine.rendering.ShapeRender;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Main {

    public static void main (String[] args){

        GameEngine gameEngine = new GameEngine();

        Scene scene = new Scene(){
            @Override
            public void update() {
                super.update();
                getPhysics2D().update((float) GameEngine.deltaTime);
            }
        };

        scene.setScaleFactor(0.001f);
        GameEngine.fpsCap = 60;

        RectangleGameObject stationary = new RectangleGameObject(Color.RED){

            @Override
            public void onCollisionEnter(Collider collider) {
                super.onCollisionEnter(collider);
                getComponent(ShapeRender.class).setColor(Color.black);
            }

            @Override
            public void onCollisionLeft(Collider collide) {
                super.onCollisionLeft(collide);
                getComponent(ShapeRender.class).setColor(Color.RED);
            }
        };
        stationary.addComponent(new Rigidbody2D(){
            @Override
            public void start() {
                super.start();
                setMass(100);
                setFreeze(true);
            }
        });

        Rigidbody2D rigidbody1 = new Rigidbody2D();
        stationary.transform.setScale(new Vector2(5,1));

        stationary.transform.setRotation(45);

        rigidbody1.setBodyType(BodyType.STATIC);

        stationary.addComponent(rigidbody1);
        Debug.log("before add");
        scene.getPhysics2D().add(rigidbody1);
        Debug.log("after add");


        RectangleGameObject player = new RectangleGameObject();

        Rigidbody2D rigidbody2D = new Rigidbody2D();
        player.addComponent(rigidbody2D);
        //rigidbody2D.setBodyType(BodyType.KINEMATIC);

        player.transform.setScale(new Vector2(1,2));
        player.transform.setPosition(new Vector2(0,3));

        player.addComponent(new Component() {
            @Override
            public void update() {
                super.update();
                Rigidbody2D r2 = getComponent(Rigidbody2D.class);
                if(Input.isKeyDown(Keys.D)){
                    //r2.getRawBody().setLinearVelocity(new Vec2(10,0));
                    r2.getRawBody().applyForce(new Vec2(50,0), new Vec2(0,0));
                }
                if(Input.isKeyDown(Keys.A)){
                    r2.getRawBody().applyForce(new Vec2(-50,0), new Vec2(0,0));
                    //r2.getRawBody().getPosition().set(transform.getPosition().getX()+1,transform.getPosition().getY());
                }
                if(Input.isKeyDown(Keys.SPACE)){
                    r2.getRawBody().applyForceToCenter(new Vec2(0,10));
                }
            }

            @Override
            public void start() {
                super.start();
                //rigidbody2D.getRawBody().applyForceToCenter(new Vec2(1000,0));
            }
        });

        scene.getPhysics2D().add(rigidbody2D);



        scene.add(player);
        scene.add(stationary);



        GameEngine.setSelectedScene(scene);
        gameEngine.start();
    }
}
