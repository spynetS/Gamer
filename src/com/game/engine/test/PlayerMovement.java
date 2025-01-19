package com.game.engine.test;

import com.game.engine.Input.Input;
import com.game.engine.Input.Keys;
import com.game.engine.components.Component;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import com.game.engine.physics.Rigidbody;

/**
 * PlayerMovement
 */
public class PlayerMovement extends Component {

	public void update(){
		if(this.transform != null){
			if(Input.isKeyPressed(Keys.SPACE)){
				this.transform.getComponent(Rigidbody.class).addForce(Vector2.up);
			}
			if(Input.isKeyPressed(Keys.D)){
				this.transform.getComponent(Rigidbody.class).addForce(Vector2.left);
			}
			if(Input.isKeyPressed(Keys.A)){
				this.transform.getComponent(Rigidbody.class).addForce(Vector2.right);
			}
		}
		else{
			if(Input.isKeyPressed(Keys.SPACE)){
				this.transform.translate(new Vector2(0,100));
			}
			if(Input.isKeyPressed(Keys.D)){
				this.transform.translate(new Vector2(100,0));
			}
			if(Input.isKeyPressed(Keys.A)){
				this.transform.translate(new Vector2(-100,0));
			}
		}

	}
}
