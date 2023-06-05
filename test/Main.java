import com.game.engine.GameEngine;
import com.game.engine.GameObject;
import com.game.engine.Input.Input;
import com.game.engine.Scene;
import com.game.engine.components.*;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import com.game.engine.rendering.Renderer;
import com.game.engine.rendering.ShapeRender;

import java.awt.*;

public class Main {

    public static void main (String[] args){

        GameEngine gameEngine = new GameEngine();

        Scene scene = new Scene();
        Input.addContext("all");
        /*
         * change a scene and then save it to a load file
         * when it starts we load in that load file
         */
        RectangleGameObject g = new RectangleGameObject();
        RectangleGameObject g2 = new RectangleGameObject();

        g.addChild(g2);
        g2.transform.setPosition(new Vector2(200,200));

        scene.add(g);
        g.transform.setPosition(new Vector2(100,0));

        scene.add(new RectangleGameObject());

        GameEngine.setSelectedScene(scene);

        gameEngine.start();
    }
}
