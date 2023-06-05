package com.game.engine;

import com.game.engine.components.Component;
import com.game.engine.components.EditorComponent;
import com.game.engine.msc.Vector2;
import com.game.engine.rendering.Renderer;
import com.game.engine.components.Transform;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class GameObject {

    public String tag = "";
    public String name = "";
    private EditorComponent editorComponent = new EditorComponent();
    private boolean started = false;

    //this is so we dont change the list we are updating
    private LinkedList<GameObject> newGameObjects = new LinkedList<>();
    private LinkedList<GameObject> removeGameObjects = new LinkedList<>();
    private LinkedList<Component> newComponents = new LinkedList<>();
    private LinkedList<Component> removeComponents = new LinkedList<>();

    public Transform transform = new Transform(this);

    public ArrayList<Component> components = new ArrayList<>();
    public ArrayList<GameObject> gameObjects = new ArrayList<>();

    @Getter @Setter private boolean isMouseInside = false;

    public void removeComponent(Component component){
        removeComponents.add(component);
        if (!started) addObjects();
    }
    public void addComponent(Component component){
        newComponents.add(component);
        if (!started) addObjects();
    }
    public void addComp(Component component){
        component.transform = this.transform;
        components.add(component);
    }

    public void addChild(GameObject gameObject){
        newGameObjects.add(gameObject);
        if (!started) addObjects();
    }
    public void removeChild(GameObject gameObject){
        removeGameObjects.add(gameObject);
        if (!started) addObjects();
    }
    private void addChild__(GameObject gameObject){
        gameObject.transform.setLocalPosition(gameObject.transform.getPosition().subtract(this.transform.getPosition()));

        gameObjects.add(gameObject);
        gameObject.transform.setParent(this);
    }

    public GameObject getChild(int index){
        return gameObjects.get(index);
    }
    public <T extends GameObject> T getChild(Class<T> type){
        for(GameObject c : gameObjects){
            if(type.isInstance(c)){
                return (T)c;
            }
        }
        return null;
    }

    public <T extends Component> T getComponent(Class<T> type){

        for(Component c : components){
            if(type.isInstance(c)){
                return (T)c;
            }
        }
        return null;
    }

    public void start(){
        addObjects();
        started = true;
        transform.start();
        editorComponent.transform = transform;
        editorComponent.start();
        for(Component c : components){
            c.start();
        }
        for(GameObject child : gameObjects) child.start();
    }
    private void addObjects(){
        if(newGameObjects.size() > 0){
            for(GameObject g : newGameObjects) addChild__(g);
            newGameObjects.clear();
        }
        if(removeGameObjects.size() > 0){
            for(GameObject g : removeGameObjects) gameObjects.remove(g);
            removeGameObjects.clear();
        }
        if(newComponents.size() > 0){
            for(Component g : newComponents) addComp(g);
            newComponents.clear();
        }
        if(removeComponents.size() > 0 ){
            for(Component c : removeComponents) components.remove(c);
            removeComponents.clear();
        }

    }
    public void update(){
        addObjects();
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
        if(GameEngine.getSelectedScene().isDebug() && editorComponent.isStarted())
            editorComponent.render(g);
    }

    public void updateSecond() {
        for(Component c : components){
            c.updateSecond();
        }
        transform.update();
        for(GameObject c : gameObjects){
            c.updateSecond();
        }
    }

    public void updateMillisecond() {
        for(Component c : components){
            c.updateMillisecond();
        }
        transform.update();
        for(GameObject c : gameObjects){
            c.updateMillisecond();
        }
    }

    public void onMouseOver() {
        if(!isMouseInside)
            onMouseEnter();
        isMouseInside = true;

    }
    public void onMouseEnter(){

    }
    public void onMouseLeft(){
        setMouseInside(false);
    }
}
