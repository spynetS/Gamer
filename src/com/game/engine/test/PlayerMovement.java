package com.game.engine.test;

import com.game.engine.GameEngine;
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

	public float speed = 4;

	@Override
	public void update(){
		super.update();
		if(Input.isKeyDown(Keys.D)){
			this.transform.translate(Vector2.right.multiply(speed));
		}
		if(Input.isKeyDown(Keys.A)){
			this.transform.translate(Vector2.left.multiply(speed));
		}
		if(Input.isKeyDown(Keys.W)){
			this.transform.translate(Vector2.up.multiply(speed));
		}
		if(Input.isKeyDown(Keys.S)){
			this.transform.translate(Vector2.down.multiply(speed));
		}
	}
}
