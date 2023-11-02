import com.game.engine.GameEngine;
import com.game.engine.GameObject;
import com.game.engine.Scene;
import com.game.engine.msc.Vector2;
import com.game.engine.physics.Rigidbody;
import com.game.engine.physics2d.components.Box2DCollider;
import com.game.engine.physics2d.components.Rigidbody2D;
import com.game.engine.rendering.Renderer;
import com.game.engine.rendering.ShapeRender;

public class Main2 {

    public static void main(String[] args){
        GameEngine engine = new GameEngine();

        GameObject player = new GameObject();
        player.addComponent(new ShapeRender());
        player.addComponent(new Rigidbody(true));

        GameObject floor = new GameObject();
        floor.addComponent(new Box2DCollider());
        floor.addComponent(new ShapeRender());
        floor.transform.setScale(new Vector2(2,1));
        floor.transform.setPosition(new Vector2(0,-2));

        Scene scene = new Scene(){
            @Override
            public void update() {
                super.update();
            }
        };

        scene.add(player);
        scene.add(floor);


        GameEngine.setSelectedScene(scene);
        engine.start();
    }

}
