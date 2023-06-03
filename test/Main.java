import com.game.engine.GameEngine;
import com.game.engine.GameObject;
import com.game.engine.Scene;
import com.game.engine.components.*;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import com.game.engine.rendering.ShapeRender;

import java.awt.*;

public class Main {

    public static void main (String[] args){

        GameEngine gameEngine = new GameEngine();



        GameEngine.setSelectedScene(new CollisionScene());

        gameEngine.start();
    }
}
