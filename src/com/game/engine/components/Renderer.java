package com.game.engine.components;


import com.game.engine.msc.Vector2;

import java.awt.*;

public class Renderer extends Component {

    protected Vector2 getPos(){
        if(transform.getParent() == null)
            return transform.getPosition().subtract(transform.getScale().divide(2));
        else{
            Vector2 scale = transform.getGlobalPosition().subtract(transform.getGlobalScale().divide(2));

            return scale;
        }
    }

    public void render(Graphics2D g){

    }

}
