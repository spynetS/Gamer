import com.game.engine.GameEngine;
import com.game.engine.GameObject;
import com.game.engine.Scene;
import com.game.engine.components.*;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;

public class Main {

    public static void main (String[] args){

        GameEngine gameEngine = new GameEngine();

        Scene scene = new Scene();


        RectangleGameObject gameObject1 = new RectangleGameObject();
        gameObject1.addComponent(new Rigidbody());
        gameObject1.addComponent(new PlayerMovement());

        gameObject1.transform.setScale(new Vector2(20,20));

        //gameObject1.getComponent(Rigidbody.class).addForce(new Vector2(1,0));
        //gameObject1.getComponent(Rigidbody.class).setMass(10);

        RectangleGameObject ch = new RectangleGameObject();
        ch.transform.setPosition(new Vector2(0,20));


        gameObject1.addChild(ch);


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
