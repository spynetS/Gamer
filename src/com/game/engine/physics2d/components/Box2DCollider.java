package com.game.engine.physics2d.components;

import com.game.engine.components.Component;
import com.game.engine.msc.Vector2;
import lombok.Getter;
import lombok.Setter;

public class Box2DCollider extends Component {

    @Getter @Setter private Vector2 halfSize = new Vector2(10,10);

}
