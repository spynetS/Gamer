import com.game.engine.GameEngine;
import com.game.engine.GameObject;
import com.game.engine.Input.Input;
import com.game.engine.Input.Keys;
import com.game.engine.Scene;
import com.game.engine.components.RectangleGameObject;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import com.game.engine.rendering.ShapeRender;
import com.game.engine.rendering.Sprite;
import com.game.engine.rendering.SpriteRenderer;

import java.awt.*;

public class Main {

    public static void main (String[] args){
        GameEngine e = new GameEngine();
        Scene scene = new Scene(){
            @Override
            public void update() {
                super.update();
                if(Input.isKeyDown(Keys.RIGHTARROW)){
                    setCameraOffset(getCameraOffset().add(Vector2.right));
                }
            }
        };

        GameObject g = new GameObject();
        Sprite sprite = new Sprite();
        sprite.loadSprite("/tiles/coal.png");
        SpriteRenderer sr = new SpriteRenderer();
        sr.addSprite(sprite);


        g.addComponent(sr);

        scene.add(g);

        GameEngine.setSelectedScene(scene);
        e.start();
    }
}
