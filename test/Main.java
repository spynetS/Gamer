import com.game.engine.GameEngine;
import com.game.engine.GameObject;
import com.game.engine.Input.Input;
import com.game.engine.Input.Keys;
import com.game.engine.Scene;
import com.game.engine.components.*;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import com.game.engine.rendering.ShapeRender;
import com.game.engine.rendering.Sprite;
import com.game.engine.rendering.SpriteRenderer;

import java.awt.*;

public class Main {

    public static void main (String[] args){

        GameEngine gameEngine = new GameEngine();

        Scene scene = new Scene();

        Sprite grass = new Sprite("/tiles/GrassTile.png");
        Sprite tree = new Sprite("/tiles/trees.png");
        Sprite tree2 = new Sprite("/tiles/trees2.png");
        for(int i = 0 ; i < 10; i ++){
            for(int j = 0 ; j < 10; j ++){
                GameObject g = new GameObject();
                SpriteRenderer renderer = new SpriteRenderer();

                renderer.addSprite(tree);
                renderer.addSprite(tree2);

                g.addComponent(renderer);
                g.transform.setPosition(new Vector2(i*100,j*100));
                scene.add(g);
            }
        }
        SpriteObject g = new SpriteObject ("/tiles/coal.png"){
            @Override
            public void update() {
                super.update();
                transform.setPosition(Input.getMousePosition());
                if(Input.isKeyDown(Keys.D)){
                    scene.getCameraOffset().adds(Vector2.right);
                }
                if(Input.isKeyDown(Keys.A)){
                    scene.getCameraOffset().adds(Vector2.left);
                }
                if(Input.isKeyDown(Keys.W)){
                    scene.getCameraOffset().adds(Vector2.up);
                }
                if(Input.isKeyDown(Keys.S)){
                    scene.getCameraOffset().adds(Vector2.down);
                }
            }
        };

        scene.add(g);

        GameEngine.setSelectedScene(scene);

        gameEngine.start();
    }
}
