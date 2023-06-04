import com.game.engine.GameObject;
import com.game.engine.Scene;
import com.game.engine.collision.Collider;
import com.game.engine.components.RectangleGameObject;
import com.game.engine.msc.Vector2;
import com.game.engine.physics.Rigidbody;
import com.game.engine.rendering.Renderer;
import com.game.engine.rendering.ShapeRender;

import java.awt.*;

public class CollisionScene extends Scene {

    public CollisionScene (){

        GameObject gameObject1 = new GameObject();
        gameObject1.addComponent(new ShapeRender());
        gameObject1.addComponent(new Collider());
        gameObject1.addComponent(new Rigidbody());
        gameObject1.addComponent(new PlayerMovement());

        gameObject1.getComponent(Rigidbody.class).setMass(0.5f);
        gameObject1.getComponent(Rigidbody.class).setAngularVelocity(0);

        gameObject1.transform.setPosition(new Vector2(200,-200));
        gameObject1.getComponent(Rigidbody.class).setUseGravity(false);


        RectangleGameObject gameObject2 = new RectangleGameObject();
        gameObject2.addComponent(new Collider());
        gameObject2.transform.setPosition(new Vector2(200,0));
        gameObject2.addComponent(new Rigidbody());
        gameObject2.getComponent(Rigidbody.class).setMass(100f);
        gameObject2.getComponent(Rigidbody.class).setFreeze(false);


        add(gameObject1);
        add(gameObject2);
    }

}
