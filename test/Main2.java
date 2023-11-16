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

        Debug.showWhere = true;
        GameObject player = new GameObject();
        player.addComponent(new ShapeRender());
        player.transform.setScale(new Vector2(1,1));
        player.transform.setPosition(new Vector2(0,2));
        player.addComponent(new Rigidbody(true));
        player.addComponent(new Collider());
        player.getComponent(ShapeRender.class).setColor(Color.RED);

        GameObject floor = new GameObject();
        floor.addComponent(new ShapeRender());
        floor.addComponent(new Collider());
        floor.transform.setPosition(new Vector2(0,-2));
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
        //scene.setScaleFactor(0.0001f);

        GameEngine.setSelectedScene(scene);
        engine.start();
    }

}
