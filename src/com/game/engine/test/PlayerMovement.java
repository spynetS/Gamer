package com.game.engine.test;

import com.game.engine.Input.Input;
import com.game.engine.Input.Keys;
import com.game.engine.components.Component;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;

/**
 * PlayerMovement
 */
public class PlayerMovement extends Component {

	public void update(){
		if(Input.isKeyPressed(Keys.SPACE)){
			this.transform.setScale(new Vector2(300,300));
		}
	}
}
