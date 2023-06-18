import com.game.engine.GameEngine;
import com.game.engine.GameObject;
import com.game.engine.Input.Input;
import com.game.engine.Scene;
import com.game.engine.collision.Collider;
import com.game.engine.components.*;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import com.game.engine.physics.Rigidbody;
import com.game.engine.rendering.Renderer;
import com.game.engine.rendering.ShapeRender;
import com.game.engine.rendering.Sprite;
import com.game.engine.rendering.SpriteRenderer;

import java.awt.*;

public class Main {

    public static void main (String[] args){

        GameEngine gameEngine = new GameEngine();

        Scene scene = new Scene();


        RectangleGameObject stationary = new RectangleGameObject(Color.RED){


        };
        stationary.addComponent(new Rigidbody(){
            @Override
            public void start() {
                super.start();
                setMass(100);
                setFreeze(true);
            }
        });
        stationary.addComponent(new Collider());

        RectangleGameObject player = new RectangleGameObject();
        player.addComponent(new Collider());
        player.addComponent(new Rigidbody());
        player.addComponent(new PlayerMovement());

        player.transform.setPosition(new Vector2(200,0));

        scene.add(player);
        scene.add(stationary);



        GameEngine.setSelectedScene(scene);
        gameEngine.start();
    }
}
