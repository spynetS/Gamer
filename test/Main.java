import com.game.engine.GameEngine;
import com.game.engine.GameObject;
import com.game.engine.Input.Input;
import com.game.engine.Input.Keys;
import com.game.engine.Scene;
import com.game.engine.SceneHolder;
import com.game.engine.components.RectangleGameObject;
import com.game.engine.components.Renderer;
import com.game.engine.components.ShapeRender;
import com.game.engine.components.Transform;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main (String[] args){



        GameEngine gameEngine = new GameEngine();

        Scene scene = new Scene();


        RectangleGameObject gameObject1 = new RectangleGameObject();
        gameObject1.addComponent(new PlayerMovement());

        GameObject text = new GameObject(){
            @Override
            public void update() {
                super.update();
                if(Input.isKeyPressed(Keys.U)){
                    transform.setScale(transform.getScale().add(1));
                }
                if(Input.isKeyPressed(Keys.J)){
                    transform.setScale(transform.getScale().subtract(1));
                }
            }
        };

        text.addComponent(new ShapeRender());

       // gameObject1.addChild(text);
        text.transform.setPosition(new Vector2(10, -20));

        scene.add(gameObject1);

        Scene scene2 = new Scene();

        for(int y = 0; y < 10; y ++){
            for(int x = 0; x < 10; x ++){
                RectangleGameObject g = new RectangleGameObject();

                g.transform.setPosition(new Vector2(x*16-(50),y*16-(50)));
                scene2.add(g);
            }
        }

        GameEngine.setSelectedScene(scene);

        gameEngine.start();
    }
}
