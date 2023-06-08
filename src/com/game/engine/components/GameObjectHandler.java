package com.game.engine.components;

import com.game.engine.GameObject;
import com.game.engine.msc.Debug;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * GameObjectHandler is a class that handles adding removing gameobjecits and components in the world.
 * We can not just add components and gameobjects on the scripts because it is manipulating that list
 * so we do it after the update cycle is done.
 */
public class GameObjectHandler {

    @Getter
    @Setter
    HashMap<GameObject, GameObject> addChildren = new HashMap<>();

    @Getter
    @Setter
    HashMap<GameObject, GameObject> removeChildren = new HashMap<>();

    @Getter
    @Setter
    HashMap<GameObject, Component> addComponent = new HashMap<>();

    @Getter
    @Setter
    HashMap<GameObject, Component> removeComponent = new HashMap<>();

    @Getter
    @Setter
    LinkedList<GameObject> addGameObject = new LinkedList<>();

    @Getter
    @Setter
    LinkedList<GameObject> removeGameObject = new LinkedList<>();

    public GameObject instantiate(GameObject gameObject) {
        addGameObject.add(gameObject);
        return gameObject;
    }

    public GameObject instantiate(GameObject child, GameObject parent){
        addChildren.put(parent,child);
        return child;
    }

    public void addComponent(Component component, GameObject gameObject){
        addComponent.put(gameObject, component);
    }

    public ArrayList<GameObject> update(ArrayList<GameObject> gameObjects){

        if(addGameObject.size() > 0) {
            gameObjects.addAll(addGameObject);
            addGameObject.clear();
        }

        if(addComponent.size() > 0){
            for (Map.Entry<GameObject, Component> entry : addComponent.entrySet()) {

                entry.getKey().addComp(entry.getValue());
            }
            addComponent.clear();
        }

        if(addChildren.size() > 0){
            for (Map.Entry<GameObject, GameObject> entry : addChildren.entrySet()) {
                entry.getKey().addChild(entry.getValue());
            }
            addChildren.clear();
        }

        return gameObjects;
    }

}
