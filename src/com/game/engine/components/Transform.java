package com.game.engine.components;

import com.game.engine.GameObject;
import com.game.engine.msc.Debug;
import com.game.engine.msc.Vector2;
import lombok.Getter;
import lombok.Setter;

/**
 * The transform class is the base of the GameObjects form.
 * It is the position rotation scale and parenting.
 */
public class Transform extends Component{

    @Setter
    private Vector2 globalPosition = new Vector2();

    @Getter
    @Setter
    private Vector2 localPosition = new Vector2();

    @Getter
    @Setter
    private float rotation = 0f;

    @Getter
    @Setter
    private Vector2 localScale = new Vector2(1,1);

    @Setter
    private Vector2 globalScale = new Vector2(10,10);

    @Getter
    @Setter
    private GameObject gameObject;

    @Getter
    @Setter
    private GameObject parent;

    private Vector2 scalePositionThing = new Vector2(0,0);

    public Transform(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public Vector2 getGlobalPosition() {
        return globalPosition.add(localPosition.multiply(scalePositionThing));
    }

    public Vector2 getGlobalScale() {
        if(localScale.equals(Vector2.zero)) return new Vector2(1,1);
        return globalScale.add(localScale);
    }

    public Vector2 getScale(){
        return globalScale;
    }
    public void setScale(Vector2 scale){
        if(parent == null){

            setScaleFactor(scale.divide(globalScale));
        }
        else{
            if(localScale.containsZero()){
                Debug.log("ues");
                localScale = scale;
                Debug.log(localScale);

            }
            setScaleFactor(scale.divide(localScale));
            localScale = scale;
        }
    }
    public void setScaleFactor(Vector2 factor){
        globalScale = globalScale.multiply(factor);
        //scale children
        for(GameObject g : gameObject.gameObjects){
            g.transform.setScaleFactor(factor);
        }
    }

    public Vector2 getPosition() {
        if(parent == null)
            return globalPosition;
        return localPosition;
    }

    public void setPosition(Vector2 position) {
        if(parent == null)
            globalPosition = position;
        else{
            localPosition = position;
        }
    }

    @Override
    public void update() {
        super.update();
        //update parent values
        if(parent != null){
            globalPosition = parent.transform.getPosition();
            globalScale = parent.transform.getScale().add(localScale);

            //this is for parent scaling so the object moves when parent scales
            if(!localScale.containsZero())
                scalePositionThing = (globalScale.divide(localScale)).divide(10);

            rotation = parent.transform.getRotation();

        }
    }

    @Override
    public void start() {
        if(parent != null) globalScale = parent.transform.getGlobalScale().add(localScale);
        Debug.log(globalScale);
        super.start();
    }

    @Override
    public String toString() {
        return "Transform{" +
                "globalPosition=" + getGlobalPosition() +
                ", \nlocalPosition=" + localPosition +
                ", \nrotation=" + rotation +
                ", \nlocalScale=" + localScale +
                ", \nglobalScale=" + getGlobalScale() +
                ", \ngameObject=" + gameObject +
                ", \nparent=" + parent +
                ", \nscalePositionThing=" + scalePositionThing +
                '}';
    }
}
