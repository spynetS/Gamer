package com.game.engine.editor.components;

import com.game.engine.Input.Input;
import com.game.engine.Input.InputComponent;
import com.game.engine.Input.Keys;
import com.game.engine.components.Component;
import com.game.engine.physics.Rigidbody;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;

public class PlayerMovement extends Component {

    InputComponent input = new InputComponent();
    public void start(){
        Input.addContext("com.game.engine.editor.components.PlayerMovement");
        input.setContext("com.game.engine.editor.components.PlayerMovement");
        transform.getGameObject().addComponent(input);
    }

    public void update(){

        /*
        if(input.isKeyDown(Keys.A)){
            //transform.setPosition(transform.getPosition().add(Vector2.left.multiply(1)));
            getComponent(Rigidbody.class).addForce(Vector2.left);
        }
        if(input.isKeyDown(Keys.D)){
            //transform.setPosition(transform.getPosition().add(Vector2.right.multiply(1)));
            getComponent(Rigidbody.class).addForce(Vector2.right);
        }
        if(input.isKeyDown(Keys.W)){
            transform.setPosition(transform.getPosition().add(Vector2.up.multiply(1)));
        }
        if(input.isKeyDown(Keys.S)){
            transform.setPosition(transform.getPosition().add(Vector2.down.multiply(1)));
        }
        if(input.isKeyDown(Keys.E)){
            transform.setRotation(transform.getRotation()+1);
        }

        if(Input.isKeyDown(Keys.D))
            transform.setPosition(transform.getPosition().add(new Vector2(1f,0)));
        if(Input.isKeyDown(Keys.A))
            transform.setPosition(transform.getPosition().add(new Vector2(-1f,0)));

    */

        if(input.isKeyDown(Keys.A)){
            //transform.setPosition(transform.getPosition().add(Vector2.left.multiply(1)));
            getComponent(Rigidbody.class).addForce(Vector2.left);
        }
        if(input.isKeyDown(Keys.D)){
            //transform.setPosition(transform.getPosition().add(Vector2.right.multiply(1)));
            getComponent(Rigidbody.class).addForce(Vector2.right);
        }
        if(input.isKeyDown(Keys.W)){
            //transform.setPosition(transform.getPosition().add(Vector2.left.multiply(1)));
            getComponent(Rigidbody.class).addForce(Vector2.up);
        }
        if(input.isKeyDown(Keys.S)){
            //transform.setPosition(transform.getPosition().add(Vector2.right.multiply(1)));
            getComponent(Rigidbody.class).addForce(Vector2.down);
        }

        if(input.isKeyPressed(Keys.UPARROW)){
            Debug.log("scale up");
            //transform.setScaleFactor(new Vector2(2,2));
            transform.setScale(transform.getScale().add(1));
        }
        else if(input.isKeyPressed(Keys.DOWNARROW)){
            transform.setScaleFactor(new Vector2(0.5f,0.5f));
        }
        if(input.isKeyDown(Keys.E)){
            transform.setRotation(transform.getRotation()+1);
        }
        if(input.isKeyDown(Keys.Q)){
            transform.setRotation(transform.getRotation()-1);
        }
    }

}
