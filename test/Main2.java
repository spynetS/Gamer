import com.game.engine.GameEngine;
import com.game.engine.GameObject;
import com.game.engine.Input.Input;
import com.game.engine.Input.Keys;
import com.game.engine.Scene;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import com.game.engine.physics.Collider;
import com.game.engine.physics.Rigidbody;
import com.game.engine.rendering.CircleRenderer;
import com.game.engine.rendering.ShapeRender;
import com.game.engine.rendering.shapes.Circle;

import java.awt.*;

public class Main2 {

    public static void main(String[] args){
        GameEngine engine = new GameEngine();
        //GameEngine.DELAY = 200;

        GameObject player = new GameObject(){
            @Override
            public void update() {
                super.update();
                if(Input.isKeyDown(Keys.UPARROW)){
                    Debug.log(transform.getRotation());
                    transform.setRotation(transform.getRotation()+10);
                }
                if(Input.isKeyPressed(Keys.SPACE)){
                    getComponent(Rigidbody.class).addForce(Vector2.down.multiply(1));
                }
                if(Input.isKeyDown(Keys.RIGHTARROW)){
                    getComponent(Rigidbody.class).addForce(Vector2.right);
                }
            }
        };
        player.addComponent(new ShapeRender());
        player.transform.setScale(new Vector2(1,1));
        player.transform.setPosition(new Vector2(0,1.5f));
        player.addComponent(new Rigidbody(true));
        player.getComponent(Rigidbody.class).setVelocity(new Vector2(1,0));
        player.getComponent(Rigidbody.class).setMass(1);
        player.addComponent(new Collider());
        player.tag = "Player";
        player.getComponent(ShapeRender.class).setColor(Color.RED);

        GameObject floor = new GameObject();
        floor.tag = "Ground";
        floor.addComponent(new ShapeRender());
        Rigidbody b = new Rigidbody();
        b.setMass(100);
        b.setFreeze(true);
        floor.addComponent(b);


        floor.addComponent(new Collider());
        floor.transform.setPosition(new Vector2(0,0));
        floor.transform.setScale(new Vector2(3,1));




        Scene scene = new Scene(){
            @Override
            public void update() {
                super.update();
                if(Input.isKeyDown(Keys.D)) setCameraOffset(getCameraOffset().add(Vector2.right.multiply(10)));
            }
        };
        scene.add(player);
        scene.add(floor);
        //scene.setStepUpdate(true);
        //scene.setScaleFactor(0.0001f);
        GameEngine.setSelectedScene(scene);
        engine.start();
    }

}
