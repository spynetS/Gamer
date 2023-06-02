package com.game.engine.components;

import com.game.engine.msc.Debug;

/**
 * The component class is the base of the component system. The component can be inharited
 * where you can add your own logic to control the GameObjects.
 */
public abstract class Component {

    public Transform transform;

    public void start(){}

    public void update(){
    }

}
