package com.game.engine.components;

import com.game.engine.GameEngine;
import com.game.engine.GameObject;
import com.game.engine.Scene;
import lombok.Getter;
import lombok.Setter;

/**
 * The component class is the base of the component system. The component can be inharited
 * where you can add your own logic to control the GameObjects.
 */
public abstract class Component implements Comp{

    @Getter @Setter private boolean started = false;
    public Transform transform;

    @Override
    public Transform transform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public void start(){
        started = true;
    }

    public void update(){}

    public void __update__(){

    }
    public <T extends Component> T getComponent(Class<T> tClass){
        return transform().getGameObject().getComponent(tClass);
    }

    public void instantiate(GameObject gameObject) {
        Scene selected = GameEngine.getSelectedScene();
        if(selected != null){
            selected.getGameObjectHandler().getAddGameObject().add(gameObject);
        }
    }

    public GameObject instantiate(GameObject child, GameObject parent) {
        Scene selected = GameEngine.getSelectedScene();
        if (selected != null) {
            selected.getGameObjectHandler().getAddChildren().put(parent, child);
        }
        return child;
    }

    public void updateSecond() {
    }

    public void updateMillisecond() {
    }



}
