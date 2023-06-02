import com.game.engine.GameEngine;
import com.game.engine.GameObject;
import com.game.engine.Input.Input;
import com.game.engine.Input.InputComponent;
import com.game.engine.Input.Keys;
import com.game.engine.components.Component;
import com.game.engine.components.RectangleGameObject;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;

public class PlayerMovement extends Component {

    InputComponent input = new InputComponent();
    public void start(){
        Input.addContext("PlayerMovement");
        input.setContext("PlayerMovement");


        GameObject child = instantiate(new RectangleGameObject(), transform.getGameObject());
        child.transform.setPosition(new Vector2(10,10));

        transform.getGameObject().addComponent(input);
    }

    public void update(){

        if(input.isKeyDown(Keys.A)){
            transform.setPosition(transform.getPosition().add(Vector2.left.multiply(1)));
        }
        if(input.isKeyDown(Keys.D)){
            transform.setPosition(transform.getPosition().add(Vector2.right.multiply(1)));
        }
        if(input.isKeyDown(Keys.W)){
            transform.setPosition(transform.getPosition().add(Vector2.up.multiply(1)));
        }
        if(input.isKeyDown(Keys.S)){
            transform.setPosition(transform.getPosition().add(Vector2.down.multiply(1)));
        }

        /*
        if(Input.isKeyDown(Keys.D))
            transform.setPosition(transform.getPosition().add(new Vector2(1f,0)));
        if(Input.isKeyDown(Keys.A))
            transform.setPosition(transform.getPosition().add(new Vector2(-1f,0)));

        if(Input.isKeyPressed(Keys.UPARROW) && Input.isKeyDown(Keys.SHIFT)){
            transform.setScaleFactor(new Vector2(2,2));
        }
        else if(Input.isKeyPressed(Keys.DOWNARROW) && Input.isKeyDown(Keys.SHIFT)){
            transform.setScaleFactor(new Vector2(0.5f,0.5f));
        }
        else if(Input.isKeyPressed(Keys.UPARROW)){
            transform.setScale(transform.getScale().add(1));
            //transform.setScaleFactor(new Vector2(2,2));
        }
        else if(Input.isKeyPressed(Keys.DOWNARROW)){
            transform.setScale(transform.getScale().subtract(1));
            //transform.setScaleFactor(new Vector2(0.5f,0.5f));
        }
    */
    }

}
