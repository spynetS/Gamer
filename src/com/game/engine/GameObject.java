package com.game.engine;

import com.game.engine.components.Component;
import com.game.engine.components.Renderer;
import com.game.engine.components.ShapeRender;
import com.game.engine.components.Transform;
import com.game.engine.msc.Debug;

import java.awt.*;
import java.util.ArrayList;

public class GameObject {

    public String tag = "";
    public String name = "";

    public Transform transform = new Transform(this);

    public ArrayList<Component> components = new ArrayList<>();

    public ArrayList<GameObject> gameObjects = new ArrayList<>();

    public void addComponent(Component component){
        component.transform = this.transform;
        components.add(component);
    }

    public void addChild(GameObject gameObject){
        gameObjects.add(gameObject);
        gameObject.transform.setParent(this);
    }

    public <T extends Component> T getComponent(Class<T> type){

        for(Component c : components){
            if(type.isInstance(c)){
                return (T)c;
            }
        }
        return null;
    }

    public void update(){
        for(Component c : components){
            c.update();
        }
        transform.update();
        for(GameObject c : gameObjects){
            c.update();
        }
    }

    public void render(Graphics2D g){
        Renderer renderer = this.getComponent(Renderer.class);

        if(renderer != null) {
            renderer.render(g);
        }

        for(GameObject c : gameObjects){
            c.render(g);
        }
    }

}
