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

        GameObject player = new GameObject();

        SpriteRenderer spriteRenderer = new SpriteRenderer();
        spriteRenderer.addSprite(new Sprite("/tiles/GrassTile.png"));

        player.addComponent(spriteRenderer);

        player.addComponent(new Rigidbody());
        player.addComponent(new PlayerMovement());

        scene.add(player);

        GameEngine.setSelectedScene(scene);
        gameEngine.start();
    }
}
