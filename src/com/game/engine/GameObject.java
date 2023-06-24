package com.game.engine;

import com.game.engine.collision.Collider;
import com.game.engine.components.Comp;
import com.game.engine.components.Component;
import com.game.engine.components.EditorComponent;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import com.game.engine.physics2d.components.Rigidbody2D;
import com.game.engine.rendering.Renderer;
import com.game.engine.components.Transform;
import lombok.Getter;
import lombok.Setter;
import org.jbox2d.dynamics.Body;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class GameObject {

    public String tag = "";
    public String name = "";
    private EditorComponent editorComponent = new EditorComponent();
    private boolean started = false;
    private Collider colliding = null;
    //this is so we don't change the list we are updating
    private LinkedList<GameObject> newGameObjects = new LinkedList<>();
    private LinkedList<GameObject> removeGameObjects = new LinkedList<>();
    private LinkedList<Comp> newComponents = new LinkedList<>();
    private LinkedList<Comp> removeComponents = new LinkedList<>();
    public Transform transform = new Transform(this);
    public ArrayList<Comp> components = new ArrayList<>();
    public ArrayList<GameObject> gameObjects = new ArrayList<>();
    @Getter @Setter private boolean isMouseInside = false;

    public void removeComponent(Comp component){
        removeComponents.add(component);
        if (!started) addObjects();
    }
    public void addComponent(Comp component){
        newComponents.add(component);
        if (!started) addObjects();
    }
    public void addComp(Comp component){
        component.setTransform(this.transform);
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

        for(Comp c : components){
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
        for(Comp c : components){
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
            for(Comp g : newComponents) addComp(g);
            newComponents.clear();
        }
        if(removeComponents.size() > 0 ){
            for(Comp c : removeComponents) components.remove(c);
            removeComponents.clear();
        }

    }
    public void update(){
        if(colliding != null) onCollisionLeft(colliding);
        addObjects();
        for(Comp c : components){
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
        if(GameEngine.getSelectedScene().isEditing() && editorComponent.isStarted())
            editorComponent.render(g);

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
    public void onCollisionEnter(Collider collider) {
        colliding = collider;
    }
    public void onCollisionLeft(Collider collide){
        colliding = null;
    }
    public void destroy() {
        GameEngine.getSelectedScene().getGameObjectHandler().getRemoveGameObject().add(this);
    }
}
