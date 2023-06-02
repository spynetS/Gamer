import com.game.engine.GameEngine;
import com.game.engine.Input.Input;
import com.game.engine.Input.InputComponent;
import com.game.engine.Input.Keys;
import com.game.engine.components.Component;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;

public class PlayerMovement extends Component {

    InputComponent input = new InputComponent();
    public void start(){
        Input.addContext("PlayerMovement");
        input.setContext("PlayerMovement");
        Debug.log(transform.getGameObject());
        GameEngine.getSelectedScene().getGameObjectHandler().addComponent(input, transform.getGameObject());
        //transform.getGameObject().addComponent(input);
    }

    public void update(){

        if(input.isKeyPressed(Keys.A)){
            Debug.log("A was pressed");
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
