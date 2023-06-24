import com.game.engine.GameEngine;
import com.game.engine.GameObject;
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
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Main {

    public static void main (String[] args){

        GameEngine gameEngine = new GameEngine();
        Scene scene = new Scene();
         Rigidbody2D r1 = Rigidbody2D.create(scene);

        scene.setScaleFactor(0.001f);
        GameEngine.fpsCap = 60;

        RectangleGameObject stationary = new RectangleGameObject(Color.RED);

        stationary.transform.setScale(new Vector2(1,1));
        stationary.transform.setPosition(new Vector2(0.6f,-2));

        stationary.addComponent(r1);


        GameObject player = new GameObject();

        //ShapeRender render = new ShapeRender();

        Rigidbody2D r2 = Rigidbody2D.create(scene);
        player.transform.setPosition(new Vector2(0,2));
        r2.setType(BodyType.DYNAMIC);

        player.addComponent(r2);


        //player.addComponent(new Component() {
        //    @Override
        //    public void update() {
        //        super.update();
        //        Rigidbody2D r2 = getComponent(Rigidbody2D.class);
        //        if(Input.isKeyDown(Keys.D)){
        //            //r2.getRawBody().setLinearVelocity(new Vec2(10,0));
        //            r2.getRawBody().applyForce(new Vec2(50,0), r2.getRawBody().getWorldCenter());
        //        }
        //        if(Input.isKeyDown(Keys.A)){
        //            r2.getRawBody().applyForce(new Vec2(-50,0), r2.getRawBody().getWorldCenter());
        //            //r2.getRawBody().getPosition().set(transform.getPosition().getX()+1,transform.getPosition().getY());
        //        }
        //        //if(Input.isKeyDown(Keys.SPACE)){
        //        //    r2.getRawBody().applyForce(new Vec2(0,9.82f*10), r2.getRawBody().getWorldCenter());
        //        //}
        //        if(Input.isKeyPressed(Keys.SPACE)){
        //            r2.getRawBody().applyLinearImpulse(new Vec2(0,10),r2.getRawBody().getWorldCenter());
        //        }
        //    }
        //
        //    /**
        //     * snaps
        //     * platta
        //     *
        //     */
        //    @Override
        //    public void start() {
        //        super.start();
        //        //rigidbody2D.getRawBody().applyForceToCenter(new Vec2(1000,0));
        //    }
        //});



        scene.add(stationary);
        scene.add(player);



        GameEngine.setSelectedScene(scene);
        gameEngine.start();
    }
}
