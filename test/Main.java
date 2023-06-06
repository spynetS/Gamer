import com.game.engine.GameEngine;
import com.game.engine.GameObject;
import com.game.engine.Input.Input;
import com.game.engine.Scene;
import com.game.engine.components.*;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import com.game.engine.physics.Rigidbody;
import com.game.engine.rendering.ShapeRender;
import com.game.engine.rendering.Sprite;
import com.game.engine.rendering.SpriteRenderer;

import java.awt.*;

public class Main {

    public static void main (String[] args){

        GameEngine gameEngine = new GameEngine();

        Scene scene = new Scene();
        scene.setScaleFactor(0.01f);

        GameObject player = new GameObject(){
            @Override
            public void start() {
                super.start();
                Debug.startCount();
            }

            @Override
            public void update() {
                super.update();
                if(transform.getPosition().getY() > 100){
                    Debug.endCountMillSeconds();
                }
            }
        };

        SpriteRenderer spriteRenderer = new SpriteRenderer();
        spriteRenderer.addSprite(new Sprite("/tiles/GrassTile.png"));

        player.addComponent(spriteRenderer);

        player.addComponent(new Rigidbody());

        player.getComponent(Rigidbody.class).setUseGravity(false);

        player.addComponent(new PlayerMovement());

        player.transform.setScale(new Vector2(100,100));

        scene.add(player);

        GameEngine.setSelectedScene(scene);
        gameEngine.start();
    }
}
